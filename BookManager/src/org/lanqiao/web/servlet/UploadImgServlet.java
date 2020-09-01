package org.lanqiao.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.lanqiao.service.BookService;
import org.lanqiao.serviceImpl.BookServiceImpl;

@WebServlet("/uploadImg")
@MultipartConfig
public class UploadImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//������������
		request.setCharacterEncoding("UTF-8");
		
		//��ȡ�ļ�·��
		Part img_part = request.getPart("myImg");
		Part file_part = request.getPart("myFile");
		
		//��ȡͷ
		String img_header = img_part.getHeader("Content-Disposition");
		String file_header = file_part.getHeader("Content-Disposition");
		
		//��ȡ���Ե�����
		String[] imgarr = img_header.split("\"");
		String imgName = imgarr[imgarr.length-1];
		String[] filearr = file_header.split("\"");
		String fileName = filearr[filearr.length-1];
		
		
		
		
		//��ȡ��ͼƬ���ļ�д���ĵط�
		String imgrelPath = this.getServletContext().getRealPath("/bookes/images" + "/" + imgName);
		String filerelPath = this.getServletContext().getRealPath("/bookes/txts" + "/" + fileName);
		
		//д���ļ�
		img_part.write(imgrelPath);
		file_part.write(filerelPath);
		
		//��ȡ�������ݿ��·��
		String imgPath = "/bookes/images" + "/" + imgName;
		//String filePath = "/bookes/txts" + "/" + fileName;
		//��װ������Ϣ
		String bookname = request.getParameter("bookName").trim();
		String author = request.getParameter("author").trim();
		String strprice = request.getParameter("price").trim();
		//���۸�ת��Ϊdouble��������
		double price = Double.parseDouble(strprice);
		String type = request.getParameter("type").trim();
		//��type��Ϊ��Ӧ���͵�id
		int cid = Integer.parseInt(type);
		String subscript = request.getParameter("subscript").trim();
		//��ȡϵͳʱ��
		Date date = new Date();
		//ת��ΪYYYY-MM-dd hh:mm:ss��ʽ
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		String created = format.format(date);
		
		//����service�������ݿ��������
		BookService bookservice = new BookServiceImpl();
		int i = bookservice.addBook(bookname, author, price, imgPath, subscript, created);
		if(i != 0) {
			//ͼ����ӳɹ�
			//���ͼ�����
			int j = bookservice.addBookCategory(cid, bookname);
			if(j != 0) {
				//ͼ��ͷ��඼��ӳɹ�
				request.setAttribute("alertSuccessInfo", "ͼ����ӳɹ�");
				//ת�������ͼ��ҳ��
				request.getRequestDispatcher("/management/addBook.jsp").forward(request, response);
			}else {
				//ͼ����ӳɹ�,���Ƿ������ʧ��
				request.setAttribute("alertErrorInfo", "ͼ����ӳɹ�,���Ƿ�����Ϣ���ʧ��");
				//ת�������ͼ��ҳ��
				request.getRequestDispatcher("/management/addBook.jsp").forward(request, response);
			}
		}else {
			//ͼ�����ʧ��
			request.setAttribute("alertErrorInfo", "ͼ�����ʧ��");
			//ת�������ͼ��ҳ��
			request.getRequestDispatcher("/management/addBook.jsp").forward(request, response);
		}
		
		
		/*System.out.println(bookname);
		System.out.println(author);
		System.out.println(price);
		System.out.println(type);
		System.out.println(subscript);
		System.out.println(imgPath);
		System.out.println(filePath);*/
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}