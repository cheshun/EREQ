package org.upupxjg.ereq.http;

import javax.servlet.Filter;

public  class EREQFilter {
	private String path;
	private Filter filter;
	
	
	
	public EREQFilter(String path, Filter filter) {
		this.path = path;
		this.filter = filter;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
}
