package org.lanqiao.dao;

import java.sql.SQLException;
import java.util.List;

import org.lanqiao.bean.Book;

/*
 * ����ͼ���Ľӿ�
 * 
 * bid,name,author(����),price(�۸�),image(ͼƬ),content(���),
 * status(1-������2-�¼�) , counts(������) ,created(ʱ��)
 * 
 * 1.��ѯȫ��ͼ��:(queryAllBook())
 * 2.����ͼ��id��ѯ����ͼ��:(queryBookById(int id))
 * 3.����ͼ��id��ѯͼ���������(�������)
 * 4.����
 * 5.VIP����
 * 6.���а�
 * 7.����ͼ�����id(cid)��ѯ��ǰҳͼ��list
 * 8.����ͼ�����id(cid)��ѯͼ��list
 */

public interface BookDao {
	
	//1.��ѯȫ��ͼ��:(queryAllBook())
	List<Book> queryAllBook() throws SQLException;
	
	//2.����ͼ��id��ѯ����ͼ��:(queryBookById(int id))
	Book queryBookById(int bid) throws SQLException;
	
	//3.����ͼ��id��ѯͼ���������(�������)
	boolean isConsumption(int bid) throws SQLException;
	
	//4.����
	int download(int bid, int uid, String created, double money) throws SQLException;

	//5.VIP����
	int vipdownload(int bid, int uid, String created, double money) throws SQLException;
	
	//6.���а�
	List<Book> rankingList() throws SQLException;
	
	//7.����ͼ�����cid(cid)��ѯ��ǰҳͼ��list
	List<Book> queryBookByCid(int cid, int index, int currentProducts) throws SQLException;
	
	//8.����ͼ�����cid(cid)��ѯͼ��list
	int queryTotalProducts(int cid) throws SQLException;

	//9.����ͼ�����Ʋ�ѯͼ����Ϣ
	Book queryBookByName(String bookName) throws SQLException;
	
	//10.����ͼ��id�޸�ͼ����Ϣ
	int updateBookInfo(int bid) throws SQLException;
	
	//11.���ͼ��
	int addBook(String name, String autor, double price, String imgsrc,
			String subscript, String created) throws SQLException;
	
	//12.�����������ͼ��ķ�����Ϣ
	int addBookCategory(int cid, String bookname) throws SQLException;
	
}
