package Unlimited.min.point.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.point.action.PointAction;
import Unlimited.min.point.command.PointActionCommand;
import Unlimited.min.point.dao.PointDAO;

public class GetRefundRequestService implements PointAction {

	@Override
	public PointActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// db에서 환전요청을 불러온다.
		PointDAO pointDAO = new PointDAO();
		ArrayList<Map<String, Object>> refundRequestList = pointDAO.getRefundRequestList();
		request.setAttribute("refundRequestList", refundRequestList);

		PointActionCommand pointActionCommand = new PointActionCommand();
		pointActionCommand.setPath("./point/refund_request.jsp");
		pointActionCommand.setRedirect(false);

		return pointActionCommand;
	}

}
