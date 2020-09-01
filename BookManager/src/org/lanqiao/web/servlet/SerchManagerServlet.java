package org.lanqiao.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.User;
import org.lanqiao.service.UserService;
import org.lanqiao.serviceImpl.UserServiceImpl;

@WebServlet("/serchManager")
public class SerchManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡҳ�洫���Ĳ���
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		UserService userservice = new UserServiceImpl();
		List<User> allManager = userservice.queryAllManager(3);
		request.setAttribute("allManager", allManager);
		// ����ͼ�黧����ѯ��Ϣ
		User user = userservice.queryManagerByUserName(username);
		request.setAttribute("manager", user);
		if(user == null) {
			request.setAttribute("alertInfo", "û�и��û�Ŷ");
		}
		request.getRequestDispatcher("/management/manageManager.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
