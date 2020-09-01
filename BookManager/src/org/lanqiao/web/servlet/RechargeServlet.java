package org.lanqiao.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.service.UserService;
import org.lanqiao.serviceImpl.UserServiceImpl;

@WebServlet("/recharge")
public class RechargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//��ȡҳ�洫���Ĳ���
		
		String uuid = request.getParameter("uuid");
		//ȡ��session�ڵ�uuid��
		String sesUuid = (String) request.getSession().getAttribute("uuid");
		
		if(sesUuid == null) {
			//��ҪƵ��ˢ��
			request.setAttribute("alertinfo", "�����ĵȴ�");
			//ת������ֵҳ��
			request.getRequestDispatcher("/jsp/recharge.jsp").forward(request, response);
			return;
		}
		
		if(uuid == null) {
			//��ҪƵ��ˢ��
			request.setAttribute("alertinfo", "�����ĵȴ�");
			//ת������ֵҳ��
			request.getRequestDispatcher("/jsp/recharge.jsp").forward(request, response);
			return;
		}
		
		if(!uuid.equals(sesUuid)) {
			//��ҪƵ��ˢ��
			request.setAttribute("alertinfo", "�����ĵȴ�");
			//ת������ֵҳ��
			request.getRequestDispatcher("/jsp/recharge.jsp").forward(request, response);
			return;
		}
		
		if(uuid.equals(sesUuid)) {
			String username = request.getParameter("username");
			username = new String(username.getBytes("ISO8859-1"), "UTF-8");
			String strmoney = request.getParameter("money");
			strmoney = new String(strmoney.getBytes("ISO8859-1"), "UTF-8");
			String strUid = request.getParameter("uid");
			
			//�ַ���ת��Ϊ����
			int money = Integer.parseInt(strmoney);
			int uid = Integer.parseInt(strUid);
			
			//��ȡ��ǰϵͳʱ��
			Date currentTime = new Date();
			//�ı������ʽ���Լ���Ҫ�ĸ�ʽ��
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//�õ��ַ���ʱ��
			String created = formatter.format(currentTime);
			//�ַ���תʱ��
			//DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			//System.out.println(created);
			
//		response.getWriter().write(username);//���(���Ŀ���)
			//��ֵ����
			UserService userservice = new UserServiceImpl();
			int i = userservice.recharge(username, money, uid, created);
			if(i != 0) {
				//��ֵ�ɹ�
				request.setAttribute("alertinfo", "�ɹ���ֵ" + money + "���");
			}else {
				//��ֵʧ��
				request.setAttribute("alertinfo", "��ֵʧ��");
			}
			
			
			/*
			 * �����˾������cookie
			 */
			request.getSession().setAttribute("uuid", null);
			
			
			//ת������ֵҳ��
			request.getRequestDispatcher("/jsp/recharge.jsp").forward(request, response);
			return;
		}else {
			//��ҪƵ��ˢ��
			request.setAttribute("alertinfo", "�벻ҪƵ��ˢ��");
			//ת������ֵҳ��
			request.getRequestDispatcher("/jsp/recharge.jsp").forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
