package org.lanqiao.dao;

import java.sql.SQLException;

/*
 * ����ͼ�����ӿ�
 * cid,cname
 * 1.���ݷ���cname��ѯ����id(int queryCidByCname(String cname))
 * 2.��ѯȫ��������Ϣ(...)
 * 3.����cid��ѯcname
 */

public interface CategoryDao {
	
	//1.���ݷ���cname��ѯ����id;
	int queryCidByCname(String cname) throws SQLException;
	
	//3.����cid��ѯcname
	String queryCnameByCid(int cid) throws SQLException;
	
}
