package org.lanqiao.bean;

/*
 * ������
 * did,uid(�û�id),bid(ͼ��id), created(����ʱ��)
 */

public class Downloads {

	private int did;
	private int uid;
	private int bid;
	private String created;
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	@Override
	public String toString() {
		return "Downloads [did=" + did + ", uid=" + uid + ", bid=" + bid + ", created=" + created + "]";
	}
	
}
