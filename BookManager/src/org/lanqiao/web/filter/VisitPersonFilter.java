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
import org.lanqiao.service.UserService;
import org.lanqiao.serviceImpl.UserServiceImpl;

/*
 * ������������ҳ��Ĺ�����
 */

@WebFilter(urlPatterns= {"/jsp/personInfo.jsp", "/jsp/recharge.jsp","/toVip","/tovip.jsp"})
public class VisitPersonFilter implements Filter {

	public void destroy() {
		System.out.println("filter destory...");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//�Ƚ�request��response����������תΪHTTPServletRequest��HTTPServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//ת��
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		//��ȡ��¼��session${sessionScope.isLogined.username }
		User user = (User) req.getSession().getAttribute("isLogined");
        if(user == null){//û�е�¼
            //���sessionΪ�ձ�ʾ�û�û�е�½���ض���login.jspҳ��
            //System.out.println("request.getContextPath()=" + request.getContextPath());  
            res.sendRedirect(req.getContextPath() + "/jsp/index.jsp");
            return;
        }else {
        	//���²�ѯ���ݿ�
        	UserService userservice = new UserServiceImpl();
        	User user2 = userservice.queryUserByUserName(user.getUsername());
        	req.getSession().setAttribute("isLogined", user2);
        }
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("filter init...");
	}

}
