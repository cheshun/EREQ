package org.upupxjg.ereq.scanner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.upupxjg.ereq.util.Properties;

public class ScannerTest {

	@Before
	public void init(){
		Properties.init();
	}
	@Test
	public void testInit() {
		new Scanner().init();
	}

}
