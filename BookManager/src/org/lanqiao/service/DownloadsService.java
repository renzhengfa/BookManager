package org.lanqiao.service;


/*
 * �����û�ͼ�����ؼ�¼��Ľӿ�
 * 
 * 1.����������(ÿһ�����Ѽ�¼):(int addRecord(int uid,int bid,String created))
 */


public interface DownloadsService {
	
	//1.����������(ÿһ�����Ѽ�¼)
	int addRecord(int uid,int bid,String created);
	
}
