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

@WebServlet("/updateManager")
public class UpdateManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//��ȡҳ�洫���Ĳ���
		String struid = request.getParameter("currentUid");
		int currentUid = Integer.parseInt(struid);
		
		String tel = request.getParameter("tel").trim();
		String gendar = request.getParameter("gendar").trim();
		String email = request.getParameter("email").trim();
		
		//�ж�
		//�绰�����Ƿ�ȫΪ����
		for(int i = 0; i < tel.length(); i++) {
			if(!Character.isDigit(tel.charAt(i))) {
				request.setAttribute("errorinfo", "�绰�������ȫΪ����");
				request.getRequestDispatcher("/management/updateManager.jsp").forward(request, response);
				return;
			}
		}
		if(tel.length() != 11) {
			request.setAttribute("errorinfo", "�绰���볤��Ϊ11λ");
			request.getRequestDispatcher("/management/updateManager.jsp").forward(request, response);
			//����,return
			return;
		}
		if(!(gendar.equals("��") || gendar.equals("Ů"))) {//���벻һ��
			request.setAttribute("errorinfo", "��,Ŀǰ��û������Ա���,Ϊ��������������...");
			request.getRequestDispatcher("/management/updateManager.jsp").forward(request, response);
			//����,return
			return;
		}
		
		//��ȡ��¼���˵�id
		User user = (User) request.getSession().getAttribute("managerisLogined");
		int i = user.getUid();
		if(i != currentUid) {//���Ǳ���,�������޸�
			request.setAttribute("errorinfo", "���Ǳ��˲���,�������޸���Ϣ");
			request.getRequestDispatcher("/management/updateManager.jsp").forward(request, response);
			//����,return
			return;
		}
		//�޸Ĳ���
		UserService userservice = new UserServiceImpl();
		int j = userservice.updateManager(currentUid, user.getPassword(), tel, email, gendar);
		if(j != 0) {
			//��ձ��浱ǰuserid��session
			request.getSession().setAttribute("updatemanagerBycurrentUid", null);
			
			request.setAttribute("successinfo", "�޸ĳɹ�");
			request.getRequestDispatcher("/management/updateManager.jsp").forward(request, response);
			//����,return
			return;
		}else {
			request.setAttribute("errorinfo", "�޸�ʧ��...");
			request.getRequestDispatcher("/management/updateManager.jsp").forward(request, response);
			//����,return
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
