package org.upupxjg.ereq.http.register.impl;

import java.util.Set;

import javax.servlet.Filter;

import org.upupxjg.ereq.core.pool.FilterPool;
import org.upupxjg.ereq.http.EREQFilter;
import org.upupxjg.ereq.http.annotation.AsPath;
import org.upupxjg.ereq.http.annotation.asEnable;
import org.upupxjg.ereq.http.register.Regeister;
import org.upupxjg.ereq.util.EREQLogger;

public class FilterRegeister implements Regeister {
	private static EREQLogger logger = EREQLogger.getLogger(ServletRegeister.class);

	@Override
	public void regist(Set<Class<?>> classes) {
		logger.debug("================filter register ===================");
		for (Class<?> clazz : classes) {
			String path = null;
			// extends from filter
			logger.debug("scann class:"+clazz.getName());
			if (Filter.class.isAssignableFrom(clazz)) {
				
				asEnable aEnable = clazz.getAnnotation(asEnable.class);
				if (aEnable != null && !aEnable.enable()) {
					logger.info("skip unabled class: "+clazz.getName());
					continue;
				}
				AsPath apath = clazz.getAnnotation(AsPath.class);
				if (apath !=  null) {
					path = apath.path();
					try {
						FilterPool.regeist(new EREQFilter(path, (Filter) clazz.newInstance()));
						logger.info("Mapping Filter'"+path+"'->'" + clazz.getName() + "'");
					} catch (InstantiationException e) {
						logger.error(clazz.getName() + "can not be instanted!");
					} catch (IllegalAccessException e) {
						logger.error(clazz.getName() + "'s constructor can not be called!");
					}

				}

			}

		}
	}

}
