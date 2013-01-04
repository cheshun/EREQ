package org.upupxjg.ereq.scanner;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.upupxjg.ereq.common.EREQException;
import org.upupxjg.ereq.http.register.Regeister;
import org.upupxjg.ereq.util.EREQLogger;
import org.upupxjg.ereq.util.Properties;

public class Scanner {
	private static EREQLogger logger = EREQLogger.getLogger(Scanner.class);
	private static Set<Class<?>> classes = new LinkedHashSet<Class<?>>();

	private Regeister[] regeisters;

	private Set<String> packageSet;
	private Set<String> unPackageSet;
	private Set<String> scanned;

	public void init() {

		logger.info("##  ======  ## Scanner INIT ##  ======  ##");

		packageSet = new LinkedHashSet<String>();
		unPackageSet = new LinkedHashSet<String>();
		scanned = new LinkedHashSet<String>();
		logger.debug("init packageSet,unPackageSet,scanned OK");

		// load all packages and not want
		Collections.addAll(packageSet, Properties.scan().packages());
		Collections.addAll(unPackageSet, Properties.scan().except());
		logger.debug("fill up packageSet,unpackageSet OK");
		// scan packages

		Iterator<String> iterator = packageSet.iterator();

		while (iterator.hasNext()) {
			classes.addAll(packageScan(iterator.next()));
		}
		logger.debug("fill up scanned,classes OK load");
		logger.info("##  ======  ## Scanner INIT FINISHED "+classes.size()+" classed loaded   ##  ======  ##");
	}



	public void setRegeisters(Regeister[] regeisters) {
		this.regeisters = regeisters;
	}

	/**
	 * before call this method ,you must call init() first!
	 * @param regeisters
	 * @throws EREQException
	 */
	public void scan(Regeister[] regeisters) throws EREQException {
		if (regeisters.length != checkRegeisterStauts(regeisters)) {
			logger.error("Scanner.scan::regeisters not all ready!");
			throw new EREQException();
		}else if(classes == null || classes.size() == 0){
			logger.warn("There is no class to regist");
			return;
		}
		for (Regeister regeister : regeisters) {
			regeister.regist(classes);
		}

	}

	public void scan() throws EREQException {
		this.scan(this.regeisters);
	}

	/**
	 * get OK registers number
	 * 
	 * @param regeisters
	 * @return
	 */
	protected int checkRegeisterStauts(Regeister[] regeisters) {

		if (regeisters == null)
			return 0;

		int i = 0;
		for (Regeister regeister : regeisters) {
			if (null != regeister) {
				i++;
			}
		}
		return i;
	}

	private Set<Class<?>> packageScan(String pack) {
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		String packageName = pack;
		String packageDirName = pack.replace('.', '/');

		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = (URL) dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					String filepath = URLDecoder.decode(url.getFile(), "UTF-8");
					innerScanner(packageName, filepath, Properties.scan().recursion(), classes);
				} else if ("jar".equals(protocol)) {
					logger.info("Load jar File:" + url.getPath());
					innerJarScanner(url, packageName, packageDirName, Properties.scan().recursion(), classes);
				}

			}
		} catch (IOException e) {
			logger.error("Can not Read Package PATH:" + packageDirName);
		}
		return classes;
	}

	private void innerJarScanner(URL url, String packageName, String packagePath, boolean recurise,
			Set<Class<?>> classes) {

		try {
			JarFile jarfile = ((JarURLConnection) url.openConnection()).getJarFile();
			Enumeration<JarEntry> entry = jarfile.entries();

			while (entry.hasMoreElements()) {
				JarEntry jarEntry = (JarEntry) entry.nextElement();
				String name = jarEntry.getName();
				if (name.charAt(0) == '/') {
					name = name.substring(1);
				}

				if (name.startsWith(packagePath)) {
					int index = name.lastIndexOf('/');
					if (index != -1) {
						name = name.substring(0, index).replace('/', '.');
					}
					if (index != -1 || recurise) {
						if (!jarEntry.isDirectory() || name.endsWith(".class")) {
							String className = name.substring(packageName.length() + 1, name.length() - 6);

							try {
								classes.add(Thread.currentThread().getContextClassLoader().loadClass(className));
								logger.debug("add class:" + className);
							} catch (ClassNotFoundException e) {
								logger.error("Can not load class:" + className);
							}
						}
					}
				}

			}
		} catch (IOException e) {
			logger.error("Can not Read Jar File:" + url.getPath());
		}
	}

	private void innerScanner(String packageName, String packagePath, final boolean recurise, Set<Class<?>> classes) {

		if (scanned.contains(packageName)) {
			logger.warn("find reduplicate packageName:" + packageName + ",skip scan");
			return;
		}

		scanned.add(packageName);
		logger.info("SCAN PATH:" + packagePath);
		if (unPackageSet.contains(packageName)) {
			logger.info("Skip not wanted package:" + packageName);
			return;
		}

		File dir = new File(packagePath);
		if (!dir.exists() || !dir.isDirectory()) {
			logger.warn("there is no file in package:" + packageName );
			return;
		}

		File[] dirFiles = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File dirFile) {
				return dirFile.getName().endsWith(".class") || (dirFile.isDirectory() && recurise);
			}
		});

		for (File file : dirFiles) {
			if (file.isDirectory()) {
				innerScanner(packageName + "." + file.getName(), file.getAbsolutePath(), recurise, classes);
			} else {
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
					logger.debug("load class:" + packageName + "." + className);
				} catch (ClassNotFoundException e) {
					logger.error("Can not load class:" + packageName + "." + className);
				}
			}
		}
	}

	@Deprecated
	private Set<String> removeConflict(Set<String> packageSet, Set<String> unPackageSet) {
		Set<String> OKSet = new LinkedHashSet<String>();

		Iterator<String> iterator = unPackageSet.iterator();
		while (iterator.hasNext()) {
			String unPackage = iterator.next();
			if (packageSet.contains(unPackage)) {
				packageSet.remove(unPackage);
			}
		}

		return OKSet;
	}

}
