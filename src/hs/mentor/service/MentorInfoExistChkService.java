package hs.mentor.service;
/*
 * 20170609 정해선
 * 멘토 회원인지 아닌지 등급번호를 이용해 판별
 * */
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;


public class MentorInfoExistChkService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		int grade_number = (int) session.getAttribute("grade_number");

		if (grade_number==2) {
			 System.out.println("멘토회원임");
			ActionCommand actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./MentorMainForm.do");
			return actionCommand;
		} else {
			 System.out.println("멘토 회원아님");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('멘토 회원이 아닙니다. 멘토 정보를 등록해주세요.');");
			writer.println(" location.href='./MentorInfoAdd.do'");
			writer.println("</script>");
			writer.flush();
			return null;
		}
	}

}
