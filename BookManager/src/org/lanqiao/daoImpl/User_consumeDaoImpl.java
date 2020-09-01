package org.lanqiao.daoImpl;

import java.sql.SQLException;

/*
 * id,uid(�û�id),created(ʱ��),bid(ͼ��id),
 * vip(1��2��),xc(1��ֵ2����),money
 */

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.lanqiao.bean.User_consume;
import org.lanqiao.dao.User_consumeDao;
import org.lanqiao.utils.JDBCUtil;

public class User_consumeDaoImpl implements User_consumeDao {

	//��ȡ��ѯ����
	static QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
	
	//1.�����û�id��ѯ�����û������Ѽ�¼:
	@Override
	public List<User_consume> queryUser_consumeByUid(int uid) throws SQLException {
		String sql = "select * from user_consume where uid = ?";
		List<User_consume> list = runner.query(sql, new BeanListHandler<User_consume>(User_consume.class), uid);
		return list;
	}

	//2.�������Ѽ�¼:(ÿ����һ�ξͻ����һ������)
	@Override
	public int addUser_consume(int uid, String created, int bid, String vip, String xc, double money) throws SQLException {
		String sql = "insert into user_consume values(?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {null, uid, created, bid, vip, xc, money};
		int i = runner.update(sql, params);
		return i;
	}

	//3.��ѯ�����û���vip��������(�����û�id)
	@Override
	public int queryUserVipDownloadsCounts(int uid) throws SQLException {
		String sql = "SELECT COUNT(*) FROM user_consume WHERE bid != 0 AND vip = 1 AND uid = ?";
		Long i = runner.query(sql, new ScalarHandler<>(), uid);
		return i.intValue();
	}

	//4.ͳ�������û�һ���µ������ܽ��
	@Override
	public double sumMonthConsumption(String created) throws SQLException {
		String sql = "SELECT SUM(money) FROM user_consume WHERE xc = '2' AND created LIKE ?";
		Object object = runner.query(sql, new ScalarHandler<>(), created+"%");
		object = object == null ? 0.0 : object;
		double doubleValue = Double.parseDouble(object.toString());
		return doubleValue;
	}
	
	//5.ͳ�������û�һ���µĳ�ֵ�ܽ��
	@Override
	public double sumMonthRecharge(String created) throws SQLException {
		String sql = "SELECT SUM(money) FROM user_consume WHERE xc = '1' AND created LIKE ?";
		Object object = runner.query(sql, new ScalarHandler<>(), created+"%");
		object = object == null ? 0.0 : object;
		double doubleValue = Double.parseDouble(object.toString());
		return doubleValue;
	}
	
	//6.ͳ�������û��������ܽ��
		@Override
		public double sumConsumption() throws SQLException {
			String sql = "SELECT SUM(money) FROM user_consume WHERE xc = '2'";
			Object object = runner.query(sql, new ScalarHandler<>());
			double doubleValue = Double.parseDouble(object.toString());
			return doubleValue;
		}
		
		//7.ͳ�������û��ĳ�ֵ�ܽ��
		@Override
		public double sumRecharge() throws SQLException {
			String sql = "SELECT SUM(money) FROM user_consume WHERE xc = '1'";
			Object object = runner.query(sql, new ScalarHandler<>());
			double doubleValue = Double.parseDouble(object.toString());
			return doubleValue;
		}
	
}
