package org.lanqiao.serviceImpl;

/*
 * ����ͼ���������
 * 
 * 1.���ݷ���cname��ѯ����id(int queryCidByCname(String cname))
 * 2.��ѯȫ��������Ϣ(...)
 */

import java.sql.SQLException;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.daoImpl.CategoryDaoImpl;
import org.lanqiao.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	//����DaoImpl����Ҫ�Ķ���
	CategoryDao categoryDao = new CategoryDaoImpl();
	
	//1.���ݷ���cname��ѯ����id
	@Override
	public int queryCidByCname(String cname) {
		int i = 0;
		try {
			i = categoryDao.queryCidByCname(cname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	//3.����cid��ѯcname
	@Override
	public String queryCnameByCid(int cid) {
		String cname = null;
		try {
			cname = categoryDao.queryCnameByCid(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cname;
	}

}
