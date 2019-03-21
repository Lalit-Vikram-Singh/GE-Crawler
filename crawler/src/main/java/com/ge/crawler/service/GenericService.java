package com.ge.crawler.service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lalit
 *
 */
public class GenericService {

	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	LinkedHashMap<String, ArrayList<String>> addressVsUrlLinks;
	HashSet<String> successList;
	HashSet<String> skipList;
	HashSet<String> errorList;

	/**
	 * @param Type:String - Value:payload
	 * @return String
	 * This method is used for parsing input Json of Address and Urls 
	 */
	public String parseUrl(String payload) {
		
		addressVsUrlLinks = new LinkedHashMap<>();
		successList = new HashSet<>();
		skipList = new HashSet<>();
		errorList = new HashSet<>();
		
		JSONObject inputPayload = new JSONObject(payload);
		JSONObject yieldObject = new JSONObject();
		JSONArray pages = inputPayload.getJSONArray("pages");
		
		for (int i = 0; i < pages.length(); i++) {
			JSONObject currentPage = (JSONObject) pages.get(i);
			String AddressUrl = currentPage.getString("address");
			JSONArray linkUrls = currentPage.getJSONArray("links");

			ArrayList<String> stubLinkUrls = new ArrayList<>();
			for (int j = 0; j < linkUrls.length(); j++) {
				String currentLink = linkUrls.get(j).toString();
				stubLinkUrls.add(currentLink);
			}
			addressVsUrlLinks.put(AddressUrl, stubLinkUrls);
		}
		
		LOG.debug("Map of address and links :: " + addressVsUrlLinks);
		
		int countFlag = 0;
		
		for (Entry<String, ArrayList<String>> entry : addressVsUrlLinks.entrySet()) {
			String key = entry.getKey();
			if (countFlag == 0) {
				filler(key);
			} else {
				break;
			}
			countFlag++;

		}

		LOG.info("Sucess List :: " + successList);
		LOG.info("Skipped List :: " + skipList);
		LOG.info("Error List :: " + errorList);
		
		yieldObject.put("Success", successList);
		yieldObject.put("Skipped", skipList);
		yieldObject.put("Error", errorList);
		
		return yieldObject.toString();
	}

	
	/**
	 * @param Type:String - Value:address
	 * @return Void
	 * This method is for filling values in output sets Ex(successList,skipList and errorList) 
	 */
	public void filler(String address) {
		
		LOG.debug("Filler current url address :: " + addressVsUrlLinks);
		boolean isAddressPresent = addressVsUrlLinks.containsKey(address);
		boolean isPresentInSuccessList = successList.contains(address);
		LOG.debug("Is address present in map :: " + isAddressPresent + " and in success list :: " + isPresentInSuccessList);

		if (isAddressPresent) {
			if (isPresentInSuccessList) {
				skipList.add(address);
			} else {
				successList.add(address);
				ArrayList<String> values = addressVsUrlLinks.get(address);
				if (CollectionUtils.isNotEmpty(values)) {
					for (String currentURL : values) {
						if (StringUtils.isNotEmpty(currentURL)) {
							filler(currentURL);
						}
					}
				}
			}

		} else {
			errorList.add(address);
		}
	}

	public static void main(String... args) throws IOException, InterruptedException {
		GenericService gs = new GenericService();
		String in1 = "{\"pages\":[{\"address\":\"http://foo.bar.com/p1\",\"links\":[\"http://foo.bar.com/p2\",\"http://foo.bar.com/p3\",\"http://foo.bar.com/p4\"]},{\"address\":\"http://foo.bar.com/p2\",\"links\":[\"http://foo.bar.com/p2\",\"http://foo.bar.com/p4\"]},{\"address\":\"http://foo.bar.com/p4\",\"links\":[ \"http://foo.bar.com/p5\",\"http://foo.bar.com/p1\",\"http://foo.bar.com/p6\"]},{\"address\":\"http://foo.bar.com/p5\",\"links\":[]},{\"address\":\"http://foo.bar.com/p6\",\"links\":[\"http://foo.bar.com/p7\",\"http://foo.bar.com/p4\",\"http://foo.bar.com/p5\"]}]}";
		String in2 = "{\"pages\":[{\"address\":\"http://foo.bar.com/p1\",\"links\":[\"http://foo.bar.com/p2\"]},{\"address\":\"http://foo.bar.com/p2\",\"links\":[\"http://foo.bar.com/p3\"]},{\"address\":\"http://foo.bar.com/p3\",\"links\":[\"http://foo.bar.com/p4\"]},{\"address\":\"http://foo.bar.com/p4\",\"links\":[\"http://foo.bar.com/p5\"]},{\"address\":\"http://foo.bar.com/p5\",\"links\":[\"http://foo.bar.com/p1\"]},{\"address\":\"http://foo.bar.com/p6\",\"links\":[\"http://foo.bar.com/p1\"]}]}";
		String in3 = "{\"pages\":[{\"address\":\"http://foo.bar.com/p1\",\"links\":[\"http://foo.bar.com/p999999999\"]},{\"address\":\"http://foo.bar.com/p2\",\"links\":[\"http://foo.bar.com/p3\"]},{ \"address\":\"http://foo.bar.com/p3\",\"links\":[\"http://foo.bar.com/p4\"]},{\"address\":\"http://foo.bar.com/p4\",\"links\":[\"http://foo.bar.com/p5\"]},{\"address\":\"http://foo.bar.com/p5\",\"links\":[\"http://foo.bar.com/p1\"]},{\"address\":\"http://foo.bar.com/p6\",\"links\":[\"http://foo.bar.com/p1\"]}]}";
		
		String outputJson1 = gs.parseUrl(in1);
		String outputJson2 = gs.parseUrl(in2);
		String outputJson3 = gs.parseUrl(in3);
		
		LOG.info("Output of first input url Json " + outputJson1);
		LOG.info("Output of sceond input url Json " + outputJson2);
		LOG.info("Output of third input url Json " + outputJson3);
	}
}
