package org.upupxjg.ereq.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EREQHttpRequest extends HttpServletRequestWrapper{


	public EREQHttpRequest(HttpServletRequest request) {
		super(request);
	}

	
}
