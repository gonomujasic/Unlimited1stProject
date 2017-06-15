package Unlimited.min.point.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Unlimited.min.point.action.PointAction;
import Unlimited.min.point.command.PointActionCommand;
import Unlimited.min.point.dao.PointDAO;
import hs.login.DAO.LoginDAO;
import hs.member.DAO.MemberDAO;
import hs.member.model.MemberDTO;

public class PointRefundRequestCheckService implements PointAction {

	@Override
	public PointActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//충환테이블업데이트
		//환전요청테이블에 done를 y로 바꿔주고
		//멤버db 포인트 업데이트하고
		//관리자포인트
		
		String id = request.getParameter("id");
		double dealing_point = -(double)Integer.parseInt(request.getParameter("dealing_point"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println(no);
		System.out.println(dealing_point);
		String why = "환전";
		PointDAO pointDAO = new PointDAO();
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = memberDAO.memberDetailInfo2(id);
		
		pointDAO.updateCRtable(id, dealing_point, memberDTO.getPoint(), why);
		pointDAO.refundDone(no);
		pointDAO.chargeMbPt(dealing_point, id, memberDTO.getPoint());

		
		
		PointActionCommand pointActionCommand = new PointActionCommand();
		pointActionCommand.setPath("./AdminGetRefundRequestService.Point");
		pointActionCommand.setRedirect(true);
		
		
		return pointActionCommand;
	}

}
