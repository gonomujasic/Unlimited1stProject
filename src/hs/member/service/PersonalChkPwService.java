package hs.member.service;

/*
 * 20170608 정해선 작성 
 * 주요 기능 : 비밀번호 입력 값과 확인 
 * 발생 오류 : 입력한 비밀번호는 출력되나, 아이디가 null이 나오고, 비밀번호가 틀렸다고 나옴 
 * 해결 방안 : 세션에 저장된 아이디를 가져옴. 
 * 상세 내용 : 아이디를 확인해서 memberDAO에서 쿼리문으로 확인하는데 세션에서 저장된 아이디를 못가져왔었음. 
 * 	String id = (String) session.getAttribute("id"); 부분을 추가해줌.
 * 
 */
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;

import hs.member.DAO.MemberDAO;
import hs.member.model.MemberDTO;

public class PersonalChkPwService implements Action {

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

		if (memberDTO != null) {

			ActionCommand actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./MemberModifyDetailService.do");
			return actionCommand;
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('비밀번호가 맞지 않습니다.');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.flush();
			writer.println("<a href=./member/PersonalChkForm.jsp></a>");
			return null;
		}
	}
}
