package org.lanqiao.service;

/*
 * ����ͼ�����ӿ�
 * 
 * 1.���ݷ���cname��ѯ����id(int queryCidByCname(String cname))
 * 2.��ѯȫ��������Ϣ(...)
 */

public interface CategoryService {

	// 1.���ݷ���cname��ѯ����id;
	int queryCidByCname(String cname);

	// 3.����cid��ѯcname
	String queryCnameByCid(int cid);
}
