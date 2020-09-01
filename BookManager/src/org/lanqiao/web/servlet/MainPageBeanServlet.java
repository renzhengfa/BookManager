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
import org.lanqiao.serviceImpl.BookServiceImpl;
import org.lanqiao.serviceImpl.PageBeanServiceImpl;

/*
 * ��ʾ��ҳ����ͼ����Ϣ(��ҳ��ʾ)
 */

@WebServlet("/mainPageBean")
public class MainPageBeanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * ��ȡ��ǰҳ��ͼ����Ϣ
		 */
		response.setContentType("text/html;charset=UTF-8");
		
		
		/*
		 * ��װ���а�����
		 * SELECT * FROM book GROUP BY counts DESC LIMIT 0,7;
		 */
		BookService bookservice = new BookServiceImpl();
		List<Book> rankingList = bookservice.rankingList();
		request.setAttribute("rankingList", rankingList);
		
		
		
		
		//��ȡҳ�����󴫻ص�ҳ����Ϣ
		String strindex = request.getParameter("pageindex");
		if(strindex == null) {
			strindex = "1";//Ĭ��Ϊ��1ҳ
		}
		//תΪint����
		int currentPageIndex = Integer.parseInt(strindex);//5.��ǰҳ����
		PageBeanServiceImpl pagebeanService = new PageBeanServiceImpl();
		int totalProducts = pagebeanService.countBook();//1.ͳ�����ݿ��ж��ٱ���
		int pages = (int) Math.ceil(1.0*totalProducts/10);//2.��ҳ��
		//�ж��Ƿ�Ϊ���һҳ(Ĭ��һҳ10����)
		int currentPageProducts = 0;//3.��ǰҳ��Ʒ��Ŀ
		if(totalProducts % 10 != 0 && currentPageIndex == pages) {
			currentPageProducts = totalProducts % 10;
		}else {
			currentPageProducts = 10;//(Ĭ��һҳ10����)
		}
		//4.���ݵ�ǰҳ��ҳ����ѯ��ǰҳ��ͼ����Ϣ
		List<Book> list = pagebeanService.queryBookByCurrentPageIndex(currentPageIndex, currentPageProducts);
		
		/*
		 * ��װ����
		 */
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPageIndex(currentPageIndex);
		pageBean.setCurrentPageProducts(currentPageProducts);
		pageBean.setList(list);
		pageBean.setPages(pages);
		pageBean.setTotalProducts(totalProducts);
		
		/*
		 * ����requestת�������ݴ��䵽index.jspҳ��
		 */
		request.setAttribute("pagebean", pageBean);
		//ת��
		request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
