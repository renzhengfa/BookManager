package org.lanqiao.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.User;
import org.lanqiao.service.UserService;
import org.lanqiao.serviceImpl.UserServiceImpl;

@WebServlet("/registorCheckUser")
public class RegistorCheckUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		//��ȡҳ�洫���Ĳ���
		String username = request.getParameter("username");
		username = new String(username.getBytes("ISO8859-1"), "UTF-8");
		//�ж��û�������
		if(username.length() < 4 || username.length() > 12) {
			response.getWriter().write("{\"haveUserName\":"+ 2 + "}");
			return;
		}
		//�����û�����ѯUser��Ϣ
		UserService userservice = new UserServiceImpl();
		User user = userservice.queryUserByUserName(username);
		int countUser = 0;
		if(user != null) {
			//�������,������ע��
			countUser = 1;
		}
		response.getWriter().write("{\"haveUserName\":"+ countUser + "}");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
