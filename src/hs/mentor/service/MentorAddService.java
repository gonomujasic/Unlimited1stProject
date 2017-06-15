package hs.mentor.service;

import java.io.PrintWriter;

/*
 * 20170608 정해선 작성
 * 발생오류 : java.sql.SQLIntegrityConstraintViolationException: ORA-01400: NULL을 ("SCOTT"."MENTOR"."ID") 안에 삽입할 수 없습니다
 * 해결방안:mentorDTO에 넣어줘야하는데 memberDTO에 넣어줌 오타 수정
 * 상세내용: 
 * */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;

import hs.member.DAO.MemberDAO;
import hs.member.model.MemberDTO;
import hs.mentor.DAO.MentorDAO;
import hs.mentor.model.MentorDTO;

public class MentorAddService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MentorDAO mentorDAO = new MentorDAO();
		MentorDTO mentorDTO = new MentorDTO();

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		ActionCommand actionCommand = new ActionCommand();
		boolean result = false;

		try {
			// id는 세션에 있는 값을 가져오고 나머지는 입력받은 값들을 가져옴
			String id = (String) session.getAttribute("id");

			mentorDTO.setId(id);
			// System.out.println("MentorAddService ___memberDTO에서 가져온 id =
			// "+mentorDTO.getId());
			mentorDTO.setNation(request.getParameter("nation"));
			mentorDTO.setEdu_language1(request.getParameter("edu_language1"));
			mentorDTO.setEdu_language2(request.getParameter("edu_language2"));
			mentorDTO.setEdu_language3(request.getParameter("edu_language3"));

			// System.out.println("MentorAddService의 mentorDTO__" + mentorDTO);

			result = mentorDAO.mentorAddInfo(mentorDTO);

			if (result == false) {
				System.out.println("멘토 정보 등록 실패");
				return null;
			}
			memberDTO.setId(id);

			memberDAO.updateGradeNumber(memberDTO);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('멘토 정보가 등록되었습니다. 다시 로그인 해주세요.');");
			writer.println(" location.href='./LoginForm.do'");
			writer.println("</script>");
			writer.flush();

			return null;
			// session.setAttribute("grade_number",
			// memberDTO.getGrade_number());
			// grade_number = (int) session.getAttribute("grade_number");
			// System.out.println("MentorAddService의 memberDTO : " + memberDTO);
			// System.out.println("멘토 정보 등록 성공");
			// actionCommand.setRedirect(true);
			// actionCommand.setPath("./LoginForm.do");
			// return actionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
