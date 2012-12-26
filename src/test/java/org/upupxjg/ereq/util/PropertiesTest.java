package org.upupxjg.ereq.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PropertiesTest {

	@Test
	public void test() {
		Properties.init();
		assertEquals(false, Properties.scan().recursion());
		assertEquals(true, Properties.loadLazy());
		assertNotNull(Properties.scan().packages());
		assertNotNull(Properties.scan().except());
	}

}
