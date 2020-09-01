package org.lanqiao.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.utils.VerifyCodeUtils;


/**
 * <p><b>AuthImage Description:</b> (��֤��)</p>
 * <b>DATE:</b> 2016��6��2�� ����3:53:12
 */
@WebServlet("/authImage")
public class AuthImage extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet { 
    static final long serialVersionUID = 1L; 
   
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	
    	response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg"); 
        
        String username = request.getParameter("username");
        if(username == null) {
        	username = "aaa";
        }
        //System.out.println(username);//aaa
        
        //��������ִ� 
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
        //����Ựsession 
        HttpSession session = request.getSession(); 
        //ɾ����ǰ��
        session.removeAttribute("verCode");
        //��¼��֤��
        session.setAttribute("verCode", verifyCode.toLowerCase()); 
        
        //����ͼƬ 
        int w = 100, h = 30; 
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
        
    } 
} 