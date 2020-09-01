package org.lanqiao.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.Book;
import org.lanqiao.bean.PageBean;
import org.lanqiao.service.BookService;
import org.lanqiao.serviceImpl.BookServiceImpl;
import org.lanqiao.serviceImpl.PageBeanServiceImpl;

@WebFilter("/jsp/index.jsp")
public class VisiteIndexPage implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//��ô���ȥ��pagebean����
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		PageBean pagebean = (PageBean)req.getAttribute("pagebean");
		
		
		/*
		 * ��װ���а�����
		 * SELECT * FROM book GROUP BY counts DESC LIMIT 0,4;
		 */
		BookService bookservice = new BookServiceImpl();
		List<Book> rankingList = bookservice.rankingList();
		req.setAttribute("rankingList", rankingList);
		/*
		 * ���pagebeanΪ�մ���û���������
		 * ��Ҫ��ȡ����
		 */
		if(pagebean == null) {
			//��ȡ���ݵĲ���
			/*
			 * ��ȡ��ǰҳ��ͼ����Ϣ
			 */
			res.setContentType("text/html;charset=UTF-8");
			//��ȡҳ�����󴫻ص�ҳ����Ϣ
			String strindex = req.getParameter("pageindex");
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
			req.setAttribute("pagebean", pageBean);
		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
