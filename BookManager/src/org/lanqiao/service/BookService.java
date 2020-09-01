package org.lanqiao.service;

import java.util.List;

import org.lanqiao.bean.Book;

/*
 * ����ͼ���Ľӿ�
 * 
 * 1.��ѯȫ��ͼ��:(queryAllBook())
 * 2.����ͼ��id��ѯ����ͼ��:(queryBookById(int id))
 * 3.����ͼ��id��ѯͼ���Ƿ񸶷�
 * 4.����
 * 5.vip����
 */

public interface BookService {

	// 1.��ѯȫ��ͼ��:(queryAllBook())
	List<Book> queryAllBook();

	// 2.����ͼ��id��ѯ����ͼ��:(queryBookById(int id))
	Book queryBookById(int id);
	
	//3.����ͼ��id��ѯͼ���Ƿ񸶷�
	boolean isConsumption(int bid);
	
	//4.����
	int download(int bid, int uid, String created, double money);

	//5.vip����(���ظ���ͼ��)
	int vipdownload(int bid, int uid, String created, double money);
	
	//6.���а�
	List<Book> rankingList();
	
	//7.����ͼ�����id(cid)��ѯͼ��list
	List<Book> queryBookByCid(int cid, int index, int currentProducts);
	
	//8.����ͼ�����id(cid)��ѯͼ��list
	int queryTotalProducts(int cid);

	// 9.����ͼ������(name)��ѯͼ����Ϣ
	Book queryBookByName(String bookName);
	
	//10.����ͼ��id�޸�ͼ����Ϣ
	int updateBookInfo(int bid);
	
	//11.���ͼ��
	int addBook(String name, String autor, double price, String imgsrc,
				String subscript, String created);
	
	//12.�����������ͼ��ķ�����Ϣ
	int addBookCategory(int cid, String bookname) ;
	
}
