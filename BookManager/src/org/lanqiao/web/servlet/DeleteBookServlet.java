package org.lanqiao.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.Book;
import org.lanqiao.bean.User;
import org.lanqiao.service.BookService;
import org.lanqiao.serviceImpl.BookServiceImpl;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//��ѯ����ͼ����ʾ��ҳ����
		BookService bookservice = new BookServiceImpl();
		List<Book> allBook = bookservice.queryAllBook();
		request.setAttribute("allBook", allBook);
		//�ж��Ƿ��ǳ�������Ա
		User manager = (User) request.getSession().getAttribute("managerisLogined");
		//��ù���Ա��rid
		int j = manager.getRid();
		if(j == 4) {//��������Ա
			//��ȡҳ�����
			String strbid = request.getParameter("bid");
			//ת��Ϊint��������
			int bid = Integer.parseInt(strbid);
			
			//�жϸ����Ƿ��Ѿ��¼�
			Book book = bookservice.queryBookById(bid);
			String status = book.getStatus();
			if(status.equals("1")) {//��δ�¼�,�����¼�
				//����bid'ȥ�޸���һ�����״̬
				int i = bookservice.updateBookInfo(bid);
				if(i != 0) {
					//�¼ܳɹ�
					request.setAttribute("alertInfo", "�¼ܳɹ�");
					request.getRequestDispatcher("/management/bookManag.jsp").forward(request, response);
				}else {
					//�¼�ʧ��
					request.setAttribute("alertInfo", "�¼�ʧ��");
					request.getRequestDispatcher("/management/bookManag.jsp").forward(request, response);
				}
			}else {//�Ѿ��¼�,�������ٴ��¼�
				//ͼ���Ѿ��¼�
				request.setAttribute("alertInfo", "��ͼ���Ѿ��¼�");
				request.getRequestDispatcher("/management/bookManag.jsp").forward(request, response);
			}
		}else {//һ�����Ա
			request.setAttribute("alertInfo", "����Ȩ�޲���");
			request.getRequestDispatcher("/management/bookManag.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
