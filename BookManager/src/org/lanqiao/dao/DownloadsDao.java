package org.lanqiao.dao;

import java.sql.SQLException;

/*
 * �����û�ͼ�����ؼ�¼��Ľӿ�
 * did,uid(�û�id),bid(ͼ��id), created(����ʱ��)
 * 
 * 1.����������(ÿһ�����Ѽ�¼):(int addRecord(int uid,int bid,String created))
 */

public interface DownloadsDao {
	
	//1.����������(ÿһ�����Ѽ�¼)
	int addRecord(int uid,int bid,String created) throws SQLException;
	
}
