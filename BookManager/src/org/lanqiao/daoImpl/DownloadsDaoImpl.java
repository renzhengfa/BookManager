package org.lanqiao.daoImpl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.lanqiao.dao.DownloadsDao;
import org.lanqiao.utils.JDBCUtil;

public class DownloadsDaoImpl implements DownloadsDao {

	//��ȡ��ѯ����
	static QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
	
	//1.�����ؼ�¼���������(ÿһ�����Ѽ�¼)
	@Override
	public int addRecord(int uid, int bid, String created) throws SQLException {
		int i = runner.update("insert into downloads values(null, ?, ?, ?)", uid, bid, created);
		return i;
	}

}
