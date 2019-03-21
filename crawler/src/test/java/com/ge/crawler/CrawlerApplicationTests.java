package com.ge.crawler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lalit
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerApplicationTests {

	@Test
	@Disabled
	public void contextLoads() {
		String str = "Checking";
		assertEquals("Checking", str);
	}
}
