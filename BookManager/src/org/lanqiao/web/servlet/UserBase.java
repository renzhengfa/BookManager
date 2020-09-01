package org.lanqiao.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * �û��Ļ���
 */

public class UserBase extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//�����������
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//��ȡ��ǰ������ֽ����ļ�
		Class<? extends UserBase> clazz = UserServlet.class;
		//��ȡ����
		String methodname = req.getParameter("methodname");
		try {
			Method method = clazz.getMethod(methodname, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(clazz.newInstance(), req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
