package hs.mentor.service;

/*
 * 201470609 정해선
 * 멘토 정보 수정시 처리하는 과정
 * 발생오류: 뷰에서 입력받은 것이 null로 찍힘
 * 해결방안: MentorInfoModify.jsp <form>태그에서 enctype="multipart/form-data"를 지워줌
 * 상세내용 : enctype="multipart/form-data"사용해서 입력값들이 다 null로 나옴
 * */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;

import hs.mentor.DAO.MentorDAO;
import hs.mentor.model.MentorDTO;

public class MentorModifyService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		HttpSession session = request.getSession();
		// System.out.println("session:"+session);
		MentorDAO mentorDAO = new MentorDAO();
		MentorDTO mentorDTO = new MentorDTO();

		boolean result = false;

		try {
			String id = (String) session.getAttribute("id");

			mentorDTO.setId(id);
			mentorDTO.setNation(request.getParameter("nation"));
			mentorDTO.setEdu_language1(request.getParameter("edu_language1"));
			mentorDTO.setEdu_language2(request.getParameter("edu_language2"));
			mentorDTO.setEdu_language3(request.getParameter("edu_language3"));
			//System.out.println("MentorModifyService:" + mentorDTO);

			result = mentorDAO.mentorInfoModify(mentorDTO);
			if (result == false) {
				System.out.println("멘토 정보 수정 실패");
				return null;
			}
			System.out.println("멘토 정보 수정 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./MentorModifySuccessForm.do");
			return actionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}