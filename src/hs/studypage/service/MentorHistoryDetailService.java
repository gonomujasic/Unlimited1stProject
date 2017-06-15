package hs.studypage.service;
/*
 * 20170612정해선
 * 발생오류:java.lang.NumberFormatException: For input string: “chat_index” 
 * 해결방안:폼에서 호출을 잘못하여서 바꿔줌
 * 상세내용:폼의 foreach문의 var 값이 아닌 다른이름으로 호출해서 발생한 오류
 * */

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.studypage.DAO.StudyPageDAO;
import hs.studypage.model.StudyPageDTO;

public class MentorHistoryDetailService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		StudyPageDAO studyPageDAO = new StudyPageDAO();
		StudyPageDTO studyPageDTO = new StudyPageDTO();
		List<?> studyPageList = new ArrayList<Object>();

		String mentor_id = (String) session.getAttribute("id");
		studyPageDTO.setMentor_id(mentor_id);
		studyPageList = studyPageDAO.historyInfo(studyPageDTO);
		System.out.println(studyPageList);
		if (studyPageList == null) {
			System.out.println("지난 강의 내역 상세보기 실패 ");
			return null;
		}
		System.out.println("지난 강의 내역 상세보기 성공");

		ActionCommand actionCommand = new ActionCommand();
		request.setAttribute("studyPageList", studyPageList);
		System.out.println("MentorHistoryDetailService___" + studyPageList);

		actionCommand.setRedirect(false);
		actionCommand.setPath("./MentorHistoryTeachForm.do");

		return actionCommand;
	}

}
