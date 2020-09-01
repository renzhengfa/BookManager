package org.lanqiao.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.User;

@WebServlet("/loginOut")
public class LoginOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡlogin��session
		User user = (User) request.getSession().getAttribute("isLogined");
		User user1 = (User) request.getSession().getAttribute("managerisLogined");
		//��ȡuser����
		if(user1 == null) {
			/*
			 * ��ͨ�û��˳���¼
			 */
			request.getSession().setAttribute("isLogined",null);
			//������ҳ
//		request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
			return;
		}
		if(user == null) {
			/*
			 * ����Ա�򳬼�����Ա�˳���¼
			 */
			//��յ�¼��session
			request.getSession().setAttribute("managerisLogined",null);
			//��ת����¼ҳ��
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
