package org.lanqiao.service;

import java.util.List;

import org.lanqiao.bean.User;

/*
 * �����û���ӿ�
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
 * 10.��ֵ
 * 11.��Ϊ��Ա
 */

public interface UserService {

	// 1.��ѯȫ��user��Ϣ:
	List<User> queryAllUser();

	// 2.�����û�id��ѯ�û���Ϣ:
	User queryUserByUid(int uid);

	// 3.�����û���Ϣ
	int addUser(/* int uid, */ String username, String password, String telephone, double money, String sex,
			String email, String status, String vip, int rid, int counts);

	// 4.����id�޸��û���Ϣ
	int updateUser(int uid, /* String username, */ String password, String telephone,
			/* double money, String sex, */ String email/*
														 * , String status, String vip, int rid, int counts
														 */);

	// 5.����idɾ���û���Ϣ
	int deleteUser(int uid, String status);

	// 6.�����û����ƺ������ѯ�û���Ϣ
	User queryUserByUserNamePassword(String username, String password);

	// 7.�����û�����ѯ�û���Ϣ
	User queryUserByUserName(String username);

	// 8.ע��
	int registor(String username, String password, String telephone, String sex, String email, String status,
			double money, String vip, int rid, int counts);

	// 9.��¼
	User login(String username, String password);

	// 10.��ֵ
	int recharge(String username, int money, int uid, String created);

	// 11.��Ϊ��Ա
	int toVip(double money, int uid, String created);

	// 12.����(��ֵvip�������鼮)
	int consumption(int uid, double money);

	// 13.����uid��ѯ���
	double muchMoney(int uid);

	// 14.��ѯ���й���Ա�򳬼�����Ա
	List<User> queryAllManager(int rid);

	// 15.��ӹ���Ա
	int addManager(String username, String password);

	//16.����id�޸Ĺ���Ա��Ϣ
	int updateManager(int uid, String password, String telephone, String email, String gendar);

	//17.�����û�����ѯ����Ա��Ϣ
	User queryManagerByUserName(String username);
	
}
