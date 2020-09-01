package org.lanqiao.daoImpl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.lanqiao.bean.Category;
import org.lanqiao.dao.CategoryDao;
import org.lanqiao.utils.JDBCUtil;

public class CategoryDaoImpl implements CategoryDao {

	//��ȡ��ѯ����
	static QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
	
	//1.���ݷ���cname��ѯ����id
	@Override
	public int queryCidByCname(String cname) throws SQLException {
		return runner.query("select * from category where cname = ?",
				new BeanHandler<Category>(Category.class), cname).getCid();
	}

	//3.����cid��ѯcname
	@Override
	public String queryCnameByCid(int cid) throws SQLException {
		String sql = "select * from category where cid = ?";
		Category category = runner.query(sql, new BeanHandler<Category>(Category.class), cid);
		return category.getCname();
	}
	
}
