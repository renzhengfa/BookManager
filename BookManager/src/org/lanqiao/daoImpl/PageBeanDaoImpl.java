package org.lanqiao.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.lanqiao.bean.Book;
import org.lanqiao.dao.PageBeanDao;
import org.lanqiao.utils.JDBCUtil;

public class PageBeanDaoImpl implements PageBeanDao {

	//��ȡ��ѯ����
	static QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
	
	//1.���ݵ�ǰҳ��ѯ��ǰҳ��ͼ����Ϣ(Ĭ��һҳΪ10����)
	@Override
	public List<Book> queryBookByCurrentPageIndex(int curentPageIndex,int currentPageProducts) throws SQLException {
		String sql = "select * from book LIMIT ?, ?";//�����һҳ��0��ʼ��ѯ,�����[((������1)*10)-1]��ʼ��ѯ
		Object[] params = {curentPageIndex == 1 ? 0 : 10*(curentPageIndex - 1), currentPageProducts};
		List<Book> list = runner.query(sql, new BeanListHandler<Book>(Book.class), params);
		return list;
	}

	//2.��ѯ����ͼ�鱾��:
	@Override
	public int countBook() throws SQLException {
		String sql = "select count(*) from book";
		Long i = (Long)runner.query(sql, new ScalarHandler<>());
		return i.intValue();
	}

}
