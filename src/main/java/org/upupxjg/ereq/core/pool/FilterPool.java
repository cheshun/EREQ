package org.upupxjg.ereq.core.pool;


import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.upupxjg.ereq.http.EREQFilter;


public  class FilterPool {

	
	private static FilterPool pool = new FilterPool();
	
	public static void regeist(EREQFilter filter){
		pool.innerRegeist( filter);
	}
	
	public static List<EREQFilter> getFilterList(){
		return pool.getFilters();
	}
	
	private List<EREQFilter> filterSet;
	
	private List<EREQFilter> getFilters(){
		return this.filterSet;
	}

	private void innerRegeist( EREQFilter filter) {
		filterSet.add(filter);
	}

	private FilterPool(){
		filterSet = new LinkedList<EREQFilter>();
	}

}
