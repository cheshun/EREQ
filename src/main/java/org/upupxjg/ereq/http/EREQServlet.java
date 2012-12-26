package org.upupxjg.ereq.http;

import org.upupxjg.ereq.http.annotation.AsPath;
import org.upupxjg.ereq.http.annotation.AsServlet;

@AsServlet
public  interface EREQServlet {
	
	@AsPath(path = "*")
	public void execute(EREQHttpRequest request,EREQHttpResponse response);
}
