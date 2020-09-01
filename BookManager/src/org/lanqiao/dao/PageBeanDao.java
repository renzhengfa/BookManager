package org.lanqiao.dao;

import java.sql.SQLException;
import java.util.List;

import org.lanqiao.bean.Book;

/*
 * ��ҳʱ�������ݿ�Ľӿ�
 * 1.���ݵ�ǰҳ��ѯ��ǰҳ��ͼ����Ϣ(Ĭ��һҳΪ10����):(queryBookByCurrentPageIndex(int curentPageIndex))
 * 2.��ѯ����ͼ�鱾��:(int countBook())
 */

public interface PageBeanDao {
	
	//1.���ݵ�ǰҳ��ѯ��ǰҳ��ͼ����Ϣ
	List<Book> queryBookByCurrentPageIndex(int curentPageIndex, int currentPageProducts) throws SQLException;
	
	//2.��ѯ����ͼ�鱾��:
	int countBook() throws SQLException;
	
}
