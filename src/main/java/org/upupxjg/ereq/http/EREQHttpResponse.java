package org.upupxjg.ereq.http;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class EREQHttpResponse extends HttpServletResponseWrapper {

	public EREQHttpResponse(HttpServletResponse response) {
		super(response);
	}

}
