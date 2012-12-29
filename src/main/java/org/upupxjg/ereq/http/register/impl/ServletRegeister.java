package org.upupxjg.ereq.http.register.impl;

import java.util.Set;

import javax.servlet.http.HttpServlet;

import org.upupxjg.ereq.core.pool.ServletPool;
import org.upupxjg.ereq.http.annotation.AsPath;
import org.upupxjg.ereq.http.annotation.AsServlet;
import org.upupxjg.ereq.http.register.Regeister;
import org.upupxjg.ereq.util.EREQLogger;

public class ServletRegeister implements Regeister {

	private static EREQLogger  logger = EREQLogger.getLogger(ServletRegeister.class);
	@Override
	public void regist(Set<Class<?>> classes) {
		for (Class<?> clazz : classes) {
			String path = null;
			//extends from httpservlet
			if (clazz.isAssignableFrom(HttpServlet.class) ) {
				path = GeneratePath(clazz);
				if (path != null) {
					try {
						//对于重复的会默认覆盖
						ServletPool.regeist(path, (HttpServlet)clazz.newInstance());
						logger.info("Mapping 'path'->'"+clazz.getName()+"'");
					} catch (InstantiationException e) {
						logger.error(clazz.getName()+"can not be instanted!");
					} catch (IllegalAccessException e) {
						logger.error(clazz.getName()+"'s constructor can not be called!");
					}
				}
			}

		}

		
	}


	protected static String GeneratePath(Class<?> clazz){
		String path = null;
		AsPath apath = clazz.getAnnotation(AsPath.class);
		if (apath != null) {
			path = apath.path();
		}else{
			path = clazz.getPackage().getName().replace('.', '/')+clazz.getName();
		}
		return path;
	}
}
