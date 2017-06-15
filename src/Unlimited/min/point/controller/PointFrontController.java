package Unlimited.min.point.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.point.action.PointAction;
import Unlimited.min.point.command.PointActionCommand;
import Unlimited.min.point.service.GetBuyHistoryService;
import Unlimited.min.point.service.GetCrHistoryService;
import Unlimited.min.point.service.GetPointHistoryService;
import Unlimited.min.point.service.GetRefundRequestService;
import Unlimited.min.point.service.GetSellHistoryService;
import Unlimited.min.point.service.PointChargeService;
import Unlimited.min.point.service.PointExchangeService;
import Unlimited.min.point.service.PointRefundRequestCheckService;
import Unlimited.min.point.service.PointRefundRequestService;

/**
 * Servlet implementation class PointFrontController
 */
@WebServlet("*.Point")
public class PointFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());
		PointActionCommand pointActionCommand = null;
		PointAction pointAction = null;

		if (pathURL.equals("/PointLogin.Point")) {
//			System.out.println(request.getParameter("id"));
//			pointAction = new PointLoginService();
			try {
				pointActionCommand = pointAction.execute(request, response);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // 간단히 pointlogin에서 point_main에서 아이디랑 포인트 값만 세션에 담아서 point_main.jsp으로

		else if (pathURL.equals("/GetPointHistoryService.Point")) {
			pointAction = new GetPointHistoryService();
			try {
				pointActionCommand = pointAction.execute(request, response);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} // 모든 멤버의 포인트내역이 있는 db에서 세션 id에 해당하는 포인트 내역 조회
		else if (pathURL.equals("/PointChargeForm.Point")) {
			pointActionCommand = new PointActionCommand();
			pointActionCommand.setPath("./point/point_charge_form.jsp");
			pointActionCommand.setRedirect(false);

		} // 
		else if (pathURL.equals("/PointChargeImport.Point")) {
//			System.out.println("da");
//			System.out.println(request.getParameter("amount"));
			pointActionCommand = new PointActionCommand();
			pointActionCommand.setPath("./point/point_charge_import.jsp");
			pointActionCommand.setRedirect(false);
			
		} // 
		else if (pathURL.equals("/PointAdmin.Point")) {
//			System.out.println("da");
//			System.out.println(request.getParameter("amount"));
			pointActionCommand = new PointActionCommand();
			pointActionCommand.setPath("./point/point_admin.jsp");
			pointActionCommand.setRedirect(false);
			
		} // 
		else if (pathURL.equals("/PointMain.Point")) {
			pointActionCommand = new PointActionCommand();
			pointActionCommand.setPath("./point/point_main.jsp");
			pointActionCommand.setRedirect(false);
			
		} // 모든 멤버의 포인트내역이 있는 db에서 세션 id에 해당하는 포인트 내역 조회
		else if (pathURL.equals("/PointChargeService.Point")) {
			pointAction = new PointChargeService();
			try {
				pointActionCommand = pointAction.execute(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		else if (pathURL.equals("/PointRefundForm.Point")) {
			pointActionCommand = new PointActionCommand();
			pointActionCommand.setPath("./point/point_refund_form.jsp");
			pointActionCommand.setRedirect(false);
		} 
		else if (pathURL.equals("/PointRefundRequestCheckService.Point")) {
//			System.out.println("ㅁㄴㅇ");
			pointAction = new PointRefundRequestCheckService();
			try {
				pointActionCommand = pointAction.execute(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		
		else if (pathURL.equals("/PointRefundRequestService.Point")) {
//			System.out.println("ㅁㄴㅇ");
			pointAction = new PointRefundRequestService();
			try {
				pointActionCommand = pointAction.execute(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		
		
		
		else if (pathURL.equals("/AdminGetRefundRequestService.Point")) {
//			System.out.println("ㅁㄴㅇ");
			pointAction = new GetRefundRequestService();
			try {
				pointActionCommand = pointAction.execute(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		else if (pathURL.equals("/AdminGetPointHistoryForm.Point")) {
			System.out.println("ad");
			pointActionCommand = new PointActionCommand();
			pointActionCommand.setPath("./point/point_admin_history.jsp");
			pointActionCommand.setRedirect(false);
		} 
		
		else if (pathURL.equals("/GetSellHistoryService.Point")) {
			pointAction = new GetSellHistoryService();
			try {
				pointActionCommand = pointAction.execute(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if (pathURL.equals("/GetBuyHistoryService.Point")) {
//			System.out.println("asd");
			pointAction = new GetBuyHistoryService();
			try {
				pointActionCommand = pointAction.execute(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if (pathURL.equals("/GetCrHistoryService.Point")) {
//			System.out.println("asd");
			pointAction = new GetCrHistoryService();
			try {
				pointActionCommand = pointAction.execute(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if (pathURL.equals("/PointExchangeService.Point")) {
			System.out.println("asd");
			pointAction = new PointExchangeService();
			try {
				pointActionCommand = pointAction.execute(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		

		// if (pathURL.equals("/PointChargeIMPORT.point")) {
		// pointActionCommand = new PointActionCommand();
		// pointActionCommand.setPath("./point/point_charge_form.jsp");
		// pointActionCommand.setRedirect(false);
		// try {
		// pointActionCommand = pointAction.execute(request, response);
		//
		// } catch (Exception e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
		// } // 모든 멤버의 포인트내역이 있는 db에서 세션 id에 해당하는 포인트 내역 조회

		if (pointActionCommand != null) {
			if (pointActionCommand.isRedirect()) {
				response.sendRedirect(pointActionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(pointActionCommand.getPath());
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
