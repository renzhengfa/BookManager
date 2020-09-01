package org.lanqiao.service;

import java.util.List;

import org.lanqiao.bean.Book;

/*
 * �����ҳʱ�Ľӿ�
 * 1.���ݵ�ǰҳ��ѯ��ǰҳ��ͼ����Ϣ(Ĭ��һҳΪ10����):(queryBookByCurrentPageIndex(int curentPageIndex))
 * 2.��ѯ����ͼ�鱾��:(int countBook())
 */

public interface PageBeanService {

	// 1.���ݵ�ǰҳ��ѯ��ǰҳ��ͼ����Ϣ
	List<Book> queryBookByCurrentPageIndex(int curentPageIndex, int currentPageProducts);

	// 2.��ѯ����ͼ�鱾��:
	int countBook();

}
