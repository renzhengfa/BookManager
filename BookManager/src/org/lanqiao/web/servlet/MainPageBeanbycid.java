package org.lanqiao.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.Book;
import org.lanqiao.bean.PageBean;
import org.lanqiao.service.BookService;
import org.lanqiao.service.CategoryService;
import org.lanqiao.serviceImpl.BookServiceImpl;
import org.lanqiao.serviceImpl.CategoryServiceImpl;

@WebServlet("/mainPageBeanbycid")
public class MainPageBeanbycid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		/*
		 * ��ȡ����ͼ������
		 */
		String strcid = request.getParameter("cid");
		int cid = 1;
		if(strcid != null) {
			cid = Integer.parseInt(strcid);
		}
		request.setAttribute("cid", cid);
		/*
		 * ��ȡҳ����
		 */
		String indexstr = request.getParameter("index");
		if(indexstr == null) {
			indexstr = "1";//Ĭ�ϵ�һҳ
		}
		int index = Integer.parseInt(indexstr);
		
		//��������id��ѯ��������
		CategoryService categoryservice = new CategoryServiceImpl();
		String cname = categoryservice.queryCnameByCid(cid);
		request.setAttribute("cname", cname);//ͼ������
		
		//����cid,index��ѯ��ǰҳͼ��
		BookService bookservice = new BookServiceImpl();
		//�����װ��pagebean
		//����ҳ����ȡͼ������
		int totalproducts = bookservice.queryTotalProducts(cid);
		int pages = totalproducts / 10;
		if (totalproducts % 10 != 0) {
			pages = totalproducts / 10 + 1;//ҳ��
		}
		
		int currentProducts = 10;//Ĭ��һҳ10����
		if(index == pages && totalproducts % 10 != 0) {
			currentProducts = totalproducts % 10;
		}
		List<Book> currentbooklist = bookservice.queryBookByCid(cid, index, currentProducts);
		
		PageBean pagebean = new PageBean();
		pagebean.setCurrentPageIndex(index);
		pagebean.setList(currentbooklist);
		pagebean.setPages(pages);
		pagebean.setCurrentPageProducts(currentProducts);
		pagebean.setTotalProducts(totalproducts);
		
		request.setAttribute("pagebean", pagebean);
		
		/*
		 * ��װ���а�����
		 * SELECT * FROM book GROUP BY counts DESC LIMIT 0,7;
		 */
		List<Book> rankingList = bookservice.rankingList();
		request.setAttribute("rankingList", rankingList);
		
		
		//ת��
		request.getRequestDispatcher("/jsp/indexbycid.jsp").forward(request, response);
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
