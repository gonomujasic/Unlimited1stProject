package hs.member.service;
/*
 * 20170610 정해선
 * 비밀번호 변경시 원래비밀번호를 확인 후 일치하면 변경처리
 * */
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.member.DAO.MemberDAO;
import hs.member.model.MemberDTO;

public class PwChangeService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		String id = (String) session.getAttribute("id");

		memberDTO.setId(id);
		memberDTO.setPassword(request.getParameter("password"));
		// System.out.println("PersonalChkPwService" + memberDTO);
		memberDTO = memberDAO.chkPw(memberDTO);
		// System.out.println("PersonalChkPwService" + memberDTO);

		boolean result = false;

		if (memberDTO != null) {
			//System.out.println("현재 비밀번호와 일치. 변경해주기");
			memberDTO.setPassword(request.getParameter("newPassword"));
			//System.out.println("변경하는 비밀번호" + memberDTO.getPassword());
			result = memberDAO.pwChange(memberDTO);
			if (result == false) {
				//System.out.println("비밀번호 변경 등록 실패");
				return null;
			} else {
				//System.out.println("비밀번호 변경 등록 성공");
				ActionCommand actionCommand = new ActionCommand();
				actionCommand.setRedirect(false);
				actionCommand.setPath("./ChangePwSuccessForm.do");
				return actionCommand;
			}

		} else {
			//System.out.println("현재 비밀번호와 불일치");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('현재 비밀번호가 맞지 않습니다. 다시 입력해주세요');");
			writer.println(" location.href='./ChangePwForm.do'");
			writer.println("</script>");
			writer.flush();

			return null;
		}
	}
}
