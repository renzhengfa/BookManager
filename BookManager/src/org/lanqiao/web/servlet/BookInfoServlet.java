package org.lanqiao.web.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.Book;
import org.lanqiao.service.BookService;
import org.lanqiao.serviceImpl.BookServiceImpl;

@WebServlet("/bookInfo")
public class BookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ������bid����
		String bid = request.getParameter("bid");
		//תΪ����
		int id = Integer.parseInt(bid);
		//����bid��ѯ��ͼ����Ϣת����ͼ������ҳ
		BookService bookservice = new BookServiceImpl();
		Book book = bookservice.queryBookById(id);
		request.setAttribute("book", book);
		//����һ��uuid,ȷ���û�Ƶ��ˢ��
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		request.getSession().setAttribute("bookinfouuid", uuid);
		//ת��
		request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
