package org.lanqiao.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.ConsumptionGson;
import org.lanqiao.service.User_consumeService;
import org.lanqiao.serviceImpl.User_consumeServiceImpl;

import com.google.gson.Gson;

@WebServlet("/financialManagement")
public class FinancialManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ȡҳ�����
		String created = request.getParameter("date");
    	response.setCharacterEncoding("utf-8");
		if(created == null || created.equals("")) {
			//ͳ��ȫ����ֵ
			User_consumeService user_consumeService = new User_consumeServiceImpl();
			double sumRecharge = user_consumeService.sumRecharge();
			//ͳ��ȫ������
			double sumConsumption = user_consumeService.sumConsumption();
			//ͳ�Ƶ�ǰ�·�ȫ����ֵ
			double sumMonthRecharge = user_consumeService.sumMonthRecharge("2018-11%");
			//ͳ�Ƶ�ǰ�·�ȫ������
			double sumMonthConsumption = user_consumeService.sumMonthConsumption("2018-11%");
			
			ConsumptionGson consumption = new ConsumptionGson();
			consumption.setSumConsumption(sumConsumption);
			consumption.setSumRecharge(sumRecharge);
			consumption.setSumMonthConsumption(sumMonthConsumption);
			consumption.setSumMonthRecharge(sumMonthRecharge);
			request.setAttribute("consumption", consumption);
			request.getRequestDispatcher("/management/financialManagement.jsp").forward(request, response);
		}else {
			String[] createdArr = created.split("-");
			StringBuffer sb = new StringBuffer();
			sb.append(createdArr[0]).append("-").append(createdArr[1]);
			created = sb.toString();
			System.out.println(created);
			//ͳ��ȫ����ֵ
			User_consumeService user_consumeService = new User_consumeServiceImpl();
			double sumRecharge = user_consumeService.sumRecharge();
			//ͳ��ȫ������
			double sumConsumption = user_consumeService.sumConsumption();
			//ͳ�Ƶ�ǰ�·�ȫ����ֵ
			double sumMonthRecharge = user_consumeService.sumMonthRecharge(created);
			//ͳ�Ƶ�ǰ�·�ȫ������
			double sumMonthConsumption = user_consumeService.sumMonthConsumption(created);
			ConsumptionGson consumption = new ConsumptionGson();
			consumption.setSumConsumption(sumConsumption);
			consumption.setSumRecharge(sumRecharge);
			consumption.setSumMonthConsumption(sumMonthConsumption);
			consumption.setSumMonthRecharge(sumMonthRecharge);
			Gson gs = new Gson();
			//��Ӧ
            response.getWriter().write(gs.toJson(consumption));
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
