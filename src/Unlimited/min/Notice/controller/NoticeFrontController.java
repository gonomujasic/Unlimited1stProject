package Unlimited.min.Notice.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.service.NoticeAddService;
import Unlimited.min.Notice.service.NoticeCommentAddService;
import Unlimited.min.Notice.service.NoticeCommentDeleteService;
import Unlimited.min.Notice.service.NoticeCommentListService;
import Unlimited.min.Notice.service.NoticeCommentModifyForm;
import Unlimited.min.Notice.service.NoticeCommentModifyService;
import Unlimited.min.Notice.service.NoticeDeleteService;
import Unlimited.min.Notice.service.NoticeDetailService;
import Unlimited.min.Notice.service.NoticeDownloadService;
import Unlimited.min.Notice.service.NoticeListService;
import Unlimited.min.Notice.service.NoticeModifyForm;
import Unlimited.min.Notice.service.NoticeModifyService;
import Unlimited.min.Notice.service.NoticeSearchListService;

/**
 * Servlet implementation class NoticeFrontController
 */
@WebServlet("*.notice")
public class NoticeFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());
		NoticeActionCommand noticeActionCommand = null;
		NoticeAction noticeAction = null;

		if (pathURL.equals("/NoticeAddForm.notice")) {
			noticeActionCommand = new NoticeActionCommand();
			noticeActionCommand.setRedirect(false);
			noticeActionCommand.setPath("./Notice/Notice_write.jsp");
		} // 공지 작성 폼으로 이동하는
		if (pathURL.equals("/Main.notice")) {
			noticeActionCommand = new NoticeActionCommand();
			noticeActionCommand.setRedirect(false);
			noticeActionCommand.setPath("./main/main.jsp");
		} // 공지 작성 폼으로 이동하는

		else if (pathURL.equals("/NoticeAddService.notice")) {
			noticeAction = new NoticeAddService();
			try {
				noticeActionCommand = noticeAction.execute(request, response);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // 작성하고 DB에 올려주고 detail로 가주는

		else if (pathURL.equals("/NoticeListService.notice")) {
//			HttpSession session = request.getSession();
//			session.setAttribute("id", "admin");
			noticeAction = new NoticeListService();
			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // 리스트를 DB에서 불러오고 리스트jsp로 가는

		else if (pathURL.equals("/NoticeDeleteService.notice")) {
			noticeAction = new NoticeDeleteService();
			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // 쿼리스트링으로 db에서 번호에 해당하는 notice를 지우고 리스트로뙇

		else if (pathURL.equals("/NoticeDetailService.notice")) {
			noticeAction = new NoticeDetailService();
			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // 쿼리스트링으로 번호받고 해당번호의 디테일 db에서 갖고오고 detail.jsp로 뙇

		else if (pathURL.equals("/NoticeModifyForm.notice")) {
			noticeAction = new NoticeModifyForm();
			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // detail.jsp 에서 쿼리스트링으로 no받아서 db조회해서 modify.jsp에 리퀘스트어트리뷰션으로

		else if (pathURL.equals("/NoticeModifyService.notice")) {
			noticeAction = new NoticeModifyService();
			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // modify.jsp에서 파라미터들받아서 db에 넣어주고 디테일로

		else if (pathURL.equals("/NoticeDownloadService.notice")) {
			noticeAction = new NoticeDownloadService();

			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // 그냥 첨부파일 받고 제자리

		else if (pathURL.equals("/NoticeCommentAddService.notice")) {
			noticeAction = new NoticeCommentAddService();

			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // view.jsp 에서 쿼리스트링으로 글번호 받고 글번호에 대한 댓글등록하고
			// NoticeDetailService.notice?no=글번호 로 돌아감
		
		else if (pathURL.equals("/NoticeSearchListService.notice")) {
			noticeAction = new NoticeSearchListService();
			
			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // list.jsp에서 keyword keyfield 받고 db에서 찾음 찾은걸 list로 넣어서 search_list.jsp로
		
		else if (pathURL.equals("/NoticeCommentDeleteService.notice")) {
			
			
			noticeAction = new NoticeCommentDeleteService();
			
					
			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // list.jsp에서 keyword keyfield 받고 db에서 찾음 찾은걸 list로 넣어서 search_list.jsp로
		
		else if (pathURL.equals("/NoticeCommentListService.notice")) {
//			System.out.println(request.getParameter("no"));//찍
			noticeAction = new NoticeCommentListService();
			
			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // commentList에서 댓글들 받고,noticeDTO받고 셋어트리뷰션하고 comment_list.jsp로
		
		else if (pathURL.equals("/NoticeCommentModifyService.notice")) {
			System.out.println(request.getParameter("no"));//찍
			noticeAction = new NoticeCommentModifyService();
			
			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // 모디파이폼에서 받은값들을 디비에넣어주고 comment_list.jsp로
		
		else if (pathURL.equals("/NoticeCommentModifyForm.notice")) {
//			System.out.println(request.getParameter("no"));//찍
			noticeAction = new NoticeCommentModifyForm();
			try {
				noticeActionCommand = noticeAction.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // 공지번호로 댓글리스트 조회해서 갖고 commentmodify.jsp로 가서 
		
		

		if (noticeActionCommand != null) {
			if (noticeActionCommand.isRedirect()) {
				response.sendRedirect(noticeActionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(noticeActionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
