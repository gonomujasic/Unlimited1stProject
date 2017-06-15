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

public class ReceivedRatingService implements RatingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String mentorID = (String) session.getAttribute("mentorID");
		
		ArrayList<RatingVO> receivedList = new ArrayList<RatingVO>();
		RatingDAO rDao = RatingDAO.getInstance();
		receivedList = rDao.receivedRatingList(mentorID);
		int talkingSpeed =0;
		int friendliness =0;
		int pronunciation =0;
		int worthyOfConent =0;
		int pleasure =0;
		int avg = 0;
		
		for(int i = 0; i<receivedList.size();i++){
			
			talkingSpeed += receivedList.get(i).getTalkingSpeed();
			friendliness += receivedList.get(i).getFriendliness();
			pronunciation += receivedList.get(i).getPronunciation();
			worthyOfConent += receivedList.get(i).getWorthyOfContent();
			pleasure += receivedList.get(i).getPleasure();
			
		}
		
		talkingSpeed /= receivedList.size();
		friendliness /= receivedList.size();
		pronunciation /= receivedList.size();
		worthyOfConent /= receivedList.size();
		pleasure /= receivedList.size();
		avg = talkingSpeed+friendliness+pronunciation+worthyOfConent+pleasure/5;
		
		int[] individualRating = {talkingSpeed,friendliness,pronunciation,worthyOfConent,pleasure,avg}; 
		
		request.setAttribute("individaulRating", individualRating);
		request.setAttribute("receivedList", receivedList);
		
		RequestDispatcher rd = request.getRequestDispatcher("rating/receivedRatingListView");
		rd.forward(request, response);

	}

}
