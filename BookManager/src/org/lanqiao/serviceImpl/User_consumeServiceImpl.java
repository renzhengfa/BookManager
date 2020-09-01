package org.lanqiao.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.lanqiao.bean.User_consume;
import org.lanqiao.dao.User_consumeDao;
import org.lanqiao.daoImpl.User_consumeDaoImpl;
import org.lanqiao.service.User_consumeService;

/*
 * �����û����ѱ�user_consume����
 * id,uid(�û�id),created(ʱ��),bid(ͼ��id),
 * vip(1��2��),xc(1��ֵ2����),money
 * 
 * 1.�����û�id��ѯ�����û������Ѽ�¼:(List<User_consume> queryUser_consumeByUid(int uid))
 * 2.�������Ѽ�¼:(int addUser_consume(int uid,String created,int bid,String vip,String xc,double money))
 */

public class User_consumeServiceImpl implements User_consumeService {

	//����DaoImpl����Ҫ�Ķ���
	User_consumeDao user_consumeDao = new User_consumeDaoImpl();
	
	//1.�����û�id��ѯ�����û������Ѽ�¼:
	@Override
	public List<User_consume> queryUser_consumeByUid(int uid) {
		List<User_consume> list = null;
		try {
			list = user_consumeDao.queryUser_consumeByUid(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//2.�������Ѽ�¼:
	@Override
	public int addUser_consume(int uid, String created, int bid, String vip, String xc, double money) {
		int i = 0;
		try {
			i = user_consumeDao.addUser_consume(uid, created, bid, vip, xc, money);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//3.��ѯ�����û���vip��������(�����û�id)
	@Override
	public int queryUserVipDownloadsCounts(int uid) {
		int i = 0;
		try {
			i = user_consumeDao.queryUserVipDownloadsCounts(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//4.ͳ�������û�һ���µ������ܽ��
	@Override
	public double sumMonthConsumption(String created) {
		double sum = 0;
		try {
			sum = user_consumeDao.sumMonthConsumption(created);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	//5.ͳ�������û�һ���µĳ�ֵ�ܽ��
	@Override
	public double sumMonthRecharge(String created) {
		double sum = 0;
		try {
			sum = user_consumeDao.sumMonthRecharge(created);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}
		
	//6.ͳ�������û��������ܽ��
	@Override
	public double sumConsumption() {
		double sum = 0;
		try {
			sum = user_consumeDao.sumConsumption();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	//7.ͳ�������û��ĳ�ֵ�ܽ��
	@Override
	public double sumRecharge() {
		double sum = 0;
		try {
			sum = user_consumeDao.sumRecharge();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

}
