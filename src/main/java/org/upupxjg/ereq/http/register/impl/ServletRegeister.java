package org.upupxjg.ereq.http.register.impl;

import java.util.Set;

import javax.servlet.http.HttpServlet;

import org.upupxjg.ereq.core.pool.ServletPool;
import org.upupxjg.ereq.http.annotation.AsPath;
import org.upupxjg.ereq.http.annotation.asEnable;
import org.upupxjg.ereq.http.register.Regeister;
import org.upupxjg.ereq.util.EREQLogger;

/**
 * @author Jian'gang
 *
 */
public class ServletRegeister implements Regeister {

	private static EREQLogger logger = EREQLogger.getLogger(ServletRegeister.class);

	/* (non-Javadoc)
	 * @see org.upupxjg.ereq.http.register.Regeister#regist(java.util.Set)
	 */
	@Override
	public void regist(Set<Class<?>> classes) {
		logger.debug("=================== servlet register =================");
		for (Class<?> clazz : classes) {
			String path = null;
			// extends from httpservlet
			logger.debug("scan class:"+clazz.getName());
			if (HttpServlet.class.isAssignableFrom(clazz)) {
				
				asEnable aEnable = clazz.getAnnotation(asEnable.class);
				if (aEnable != null && !aEnable.enable()) {
					logger.info("skip unabled class: "+clazz.getName());
					continue;
				}
				path = GeneratePath(clazz);
				if (path != null) {
					try {
						// 对于重复的会默认覆盖
						ServletPool.regeist(path, (HttpServlet) clazz.newInstance());
						logger.info("Mapping '"+path+"'->'" + clazz.getName() + "'");
					} catch (InstantiationException e) {
						logger.error(clazz.getName() + "can not be instanted!");
					} catch (IllegalAccessException e) {
						logger.error(clazz.getName() + "'s constructor can not be called!");
					}
				}
			}

		}

	}

	protected static String GeneratePath(Class<?> clazz) {
		String path = null;
		AsPath apath = clazz.getAnnotation(AsPath.class);
		if (apath != null) {
			path = apath.path();
		} else {
			path = clazz.getName().replace('.', '/');
		}
		return path;
	}
}
