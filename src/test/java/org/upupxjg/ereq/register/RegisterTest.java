package org.upupxjg.ereq.register;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


import org.junit.Before;
import org.junit.Test;
import org.upupxjg.ereq.common.EREQException;
import org.upupxjg.ereq.core.pool.FilterPool;
import org.upupxjg.ereq.core.pool.ServletPool;
import org.upupxjg.ereq.http.EREQFilter;
import org.upupxjg.ereq.http.register.Regeister;
import org.upupxjg.ereq.http.register.impl.FilterRegeister;
import org.upupxjg.ereq.http.register.impl.ServletRegeister;
import org.upupxjg.ereq.scanner.Scanner;
import org.upupxjg.ereq.util.Properties;

public class RegisterTest {
	
	//private  Properties properties;
	private Scanner scanner;
	private ServletRegeister servletRegeister;
	private FilterRegeister filterRegeister;
	@Before
	public void init(){
		Properties.init();
		scanner = new Scanner();
		scanner.init();
		servletRegeister = new ServletRegeister();
		filterRegeister = new FilterRegeister();
		try {
			scanner.scan(new Regeister[]{servletRegeister,filterRegeister});
		} catch (EREQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testRegist() {
		
		try {
			HttpServlet servlet = ServletPool.find("test/test");
			assertNotNull(servlet);
			for (EREQFilter filter : FilterPool.getFilterList()) {
				assertNotNull(filter);
				System.out.println(filter.getPath());
				filter.getFilter().doFilter(null, null, null);
			}
		} catch (EREQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
