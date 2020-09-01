package org.lanqiao.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.lanqiao.bean.User;
import org.lanqiao.dao.UserDao;
import org.lanqiao.daoImpl.UserDaoImpl;
import org.lanqiao.service.UserService;

/**
 * �����û�����
 * uid,username,password,telephone,sex,email,
 * status(1����ʹ��2������),money(���),vip(1��2��), 
 * rid,counts(���ش���)
 * 
 * 1.��ѯȫ��user��Ϣ:(List<User> queryAllUser())
 * 2.�����û�id��ѯ�û���Ϣ:(User queryUserByUid(int uid))
 * 3.�����û���Ϣ
 * 4.����id�޸��û���Ϣ
 * 5.����idɾ���û���Ϣ
 * 6.�����û����ƺ������ѯ�û���Ϣ
 * 7.�����û�����ѯ�û���Ϣ
 * 8.ע��
 * 9.��¼
 */

public class UserServiceImpl implements UserService {

	//����DaoImpl����Ҫ�Ķ���
	UserDao userDao = new UserDaoImpl();
	
	// 1.��ѯȫ��user��Ϣ:
	@Override
	public List<User> queryAllUser() {
		List<User> list = null;
		try {
			list = userDao.queryAllUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 2.�����û�id��ѯ�û���Ϣ:
	@Override
	public User queryUserByUid(int uid) {
		User user = null;
		try {
			user = userDao.queryUserByUid(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// 3.�����û���Ϣ
	@Override
	public int addUser(String username, String password, String telephone, 
			double money, String sex, String email,
			String status, String vip, int rid, int counts) {
		int i = 0;
		try {
			i = userDao.addUser( username, password, telephone, sex, email, status, money, vip, rid, counts);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	// 4.����id�޸��û���Ϣ
	@Override
	public int updateUser(int uid, String password, String telephone, String email) {
		int i = 0;
		try {
			i = userDao.updateUser(uid, password, telephone, email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	// 5.����uidɾ���û���Ϣ
	@Override
	public int deleteUser(int uid, String status) {
		int i = 0;
		try {
			i = userDao.deleteUser(uid, status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//6.�����û����ƺ������ѯ�û���Ϣ
	@Override
	public User queryUserByUserNamePassword(String username, String password) {
		User user = null;
		try {
			user = userDao.queryUserByUserNamePassword(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	//7.�����û�����ѯ�û���Ϣ
	@Override
	public User queryUserByUserName(String username) {
		User user = null;
		try {
			user = userDao.queryUserByUserName(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	//8.ע��
	@Override
	public int registor(String username, String password, String telephone, String sex, String email,
			 String status, double money,
			 String vip, int rid, int counts) {
		int i = 0;
		try {
			i = userDao.registor( username, password, telephone, sex, email, status, money, vip, rid, counts);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//9.��¼
	@Override
	public User login(String username, String password) {
		User user = null;
		try {
			user = userDao.login(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	//10.��ֵ
	@Override
	public int recharge(String username, int money, int uid, String created) {
		int i = 0;
		try {
			i = userDao.recharge(username, money, uid, created);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//11.��Ϊ��Ա
	@Override
	public int toVip(double money, int uid, String created) {
		int i = 0;
		try {
			i = userDao.toVip(money, uid, created);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//12.����(��ֵvip�������鼮)
	@Override
	public int consumption(int uid, double money) {
		int i = 0;
		try {
			i = userDao.consumption(uid, money);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//13.�����û�id��ѯ�û����
	@Override
	public double muchMoney(int uid) {
		double i = 0.0;
		try {
			i = userDao.muchMoney(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	//14.��ѯ���й���Ա�򳬼�����Ա
	@Override
	public List<User> queryAllManager(int rid) {
		List<User> list = null;
		try {
			list = userDao.queryAllManager(rid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 15.��ӹ���Ա
	@Override
	public int addManager(String username, String password) {
		int i = 0;
		try {
			i = userDao.addManager(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//16.����id�޸Ĺ���Ա��Ϣ
	@Override
	public int updateManager(int uid, String password, String telephone, String email, String gendar) {
		int i = 0;
		try {
			i = userDao.updateManager(uid, password, telephone, email, gendar);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//17.�����û�����ѯ����Ա��Ϣ
	@Override
	public User queryManagerByUserName(String username) {
		User user = null;
		try {
			user = userDao.queryManagerByUserName(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
