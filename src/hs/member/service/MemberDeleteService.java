package hs.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.member.DAO.MemberDAO;
import hs.member.model.MemberDTO;
import hs.mentor.DAO.MentorDAO;
import hs.mentor.model.MentorDTO;

/*
 * 아직 해결 못했음 ㅠㅠㅠㅠ
 * 20170608 정해선 
 * 발생오류 : 멘토 회원 정보를 찾아서 지워주진 않고 일반회원 정보만 지워줌..
 * 해결방안 :
 * 상세내용 : 멘토회원일 경우 if문에 들어가긴하나, result2가 false로 됨
 * */
public class MemberDeleteService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		boolean result = false;
		boolean result2 = false;

		ActionCommand actionCommand = new ActionCommand();
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		MentorDAO mentorDAO = new MentorDAO();
		MentorDTO mentorDTO = new MentorDTO();

		int gradeNumber = (int) session.getAttribute("grade_number");
		String id = (String) session.getAttribute("id");
		// 멘토 회원일 경우 멘토 정보부터 지워줌.
		if (gradeNumber == 2) {
			mentorDTO.setId(id);
			result2 = mentorDAO.mentorInfoDelete(mentorDTO);
			// System.out.println("result2" + result2);
			if (result2 == false) {
				 System.out.println("멘토 정보 삭제 실패");
			} else {
				 System.out.println("멘토 정보 삭제 성공");
			}
		}
		// 멘토 회원이 아닐경우 일반 회원 정보만 삭제
		memberDTO.setId(id);
		result = memberDAO.memberInfoDelete(memberDTO);
		// System.out.println(memberDTO);
		if (result == false) {
			 System.out.println("회원 탈퇴 실패");
			return null;
		}
		 System.out.println("회원 탈퇴 성공");
		actionCommand.setRedirect(true);
		actionCommand.setPath("./MemberDeleteSuccess.do");

		return actionCommand;
	}
}
