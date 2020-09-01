package org.lanqiao.daoImpl;

/*
 * uid,username,password,telephone,sex,email,
 * status(1����ʹ��2������),money(���),vip(1��2��), 
 * rid,counts(���ش���)
 */

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.lanqiao.bean.User;
import org.lanqiao.dao.UserDao;
import org.lanqiao.utils.JDBCUtil;

public class UserDaoImpl implements UserDao {
	
	//����ִ��sql�Ļ���
	static QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());

	//1.��ѯȫ��user��Ϣ:
	@Override
	public List<User> queryAllUser() throws SQLException {
		String sql = "select * from user";
		List<User> list = runner.query(sql, new BeanListHandler<User>(User.class));
		return list;
	}

	//2.�����û�id��ѯ�û���Ϣ:
	@Override
	public User queryUserByUid(int uid) throws SQLException {
		String sql = "select * from user where uid = ?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), uid);
		return user;
	}

	//3.�����û���Ϣ
	@Override
	public int addUser(String username, String password, String telephone, String sex, String email,
			 String status, double money,
			 String vip, int rid, int counts) throws SQLException {
		String sql = "insert into user values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {null, username, password, telephone, sex, email, status, money, vip, rid, counts};
		int i = runner.update(sql, params);
		return i;
	}

	//4.����id�޸��û���Ϣ
	@Override
	public int updateUser(int uid, String password, String telephone, String email) throws SQLException {
		String sql = "update user set password = ?, telephone = ?, email = ? where uid = ?";
		Object[] params = {password, telephone, email, uid};
		int i = runner.update(sql, params);
		return i;
	}

	//5.����id"ɾ��"�û���Ϣ
	@Override
	public int deleteUser(int uid, String status) throws SQLException {
		String sql = "update user set status = ? where uid = ?";
		int i = runner.update(sql, status, uid);
		return i;
	}


	//6.�����û����ƺ������ѯ�û���Ϣ
	@Override
	public User queryUserByUserNamePassword(String username, String password) throws SQLException {
		String sql = "select * from user where username = ? and password = ?";
		return runner.query(sql, new BeanHandler<User>(User.class), username, password);
	}

	//7.�����û�����ѯ�û���Ϣ
	@Override
	public User queryUserByUserName(String username) throws SQLException {
		String sql = "select * from user where username = ?";
		return runner.query(sql, new BeanHandler<User>(User.class), username);
	}

	//8.ע��
	@Override
	public int registor(String username, String password, String telephone, String sex, String email,
			 String status, double money,
			 String vip, int rid, int counts) throws SQLException {
		//�ȸ����û�����ѯ�Ƿ��������
		String sql = "select * from user where username = ?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), username);
		int i = 0;
		if(user == null) {
			i = addUser(username, password, telephone, sex, email, status, money, vip, rid, counts);
		}
		return i;
	}

	//9.��¼
	@Override
	public User login(String username, String password) throws SQLException {
		String sql = "select * from user where username = ? and password = ?";
		return runner.query(sql, new BeanHandler<User>(User.class), username, password);
	}
	
	//10.��ֵ
	@Override
	public int recharge(String username, int money, int uid, String created) throws SQLException {
		String sql = "update user set money = money + ? where username = ?";
		int i = runner.update(sql, money , username);
		//�����Ѽ�¼���������(0����������,û��bid=0)(2������ͨ�û�)("1"�����ֵ)
		String sql1 = "insert into user_consume values(?,?,?,?,?,?,?)";
		Object[] params = {null, uid, created, 0, 2, "1", money};
		int j = runner.update(sql1, params);
		if(i != 0 && j != 0) {
			return i;
		}
		return 0;
	}

	//11.��Ϊ��Ա
	@Override
	public int toVip(double money, int uid, String created) throws SQLException {
		//1.�ı�user����vip��״ֵ̬
		String sql = "update user set vip = ? where uid = ?";
		int i = runner.update(sql, 1 , uid);
		//2.������Ѽ�¼
		String sql1 = "insert into user_consume values(?,?,?,?,?,?,?)";
		Object[] params = {null, uid, created, 0, 1, "2", money};
		int j = runner.update(sql1, params);
		if(i != 0 && j != 0) {
			return i;
		}
		return 0;
	}

	//12.����(��ֵvip�������鼮)
	@Override
	public int consumption(int uid, double money) throws SQLException {
		String sql = "update user set money = money - ? where uid = ?";
		return runner.update(sql, money , uid);
	}

	//13.��ѯ�û����
	@Override
	public double muchMoney(int uid) throws SQLException {
		String sql = "select money from user where uid = ?";
		double i = runner.query(sql, new ScalarHandler<>(), uid);
		return i;
	}

	//14.��ѯ���й���Ա�򳬼�����Ա
	@Override
	public List<User> queryAllManager(int rid) throws SQLException {
		String sql = "select * from user where rid = ?";
		List<User> list = runner.query(sql, new BeanListHandler<User>(User.class), rid);
		return list;
	}

	//15.��ӹ���Ա
	@Override
	public int addManager(String username, String password) throws SQLException {
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {null, username, password, null, null, null, "1", 0, 2, 3, 0};
		int i = runner.update(sql, params);
		return i;
	}

	//16.����id�޸Ĺ���Ա��Ϣ
	@Override
	public int updateManager(int uid, String password, String telephone, String gendar, String email) throws SQLException {
		String sql = "UPDATE USER SET PASSWORD = ?, telephone = ?, email = ?, sex = ? WHERE uid = ?";
		Object[] params = {password, telephone, gendar, email, uid};
		int i = runner.update(sql, params);
		return i;
	}
	

	//17.�����û�����ѯ����Ա��Ϣ
	@Override
	public User queryManagerByUserName(String username) throws SQLException {
		String sql = "select * from user where username = ? and rid = 3";
		return runner.query(sql, new BeanHandler<User>(User.class), username);
	}
	
}
