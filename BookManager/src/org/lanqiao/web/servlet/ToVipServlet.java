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

@WebServlet("/toVip")
public class ToVipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ�洫���Ĳ���
		String strmoney = request.getParameter("money");
		String strUid = request.getParameter("uid");
		
		//תΪ����
		int money = Integer.parseInt(strmoney);
		int uid = Integer.parseInt(strUid);
		
		//��Ϊ��Ա
		UserService userservice = new UserServiceImpl();
		//�����û�id��ȡ���û������
		double havemuchmoney = userservice.muchMoney(uid);
		//�ж�����Ƿ����
		if(havemuchmoney >= money) {
			//��ȡ��ǰʱ����ַ�����ʽ
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String created = format.format(date);
			//1.�޸�user��״̬;2.������Ѽ�¼
			int i = userservice.toVip(money*1.0, uid, created);
			//����,�۳�user���Ӧ��Ǯ
			int j = userservice.consumption(uid, money);
			//�ж�
			if(i != 0 && j != 0) {
				//�ɹ���Ϊ��Ա
				request.setAttribute("alertInfo", "��Ϊ��Ա�ɹ�(֧���ɹ�)");
				//��ת����Ϊ��ԱҲ
				request.getRequestDispatcher("/jsp/tovip.jsp").forward(request, response);
			}else {
				//��Ϊ��Աʧ��
				request.setAttribute("alertInfo", "��Ϊ��Աʧ��(֧��ʧ��)");
				//��ת����Ϊ��ԱҲ
				request.getRequestDispatcher("/jsp/tovip.jsp").forward(request, response);
			}
		}else {
			//����
			request.setAttribute("alertInfo", "����,���ȳ�ֵ..");
			//��ת����Ϊ��ԱҲ
			request.getRequestDispatcher("/jsp/tovip.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
