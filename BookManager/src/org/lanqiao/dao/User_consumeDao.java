package org.lanqiao.dao;

import java.sql.SQLException;
import java.util.List;

import org.lanqiao.bean.User_consume;

/*
 * �����û����ѱ�user_consume�Ľӿ�
 * id,uid(�û�id),created(ʱ��),bid(ͼ��id),
 * vip(1��2��),xc(1��ֵ2����),money
 * 
 * 1.�����û�id��ѯ�����û������Ѽ�¼:(List<User_consume> queryUser_consumeByUid(int uid))
 * 2.�������Ѽ�¼:(int addUser_consume(int uid,String created,int bid,String vip,String xc,double money))
 * 3.��ѯ�����û���vip��������(�����û�id)
 */

public interface User_consumeDao {

	//1.�����û�id��ѯ�����û������Ѽ�¼:
	List<User_consume> queryUser_consumeByUid(int uid) throws SQLException;
	
	//2.�������Ѽ�¼:
	int addUser_consume(int uid,String created,int bid,String vip,String xc,double money) throws SQLException;
	
	//3.��ѯ�����û���vip��������(�����û�id)
	int queryUserVipDownloadsCounts(int uid) throws SQLException;
	
	//4.ͳ�������û�һ���µ������ܽ��
	double sumMonthConsumption(String created) throws SQLException;

	//5.ͳ�������û�һ���µĳ�ֵ�ܽ��
	double sumMonthRecharge(String created) throws SQLException;
	
	//6.ͳ�������û��������ܽ��
	double sumConsumption() throws SQLException;
	
	//7.ͳ�������û��ĳ�ֵ�ܽ��
	double sumRecharge() throws SQLException;
	
}
