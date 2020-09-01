package org.lanqiao.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.lanqiao.bean.Book;
import org.lanqiao.dao.BookDao;
import org.lanqiao.utils.JDBCUtil;

public class BookDaoImpl implements BookDao {

	// ��ȡ��ѯ����
	static QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());

	// 1.��ѯȫ��ͼ��:(queryAllBook())
	@Override
	public List<Book> queryAllBook() throws SQLException {
		return runner.query("select * from book", new BeanListHandler<Book>(Book.class));
	}

	// 2.����ͼ��id��ѯ����ͼ��:(queryBookById(int id))
	@Override
	public Book queryBookById(int id) throws SQLException {
		return runner.query("select * from book where bid = ?", new BeanHandler<Book>(Book.class), id);
	}

	// 3.����ͼ��id��ѯͼ������(�Ƿ񸶷�)
	@Override
	public boolean isConsumption(int bid) throws SQLException {
		String sql = "SELECT c.cname FROM category c, book_category bc, "
				+ "book b WHERE c.cid = bc.cid AND b.bid = bc.bid AND b.bid = ?";
		List<Object> list = runner.query(sql, new ColumnListHandler<>("cname"), bid);
		String cname = list.get(0).toString();
		if (cname.equals("����")) {
			return true;
		}
		return false;
	}

	// 4.��ͨ����
	@Override
	public int download(int bid, int uid, String created, double money) throws SQLException {
		// 1.���ش�����1
		String sql = "update book set counts = counts + 1 where bid = ?";
		int i = runner.update(sql, bid);
		// 2.���Ѽ�¼�ı�
		String sql1 = "insert into downloads values(?, ?, ?, ?)";
		Object[] params = { null, uid, bid, created };
		int j = runner.update(sql1, params);
		// 3.��user��Ĵ������1
		String sql2 = "update user set counts = counts + 1 where uid = ?";
		int k = runner.update(sql2, uid);
		// 4.user_consume���������Ѽ�¼
		String sql3 = "insert into user_consume values(?, ?, ?, ?, ?, ?, ?)";
		Object[] params1 = { null, uid, created, bid, 2, 2, money };
		int l = runner.update(sql3, params1);
		if (i != 0 && j != 0 && k != 0 && l != 0) {
			return 1;
		}
		return 0;
	}

	// 5.VIP����
	@Override
	public int vipdownload(int bid, int uid, String created, double money) throws SQLException {
		// 1.���ش�����1
		String sql = "update book set counts = counts + 1 where bid = ?";
		int i = runner.update(sql, bid);
		// 2.���Ѽ�¼�ı�
		String sql1 = "insert into downloads values(?, ?, ?, ?)";
		Object[] params = { null, uid, bid, created };
		int j = runner.update(sql1, params);
		// 3.��user��Ĵ������1
		String sql2 = "update user set counts = counts + 1 where uid = ?";
		int k = runner.update(sql2, uid);
		// 4.user_consume���������Ѽ�¼
		String sql3 = "insert into user_consume values(?, ?, ?, ?, ?, ?, ?)";
		Object[] params1 = { null, uid, created, bid, 1, 2, money };
		int l = runner.update(sql3, params1);
		if (i != 0 && j != 0 && k != 0 && l != 0) {
			return 1;
		}
		return 0;
	}

	// 6.���а�(ǰ8����)
	@Override
	public List<Book> rankingList() throws SQLException {
		String sql = "select * from book group by counts desc limit 0,7";
		List<Book> list = runner.query(sql, new BeanListHandler<>(Book.class));
		return list;
	}

	// 7.����ͼ�����id(cid)��ѯ��ǰҳͼ��list
	@Override
	public List<Book> queryBookByCid(int cid, int index, int currentProducts) throws SQLException {
		List<Book> list = null;
		String sql = "SELECT b.* FROM book b, category c, book_category bc"
				+ " WHERE b.bid = bc.bid AND c.cid = ? AND bc.cid = ? limit ?,?";
		Object[] params = { cid, cid, (index - 1) * 10, 10 };
		list = runner.query(sql, new BeanListHandler<Book>(Book.class), params);
		return list;
	}

	// 8.����ͼ�����id(cid)��ѯͼ��list
	@Override
	public int queryTotalProducts(int cid) throws SQLException {
		String sql = "SELECT b.* FROM book b, category c, book_category bc"
				+ " WHERE b.bid = bc.bid AND c.cid = ? AND bc.cid = ?";
		List<Book> list = runner.query(sql, new BeanListHandler<Book>(Book.class), cid, cid);
		return list.size();
	}

	// 9.����ͼ������(name)��ѯͼ����Ϣ
	@Override
	public Book queryBookByName(String bookName) throws SQLException {
		Book book = null;
		String sql = "select * from book where name = ?";
		book = runner.query(sql, new BeanHandler<Book>(Book.class), bookName);
		return book;
	}

	//10.����ͼ��id�޸�ͼ����Ϣ
	@Override
	public int updateBookInfo(int bid) throws SQLException {
		String sql = "update book set status = '2' where bid = ?";
		int i = runner.update(sql, bid);
		return i;
	}

	//11.���ͼ��
	@Override
	public int addBook(String name, String author, double price, String imgsrc,
			String subscript, String created)
			throws SQLException {
		String sql = "insert into book values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {null, name, author, price, imgsrc, subscript, "1", 0, created};
		int i = runner.update(sql, params);
		return i;
	}
	
	//12.�����������ͼ��ķ�����Ϣ
	@Override
	public int addBookCategory(int cid, String bookname) throws SQLException {
		Book book = queryBookByName(bookname);
		//��ȡbookid
		int bid = book.getBid();
		String sql = "insert into book_category values(?,?)";
		int i = runner.update(sql, bid, cid);
		return i;
	}
}
