package hs.mentor.service;

/*
 * 20170609 정해선
 * 개인정보 수정하기 전, db에 있던 정보를 불러들여와서 화면에 보여줌
 * */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.mentor.DAO.MentorDAO;
import hs.mentor.model.MentorDTO;

public class MentorModifyDetailService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		MentorDAO mentorDAO = new MentorDAO();
		MentorDTO mentorDTO = new MentorDTO();

		String id = (String) session.getAttribute("id");
		mentorDTO.setId(id);
		mentorDTO = mentorDAO.mentorDetailInfo(mentorDTO);
		//System.out.println("MentorModifyDetailService" + mentorDTO);

		if (mentorDTO == null) {
			System.out.println("멘토 정보 상세보기 실패");
			return null;
		}
		System.out.println("멘토 정보 상세보기 성공");
		// 수정 페이지로 이동할 때 원래 있던 정보를 보여주기 위해 사용

		ActionCommand actionCommand = new ActionCommand();
		request.setAttribute("mentorDTO", mentorDTO);
		// System.out.println("MentorModifyDetailService" + mentorDTO);

		actionCommand.setRedirect(false);
		actionCommand.setPath("./MentorModifyForm.do");

		return actionCommand;
	}

}
