package Unlimited.min.point.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Unlimited.min.point.action.PointAction;
import Unlimited.min.point.command.PointActionCommand;
import Unlimited.min.point.dao.PointDAO;

public class GetCrHistoryService implements PointAction {

	@Override
	public PointActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
//		HttpSession session = request.getSession();
//		MemberDTO memberDTO = (MemberDTO)session.getAttribute("memberDTO");
		PointDAO pointDAO = new PointDAO();
		ArrayList<Map<String, Object>> crHistoryList = pointDAO.getCrHistory();
		
//		System.out.println(sellHistoryList);
		request.setAttribute("crHistoryList", crHistoryList);
		
		PointActionCommand pointActionCommand = new PointActionCommand();
		pointActionCommand.setPath("./point/cr_history.jsp");
		pointActionCommand.setRedirect(false);
		
		return pointActionCommand;
	
	}
}
