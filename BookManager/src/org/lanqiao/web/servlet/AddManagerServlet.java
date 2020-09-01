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

@WebServlet("/addManager")
public class AddManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ�洫���Ĳ���
		request.setCharacterEncoding("UTF-8");
		/*
		 * ��ȡ��ǰ��¼��user����
		 * (4Ϊ��������Ա�ſ�����ӻ��޸Ĺ���Ա��Ȩ��)
		 */
		User user1 = (User) request.getSession().getAttribute("managerisLogined");
		int j = user1.getRid();
		if(j == 4) {//��������Ա
			
			//��ȡҳ�洫���Ĳ���
			String username = request.getParameter("username").trim();
			String password = request.getParameter("password").trim();
			/*
			 * ��ӹ���Ա(ֻ��һ����������Ա)
			 * �Ȳ鿴��û���ظ�������(7.�����û�����ѯ�û���Ϣ)
			 */
			UserService userservice = new UserServiceImpl();
			User user = userservice.queryUserByUserName(username);
			
			if(user == null) {
				//û������˵�ʱ��ʼ���
				int i = userservice.addManager(username, password);
				if(i != 0) {
					/*
					 * ��ӳɹ�
					 * �������ҳ����ʾ��ӳɹ�
					 */
					request.setAttribute("successInfo", "��ӳɹ�");
					request.getRequestDispatcher("/management/addManager.jsp").forward(request, response);
					return;
				}else {
					//���ʧ��
					request.setAttribute("failInfo", "���ʧ��");
					request.getRequestDispatcher("/management/addManager.jsp").forward(request, response);
					return;
				}
			}else {
				//�������
				//���ʧ��
				request.setAttribute("failInfo", "����������Ѿ�");
				request.getRequestDispatcher("/management/addManager.jsp").forward(request, response);
				return;
			}
		}else {//���ǳ�������Ա
			//Ȩ�޲���
			request.setAttribute("failInfo", "�����ǳ�������Ա,Ȩ�޲��㡣");
			request.getRequestDispatcher("/management/addManager.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
