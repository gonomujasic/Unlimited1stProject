package hs.member.service;


/*
 * 20170609 정해선
 * 개인정보 수정하기 전, db에 있던 정보를 불러들여와서 화면에 보여줌
 * */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.member.DAO.MemberDAO;
import hs.member.model.MemberDTO;

public class MemberModifyDetailService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		String id=(String)session.getAttribute("id");
		memberDTO.setId(id);
		memberDTO = memberDAO.memberDetailInfo(memberDTO);
		// System.out.println("MemberModifyDetailService" + memberDTO);
		if (memberDTO == null) {
			//System.out.println("상세보기 실패");
			return null;
		}
		//System.out.println("상세보기 성공");
		// 수정 페이지로 이동할 때 원래 있던 정보를 보여주기 위해 사용

		ActionCommand actionCommand = new ActionCommand();
		request.setAttribute("memberDTO", memberDTO);
		actionCommand.setRedirect(false);
		actionCommand.setPath("./MemberModifyForm.do");

		return actionCommand;
	}

}
