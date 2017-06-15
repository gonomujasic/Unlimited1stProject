package min.unlimited.rating.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import min.unlimited.rating.dao.RatingDAO;
import min.unlimited.rating.dto.RatingVO;

public class GivenRatingService implements RatingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String menteeID = (String) session.getAttribute("id");
		
		ArrayList<RatingVO> givenList = new ArrayList<RatingVO>();
		givenList = RatingDAO.getInstance().givenRatingList(menteeID);
		
		request.setAttribute("givenList", givenList);
		RequestDispatcher rd = request.getRequestDispatcher("rating/givenRatingListView");
		rd.forward(request, response);
	}

}
