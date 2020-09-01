package org.lanqiao.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.bean.User;
import org.lanqiao.service.UserService;
import org.lanqiao.serviceImpl.UserServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		/*request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");*/
		response.setContentType("text/html;charset=UTF-8");
		//��ȡ����
		String username = request.getParameter("username");
		username = new String(username.getBytes("ISO8859-1"), "UTF-8");
		String password = request.getParameter("password");
		password = new String(password.getBytes("ISO8859-1"), "UTF-8");
		String remenberPassword = request.getParameter("remenberPassword");
		String identifyingcode = request.getParameter("identifyingcode");
		//��ȡ��֤��� session.setAttribute("verCode", verifyCode.toLowerCase()); 
		HttpSession session = request.getSession();
		String verCode = (String) session.getAttribute("verCode");
//		System.out.println(verCode);
		
		if(identifyingcode.equals(verCode)) {//��֤����ȷ
			//��¼
			UserService userservice = new UserServiceImpl();
			User user = userservice.login(username, password);
			if(user == null) {
				/*
				 * ��¼ʧ��,�û��������벻��ȷ(û�в鵽�������)
				 * ���ص�¼ҳ�������¼(��һ�������cookie��Ϣ���Ը��û�)
				 */
				request.setAttribute("errorinfo", "��¼ʧ��,�û������������,������...");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			}else {
				//��ȡ�û��Ƿ����
				String status = user.getStatus();
				if(status.equals("1")) {
					/*
					 * ��¼�ɹ�,��תҳ��
					 * 1.����Ա�򳬼�����Ա������̨����ϵͳ
					 * 2.��ͨ�û���vip��ת��ǰ̨ϵͳ
					 * 
					 * �ж��Ƿ��ǹ���Ա:����rid�ж�
					 * (1.��ͨ�û� 2.vip 3.����Ա 4.��������Ա)
					 */
					
					request.setAttribute("errorinfo", "��¼�ɹ�...");
					
					
					if(user.getRid() == 3 || user.getRid() == 4) {
						
						
						/*
						 * �ǹ���Ա�򳬼�����Ա
						 */
						if(remenberPassword != null) {
							/*
							 * ��ס���뱻����
							 * ��Session
							 */
							request.getSession().setAttribute("remenberusername", username);
							request.getSession().setAttribute("remenberpassword", password);
						}else {
							/*
							 * ��ס����û�б�����
							 * ����Sessionֱ����ת����ҳ
							 * ���Session
							 */
							request.getSession().setAttribute("remenberusername", null);
							request.getSession().setAttribute("remenberpassword", null);
						}
						HttpSession session2 = request.getSession();//��ȡlogin��session
						session2.setAttribute("managerisLogined",null);
//					System.out.println(session2.getAttribute("isLogined"));
						session2.setAttribute("managerisLogined", user);
//					System.out.println(session2.getAttribute("isLogined"));
						//��ת����̨ҳ��
						/*
						 * ��ȡ��̨ҳ�����Ա����
						 */
						List<User> allManager = userservice.queryAllManager(3);
						request.setAttribute("allManager", allManager);
						
						request.getRequestDispatcher("/management/manageManager.jsp").forward(request, response);
						
						
						
					}else {
						
						
						/*
						 * ����ͨ�û�
						 */
						if(remenberPassword != null) {
							/*
							 * ��ס���뱻����
							 * ��Session
							 */
							request.getSession().setAttribute("remenberusername", username);
							request.getSession().setAttribute("remenberpassword", password);
						}else {
							/*
							 * ��ס����û�б�����
							 * ����Sessionֱ����ת����ҳ
							 * ���Session
							 */
							request.getSession().setAttribute("remenberusername", null);
							request.getSession().setAttribute("remenberpassword", null);
						}
						HttpSession session2 = request.getSession();//��ȡlogin��session
						session2.setAttribute("isLogined",null);
//					System.out.println(session2.getAttribute("isLogined"));
						session2.setAttribute("isLogined", user);
//					System.out.println(session2.getAttribute("isLogined"));
						//��ת����ҳ��
						response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
					}
				}else {//�û�������,(���û��Ѿ�������Աɾ��)
					request.setAttribute("errorinfo", "���û��Ѿ�������Աɾ��...");
					request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				}
			}
		}else {//��֤�����
			request.setAttribute("errorinfo", "��֤�����...");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
