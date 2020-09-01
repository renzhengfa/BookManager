package org.lanqiao.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.User;

@WebFilter(urlPatterns= {"/management/updateManager.jsp","/management/addManager.jsp",
		"/management/bookManag.jsp","/management/financialManagement.jsp",
		"/management/manageManager.jsp","/management/addBook.jsp","/updateManager"
		,"/serchManager","/serchBook","/deleteManager","/financialManagement","/uploadImg"})
public class ManagementFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * Ҫ������̨����ϵͳ�����ǹ���Ա������Ȩ�޵��˵�¼�ɹ��˲ſ��Խ���
		 * 1.��ȡ��¼��user��rid(3����4���Խ���)
		 */
		//ת������
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//��ȡ��¼���˵Ľ�ɫid
		User user = (User) req.getSession().getAttribute("managerisLogined");
		if(user != null) {
			int rid = user.getRid();
			if(rid == 3 || rid == 4) {//�ǹ���Ա������Ȩ�޵��˵�¼
				chain.doFilter(req, res);
				return;
			}else {//��ͨ�û���¼,��ת����¼ҳ���¼
				req.setAttribute("errorinfo", "�����Թ���Ա��ݵ�¼");
				req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
				return;
			}
		}else {//��δ��¼
			req.setAttribute("errorinfo", "�����Թ���Ա��ݵ�¼");
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
			return;
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
