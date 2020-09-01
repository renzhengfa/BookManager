package org.lanqiao.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.Book;
import org.lanqiao.bean.User;
import org.lanqiao.service.BookService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.User_consumeService;
import org.lanqiao.serviceImpl.BookServiceImpl;
import org.lanqiao.serviceImpl.UserServiceImpl;
import org.lanqiao.serviceImpl.User_consumeServiceImpl;
import org.lanqiao.utils.DownloadUtil;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		//��ȡҳ���uuid
		String uuid = request.getParameter("uuid");
		//��ȡsession���uuid
		String bookinfouuid = (String) request.getSession().getAttribute("bookinfouuid");
		if(uuid == null) {
			// ������ǰ����ҳ�ڽ���ҳ������
			request.setAttribute("errorinfo", "������ǰ����ҳ�ڽ���ҳ������");
			request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
			return;
		}
		if(bookinfouuid == null) {
			// ������ǰ����ҳ�ڽ���ҳ������
			request.setAttribute("errorinfo", "������ǰ����ҳ�ڽ���ҳ������");
			request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
			return;
		}
		if(!uuid.equals(bookinfouuid)) {
			// Ƶ��ˢ��
			request.setAttribute("errorinfo", "�벻ҪƵ��ˢ��");
			request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
			return;
		}else {
			//���֮ǰ��session
			bookinfouuid = null;
			// ��ȡ��¼��session
			User user = (User) request.getSession().getAttribute("isLogined");
			// ����ҳ�����
			String strBid = request.getParameter("bid");
			int bid = Integer.parseInt(strBid);
			// ����ͼ��id��ѯͼ����Ϣ
			BookService bookservice = new BookServiceImpl();
			Book book = bookservice.queryBookById(bid);
			String bookPath = null;
			if(book != null) {
				/*
				 * ���ͼ���·��
				 */
				String bookName = book.getName();
//			bookName = new String(bookName.getBytes("ISO8859-1"), "utf-8");
				bookPath = "/bookes/txts/" + bookName + ".txt";
				request.setAttribute("book", book);
			}
			if (user == null) {
				// ��δ��¼(Ĭ��Ϊ�οͲ���������ͼ��,���Բ鿴ͼ��)
				request.setAttribute("errorinfo", "��δ��¼,���ȵ�¼,��δע��,����ע���ڵ�¼��������.");
				
				request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
			} else {
				//userservice
				UserService userService = new UserServiceImpl();
				// �ж��Ƿ��������(statusΪ1ʱ��������,Ϊ2ʱ�¼���)
				String status = book.getStatus();
				if (status.equals("1")) {
					/*
					 * �������� 1.�ж��Ƿ�vip�û� 2.�����Ƿ�vip�ж����ش���������Ӧ����ʾ
					 */
					String vip = user.getVip();
					
					// ��ȡ��ǰʱ�估��ʽ��
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String created = format.format(date);
					
					if (vip.equals("1")) {// ��vip,�����������ߴ�����
						// ��ȡͼ���Ƿ�Ϊ��������
						boolean consumption = bookservice.isConsumption(book.getBid());
						if (consumption) {//����ͼ������
							// ��ȡͼ����
							double price = book.getPrice();
							// ����ͼ���9��
							double money = price * 0.9;
							if (user.getMoney() >= money) {// ������
								int i = bookservice.vipdownload(bid, user.getUid(), created, money);// �ж����ݿ��Ƿ�������
								int j = userService.consumption(user.getUid(), money);//�������
								
								/*
								 * 1.���ش���
								 */
								if (i != 0 && j != 0) {
									
									DownloadUtil.downLoad(bookPath, request, response);
									
									//response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
								}else {//����ʧ��???
									request.setAttribute("errorinfo", "����ʧ��.");
									request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
								}
							} else {// ����
								request.setAttribute("errorinfo", "����,���ȳ�ֵ.");
								request.setAttribute("book", book);
								request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
							}
						} else {//����ͼ������
							// ��ȡͼ����
							double price = book.getPrice();
							// �������8��
							double money = price * 0.8;
							if (user.getMoney() >= money) {// ������
								int i = bookservice.download(bid, user.getUid(), created, money);// �ж����ݿ��Ƿ�������
								int j = userService.consumption(user.getUid(), money);//�������
								
								
								/*
								 * 2.���ش���
								 */
								if (i != 0 && j != 0) {
									
									DownloadUtil.downLoad(bookPath, request, response);
									
									//response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
								}else {//����ʧ��???
									request.setAttribute("errorinfo", "����ʧ��.");
									request.setAttribute("book", book);
									request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
								}
							} else {// ����
								request.setAttribute("errorinfo", "����,���ȳ�ֵ.");
								request.setAttribute("book", book);
								request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
							}
						}
					}
					
					if (vip.equals("2")) {// ��ͨ�û�,ֻ��������5��vip���鼮
						// �ж��Ƿ�Ϊ�����鼮(true���Ǹ���,falseΪ����Ҫ���ѵ�)
						boolean isConsumption = bookservice.isConsumption(bid);
						// ��ȡ�����鼮�����ش���
						User_consumeService user_consumeService = new User_consumeServiceImpl();
						int i = user_consumeService.queryUserVipDownloadsCounts(user.getUid());
						// System.out.println(i);
						if (isConsumption) {
							if(user.getMoney() >= book.getPrice()) {//�����ڵ���
								if (i > 5) {
									// ��ת��ͼ������ҳ������ʾ(������vip�û�,Ҫ�����ظ���ͼ�����ȳ�Ϊvip�û�.(���5��������))
									request.setAttribute("errorinfo", "���ش�������5����,���ȳ�Ϊvip�����ع���.");
									request.setAttribute("book", book);
									request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
								} else {
									/*
									 * ��������
									 */
									//�����˻����
									int k = userService.consumption(user.getUid(), book.getPrice());//�������
									int j = bookservice.vipdownload(bid, user.getUid(), created, book.getPrice());
									if(j != 0 && k != 0) {
										/*
										 * 3.���ش���
										 */
										
										DownloadUtil.downLoad(bookPath, request, response);
										
										//response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
									}else {//����ʧ��???
										request.setAttribute("errorinfo", "����ʧ��.");
										request.setAttribute("book", book);
										request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
									}
								}
							}else {
								//����
								request.setAttribute("errorinfo", "����,���ȳ�ֵ.");
								request.setAttribute("book", book);
								request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
							}
						}else {
							//����Ҫ���ѵ�ͼ��
							if(user.getMoney() >= book.getPrice()) {//�����ڵ���
								int k = userService.consumption(user.getUid(), book.getPrice());//�������
								int j = bookservice.download(bid, user.getUid(), created, book.getPrice());
								if(j != 0 && k != 0) {
									
									/*
									 * 4.���ش���
									 */
									
									DownloadUtil.downLoad(bookPath, request, response);
									//response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
								}else {//����ʧ��???
									request.setAttribute("errorinfo", "����ʧ��.");
									request.setAttribute("book", book);
									request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
								}
							}else {//����
								request.setAttribute("errorinfo", "����,���ȳ�ֵ.");
								request.setAttribute("book", book);
								request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
							}
						}
					}
				} else {
					// ͼ���Ѿ��¼�
					request.setAttribute("errorinfo", "ͼ�����¼�,����������ͼ��.");
					request.setAttribute("book", book);
					request.getRequestDispatcher("/jsp/bookInfo.jsp").forward(request, response);
				}
			}
		}
		

		// response.getWriter().write(book.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
