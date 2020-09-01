package org.lanqiao.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.lanqiao.bean.Book;
import org.lanqiao.dao.PageBeanDao;
import org.lanqiao.daoImpl.PageBeanDaoImpl;
import org.lanqiao.service.PageBeanService;

/*
 * �����ҳʱ����
 * 1.���ݵ�ǰҳ��ѯ��ǰҳ��ͼ����Ϣ(Ĭ��һҳΪ10����):(queryBookByCurrentPageIndex(int curentPageIndex))
 * 2.��ѯ����ͼ�鱾��:(int countBook())
 */

public class PageBeanServiceImpl implements PageBeanService {

	//����DaoImpl����Ҫ�Ķ���
	PageBeanDao pageBeanDao = new PageBeanDaoImpl();
	
	//1.���ݵ�ǰҳ��ѯ��ǰҳ��ͼ����Ϣ(Ĭ��һҳΪ10����):
	@Override
	public List<Book> queryBookByCurrentPageIndex(int curentPageIndex, int currentPageProducts) {
		List<Book> list = null;
		try {
			list = pageBeanDao.queryBookByCurrentPageIndex(curentPageIndex, currentPageProducts);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//2.��ѯ����ͼ�鱾��:
	@Override
	public int countBook() {
		int i = 0;
		try {
			i = pageBeanDao.countBook();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
