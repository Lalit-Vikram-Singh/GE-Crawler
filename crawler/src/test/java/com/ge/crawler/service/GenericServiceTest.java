/**
 * 
 */
package com.ge.crawler.service;



import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * @author Lalit
 *
 */

public class GenericServiceTest {
	
	/**
	 * Temp Test 
	 */
	@Test
	public void testEx() {
		String str = "Junit5 is working fine";
		assertEquals("Junit5 is working fine", str);
	}

	/**
	 * Test method for {@link com.ge.crawler.service.GenericService#parseUrl(java.lang.String)}.
	 * @throws JSONException 
	 */
	@Test
	public void testParseUrl() throws JSONException {
		GenericService gs = new GenericService();
		String internet_1 = "{\"pages\":[{\"address\":\"http://foo.bar.com/p1\",\"links\":[\"http://foo.bar.com/p2\",\"http://foo.bar.com/p3\",\"http://foo.bar.com/p4\"]},{\"address\":\"http://foo.bar.com/p2\",\"links\":[\"http://foo.bar.com/p2\",\"http://foo.bar.com/p4\"]},{\"address\":\"http://foo.bar.com/p4\",\"links\":[ \"http://foo.bar.com/p5\",\"http://foo.bar.com/p1\",\"http://foo.bar.com/p6\"]},{\"address\":\"http://foo.bar.com/p5\",\"links\":[]},{\"address\":\"http://foo.bar.com/p6\",\"links\":[\"http://foo.bar.com/p7\",\"http://foo.bar.com/p4\",\"http://foo.bar.com/p5\"]}]}";
		String internet_2 = "{\"pages\":[{\"address\":\"http://foo.bar.com/p1\",\"links\":[\"http://foo.bar.com/p2\"]},{\"address\":\"http://foo.bar.com/p2\",\"links\":[\"http://foo.bar.com/p3\"]},{\"address\":\"http://foo.bar.com/p3\",\"links\":[\"http://foo.bar.com/p4\"]},{\"address\":\"http://foo.bar.com/p4\",\"links\":[\"http://foo.bar.com/p5\"]},{\"address\":\"http://foo.bar.com/p5\",\"links\":[\"http://foo.bar.com/p1\"]},{\"address\":\"http://foo.bar.com/p6\",\"links\":[\"http://foo.bar.com/p1\"]}]}";
		String internet_3 = "{\"pages\":[{\"address\":\"http://foo.bar.com/p1\",\"links\":[\"http://foo.bar.com/p999999999\"]},{\"address\":\"http://foo.bar.com/p2\",\"links\":[\"http://foo.bar.com/p3\"]},{ \"address\":\"http://foo.bar.com/p3\",\"links\":[\"http://foo.bar.com/p4\"]},{\"address\":\"http://foo.bar.com/p4\",\"links\":[\"http://foo.bar.com/p5\"]},{\"address\":\"http://foo.bar.com/p5\",\"links\":[\"http://foo.bar.com/p1\"]},{\"address\":\"http://foo.bar.com/p6\",\"links\":[\"http://foo.bar.com/p1\"]}]}";
		
		String actual_1 = gs.parseUrl(internet_1);
		JSONObject inputPayload = new JSONObject(actual_1);
		JSONArray successList = (JSONArray) inputPayload.get("Success");
		int successCountIn1Input1 = successList.length();
		
		String actual_2 = gs.parseUrl(internet_2);
		JSONObject inputPayload2 = new JSONObject(actual_2);
		JSONArray successList2 = (JSONArray) inputPayload2.get("Success");
		int successCountIn1Input2 = successList2.length();
		
		String actual_3 = gs.parseUrl(internet_3);
		JSONObject inputPayload3 = new JSONObject(actual_3);
		JSONArray successList3 = (JSONArray) inputPayload3.get("Success");
		int successCountIn1Input3 = successList3.length();
		
		
		assertAll(
				()->assertEquals(5, successCountIn1Input1),
				()->assertEquals(5, successCountIn1Input2),
				()->assertEquals(1, successCountIn1Input3)
				);
	}

}
