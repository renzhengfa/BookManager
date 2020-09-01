package org.lanqiao.serviceImpl;

/*
 * ����ͼ������
 * 
 * 1.����daoImpl�㷽����ѯȫ��ͼ��:(queryAllBook())
 * 2.����daoImpl�㷽������ͼ��id��ѯ����ͼ��:(queryBookById(int id))
 */

import java.sql.SQLException;
import java.util.List;

import org.lanqiao.bean.Book;
import org.lanqiao.dao.BookDao;
import org.lanqiao.daoImpl.BookDaoImpl;
import org.lanqiao.service.BookService;

public class BookServiceImpl implements BookService {

	// ����DaoImpl����Ҫ�Ķ���
	BookDao bookDao = new BookDaoImpl();

	// 1.����daoImpl�㷽����ѯȫ��ͼ��:
	@Override
	public List<Book> queryAllBook() {
		List<Book> list = null;
		try {
			list = bookDao.queryAllBook();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 2.����daoImpl�㷽������ͼ��id��ѯ����ͼ��:
	@Override
	public Book queryBookById(int id) {
		Book book = null;
		try {
			book = bookDao.queryBookById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	// 3.����ͼ��id��ѯͼ���Ƿ񸶷�
	@Override
	public boolean isConsumption(int bid) {
		boolean isConsumption = false;
		try {
			isConsumption = bookDao.isConsumption(bid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isConsumption;
	}

	// 4.����
	@Override
	public int download(int bid, int uid, String created, double money) {
		int i = 0;
		try {
			i = bookDao.download(bid, uid, created, money);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	// 5.VIP����
	@Override
	public int vipdownload(int bid, int uid, String created, double money) {
		int i = 0;
		try {
			i = bookDao.vipdownload(bid, uid, created, money);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	// 6.���а�
	@Override
	public List<Book> rankingList() {
		List<Book> list = null;
		try {
			list = bookDao.rankingList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 7.����ͼ�����id(cid)��ѯͼ��list
	@Override
	public List<Book> queryBookByCid(int cid, int index, int currentProducts) {
		List<Book> list = null;
		try {
			list = bookDao.queryBookByCid(cid, index, currentProducts);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 8.����ͼ�����id(cid)��ѯͼ��list
	@Override
	public int queryTotalProducts(int cid) {
		int i = 0;
		try {
			i = bookDao.queryTotalProducts(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	// 9.����ͼ������(name)��ѯͼ����Ϣ
	@Override
	public Book queryBookByName(String bookName) {
		Book book = null;
		try {
			book = bookDao.queryBookByName(bookName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	//10.����ͼ��id�޸�ͼ����Ϣ
	@Override
	public int updateBookInfo(int bid) {
		int i = 0;
		try {
			i = bookDao.updateBookInfo(bid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//11.���ͼ��
	@Override
	public int addBook(String name, String autor, double price, String imgsrc, 
			String subscript, String created) {
		int i = 0;
		try {
			i = bookDao.addBook(name, autor, price, imgsrc, subscript, created);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	//12.�����������ͼ��ķ�����Ϣ(��Ҫ�����ͼ����ܸ���ͼ�����Ʋ�ѯͼ��id)
	@Override
	public int addBookCategory(int cid, String bookname) {
		int i = 0;
		try {
			i = bookDao.addBookCategory(cid, bookname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
