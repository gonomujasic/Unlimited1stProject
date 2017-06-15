package Unlimited.min.point.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Unlimited.min.point.action.PointAction;
import Unlimited.min.point.command.PointActionCommand;
import Unlimited.min.point.dao.PointDAO;
import Unlimited.min.point.model.MemberDTO;

public class PointRefundRequestService implements PointAction {

	@Override
	public PointActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 딜링포인트, 리펀드뱅크받고, 리펀트계좌받고 pointrefundrequest에업 데이트해준다.
		
		
		int dealing_point = Integer.parseInt(request.getParameter("dealing_point"));
		String refund_bank = request.getParameter("refund_bank");
		String refund_accountant = request.getParameter("refund_accountant");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
//		System.out.println("포인트환전서비스에서 찍음" + memberDTO);
		PointDAO pointDAO = new PointDAO();
		
		pointDAO.insertRefundRequest(dealing_point, refund_bank, refund_accountant, id);
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('환전이 요청되었습니다.')");
		out.println("</script>");
		PointActionCommand pointActionCommand = new PointActionCommand();
		pointActionCommand.setPath("./PointMain.Point");
		pointActionCommand.setRedirect(true);
		
		return pointActionCommand;
	}
	
	
	
	

}
