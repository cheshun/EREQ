package org.upupxjg.ereq.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PropertiesTest {

	@Test
	public void test() {
		Properties.init();
		assertEquals(true, Properties.scan().recursion());
		assertEquals(false, Properties.loadLazy());
		assertNotNull(Properties.scan().packages());
		assertNotNull(Properties.scan().except());
	}

}
