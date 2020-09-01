package org.lanqiao.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.Book;
import org.lanqiao.service.BookService;
import org.lanqiao.serviceImpl.BookServiceImpl;

@WebServlet("/showBooks")
public class ManagementShowBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡȫ��ͼ����Ϣ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡͼ����Ϣ
		BookService bookservice = new BookServiceImpl();
		List<Book> allBook = bookservice.queryAllBook();
		request.setAttribute("allBook", allBook);
		//ת��
		request.getRequestDispatcher("/management/bookManag.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
