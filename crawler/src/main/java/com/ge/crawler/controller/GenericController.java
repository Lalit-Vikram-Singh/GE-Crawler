package com.ge.crawler.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ge.crawler.service.GenericService;

/**
 * @author Lalit
 *
 */
@RestController
@RequestMapping("/api")
public class GenericController {

	@RequestMapping(value = "/startCrawler", method = RequestMethod.POST)
	public String startCrawler(HttpServletRequest request, @RequestBody String payload)
			throws IOException, InterruptedException {

		GenericService genService = new GenericService();
		return genService.parseUrl(payload);
	}
}
