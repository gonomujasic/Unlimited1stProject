package min.unlimited.chatting.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

public class CheckMentorService implements ChattingService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("들어와?");

		HttpSession session = request.getSession();
		int grade_number;
		try {

			grade_number = (int) session.getAttribute("grade_number");

		} catch (Exception e) {
			grade_number = 0;
		}
		
		String answer = "!";
		System.out.println("등급번호" + grade_number);
		if (grade_number == 2) {

			answer = "@";

		}

		JSONObject jobj = new JSONObject();
		jobj.put("answer", answer);
		String jsonString = jobj.toJSONString();
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jsonString);

	}

}
