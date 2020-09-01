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

@WebServlet("/deleteManager")
public class DeleteManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������������
		response.setContentType("text/html;charset=UTF-8");
		//�жϸõ�¼�����Ƿ��ǳ�������Ա
		User user = (User) request.getSession().getAttribute("managerisLogined");
		int i = user.getRid();
		UserService userservice = new UserServiceImpl();
		List<User> allManager = userservice.queryAllManager(3);
		request.setAttribute("allManager", allManager);
		if(i == 4) {
			//��ȡҳ�����
			String struid = request.getParameter("uid");
			int uid = Integer.parseInt(struid);
			//����uid��ѯuser��״̬
			User user2 = userservice.queryUserByUid(uid);
			if(user2.getStatus().equals("1")) {//����ʱ�ſ���'ɾ��'
				//�ǳ�������Ա,����uidȥ��ɾ���ù���Ա��
				int j = userservice.deleteUser(uid, "2");
				if(j != 0) {
					//ɾ���ɹ�
					request.setAttribute("alertInfo", "ɾ���ɹ�");
					request.getRequestDispatcher("/management/manageManager.jsp").forward(request, response);
				}else {
					//ɾ��ʧ��
					request.setAttribute("alertInfo", "ɾ��ʧ��");
					request.getRequestDispatcher("/management/manageManager.jsp").forward(request, response);
				}
			}else {
				//���û��ѱ�ɾ��
				request.setAttribute("alertInfo", "���û��ѱ�ɾ��");
				request.getRequestDispatcher("/management/manageManager.jsp").forward(request, response);
			}
		}else {
			//���ǳ�������Ա
//			request.setAttribute("errorInfo", "����Ȩ�޲���");
			request.setAttribute("alertInfo", "����Ȩ�޲���");
			request.getRequestDispatcher("/management/manageManager.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
