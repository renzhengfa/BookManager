package org.lanqiao.bean;

/*
 * Ȩ����
 * fid,fname(Ȩ�޵�����),url(·��)
 */

public class Function {

	private int fid;
	private String fname;
	private String url;
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Function [fid=" + fid + ", fname=" + fname + ", url=" + url + "]";
	}
	
}
