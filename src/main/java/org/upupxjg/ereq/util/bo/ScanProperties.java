package org.upupxjg.ereq.util.bo;

public class ScanProperties {
	private String[] packages = null;
	private String[] exceptPackages = null;
	private  boolean recursion = true;
	
	public String[] packages() {
		return packages;
	}
	public void setPackages(String[] packages) {
		this.packages = packages;
	}
	public String[] except() {
		return exceptPackages;
	}
	public void setExceptPackages(String[] exceptPackages) {
		this.exceptPackages = exceptPackages;
	}
	public  boolean recursion() {
		return recursion;
	}
	public void setRecursion(boolean recursion) {
		this.recursion = recursion;
	}
	
	
}
