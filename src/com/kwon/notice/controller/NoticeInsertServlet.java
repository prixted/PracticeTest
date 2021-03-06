package com.kwon.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kwon.notice.model.service.NoticeService;
import com.kwon.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/nInsert.no")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nTitle = request.getParameter("title");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String nContent = request.getParameter("content");
		String nDate = request.getParameter("date");

		System.out.println("날짜 전달 확인 : " + nDate);
		
		Date writeDate = null;
		
		if(nDate !="" && nDate != null) {
			// 날짜가 들어 왔을 때
			//2020-01-30 --> 2020, 1, 30
			String[] dateArr = nDate.split("-");
			int[] intArr = new int[dateArr.length];
			
			// String --> int
			for(int i=0; i<dateArr.length;i++) {
				intArr[i] = Integer.parseInt(dateArr[i]);
			}
			
			writeDate = new Date(new GregorianCalendar(
					intArr[0],intArr[1]-1,intArr[2]).getTimeInMillis());
			
		}else {
			// 날짜가 들어오지 않으면 
			writeDate = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		//   <--- 여기까지 사용자가 입력한 값을 받아서 변수에 지정한 코드 
		Notice n = new Notice();
		
		n.setnTitle(nTitle);
		n.setnUserId(userId);
		n.setnUserName(userName);
		n.setnContent(nContent);
		n.setnDate(writeDate);
		
		NoticeService ns = new NoticeService();
		
		int result = ns.insertNotice(n);
		
		
		if(result > 0) {
			response.sendRedirect("/test/selectList.no");
		} else {
			request.setAttribute("msg", "공지사항 추가 중 에러 발생!");
			request.getRequestDispatcher("views/common/error.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
