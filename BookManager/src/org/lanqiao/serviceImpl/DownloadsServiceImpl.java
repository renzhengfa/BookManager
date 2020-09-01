package org.lanqiao.serviceImpl;

import java.sql.SQLException;

import org.lanqiao.dao.DownloadsDao;
import org.lanqiao.daoImpl.DownloadsDaoImpl;
import org.lanqiao.service.DownloadsService;

/*
 * �����û�ͼ�����ؼ�¼�����
 * 
 * 1.����������(ÿһ�����Ѽ�¼):(int addRecord(int uid,int bid,String created))
 */

public class DownloadsServiceImpl implements DownloadsService {

	//����DaoImpl����Ҫ�Ķ���
	DownloadsDao downloadsDao = new DownloadsDaoImpl();
	
	//1.����������(ÿһ�����Ѽ�¼):
	@Override
	public int addRecord(int uid, int bid, String created) {
		int i = 0;
		try {
			i = downloadsDao.addRecord(uid, bid, created);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
