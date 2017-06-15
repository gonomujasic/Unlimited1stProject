package Unlimited.min.point.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Unlimited.min.point.action.PointAction;
import Unlimited.min.point.command.PointActionCommand;
import Unlimited.min.point.dao.PointDAO;
import Unlimited.min.point.model.MemberDTO;
import Unlimited.min.point.model.PointHistoryDTO;

public class GetPointHistoryService implements PointAction {

	@Override
	public PointActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PointActionCommand pointActionCommand = new PointActionCommand();
		HttpSession session = request.getSession();
//		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
		String id = (String) session.getAttribute("id");
		PointDAO pointDAO = new PointDAO();
		ArrayList<PointHistoryDTO> pointHistoryList = pointDAO.getMbPointHistory(id);
		
		request.setAttribute("pointHistoryList", pointHistoryList);
		
		pointActionCommand.setPath("./point/point_history.jsp");
		pointActionCommand.setRedirect(false);
		
		return pointActionCommand;
	
	}

}
