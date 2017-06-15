package Unlimited.min.point.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Unlimited.min.point.action.PointAction;
import Unlimited.min.point.command.PointActionCommand;
import Unlimited.min.point.dao.PointDAO;
import Unlimited.min.point.model.MemberDTO;

public class PointChargeService implements PointAction {

	@Override
	public PointActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PointDAO pointDAO = new PointDAO();
		MemberDTO memberDTO = null;
		//충환내역 db에 업데이트해준다.
		//멤버db를 업데이트해준다.
		//세션의 멤버DTO에 포인트가올라간 멤버DTO를 넣어준다
		//포인트액션커맨드경로를 포인트메인으로, 리다이렉트트루로 보내준다.
		
		HttpSession session = request.getSession();
//		memberDTO = (MemberDTO) session.getAttribute("memberDTO");
		String id = (String) session.getAttribute("id");
		int point = (int) session.getAttribute("point");
		int dealing_point = Integer.parseInt(request.getParameter("dealing_point"));
		int point_before = point;
		String why = "충전";
		pointDAO.updateCRtable(id, dealing_point, point_before, why);
		//환전에선에선 그대로 써서 빼주면되겠다
		
		int point_after = pointDAO.chargeMbPt(dealing_point,id,point_before);
//		System.out.println(memberDTO.getPoint());
		//환전부분에선 그냥 주면 되겠다.
//		session.invalidate();
		session.setAttribute("point", point_after);
		PointActionCommand pointActionCommand = new PointActionCommand();
		pointActionCommand.setPath("./PointMain.Point");
		pointActionCommand.setRedirect(true);
		return pointActionCommand;
	}
}
