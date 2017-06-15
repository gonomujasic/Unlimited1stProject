package min.unlimited.rating.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.unlimited.rating.dao.RatingDAO;
import min.unlimited.rating.dto.RatingVO;

public class DoRatingService implements RatingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RatingVO ratingVO = new RatingVO();
		ratingVO.setMentorID(request.getParameter("mentorID"));
		ratingVO.setMenteeID(request.getParameter("menteeID"));
		ratingVO.setReviewText(request.getParameter("reviewText"));
		ratingVO.setTalkingSpeed(Integer.parseInt(request.getParameter("talkingSpeed")));
		ratingVO.setFriendliness(Integer.parseInt(request.getParameter("friendliness")));
		ratingVO.setPronunciation(Integer.parseInt(request.getParameter("pronunciation")));
		ratingVO.setWorthyOfContent(Integer.parseInt(request.getParameter("worthyOfContent")));
		ratingVO.setPleasure(Integer.parseInt(request.getParameter("pleasure")));
		
		RatingDAO.getInstance().doRating(ratingVO);
		
		RequestDispatcher rDispatcher = request.getRequestDispatcher("");
		rDispatcher.forward(request, response);
		
	}

}
