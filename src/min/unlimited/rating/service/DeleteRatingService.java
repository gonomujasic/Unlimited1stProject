package min.unlimited.rating.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.unlimited.rating.dao.RatingDAO;

public class DeleteRatingService implements RatingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int reviewNumber = Integer.parseInt(request.getParameter("reviewNumber"));
		
		RatingDAO rDAO = RatingDAO.getInstance();
		rDAO.deleteRating(reviewNumber);
		
	}

}
