package hs.login.service;

/*
 * 20170610 정해선
 * 비밀번호 변경시 원래비밀번호를 확인 후 일치하면 변경처리
 * 발생오류:Cannot forward after response has been committed
 * 해결방안:response 삭제
 * 상세내용:response를 사용하게 되면
 * */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.login.DAO.LoginDAO;
import hs.login.model.LoginDTO;

public class inputNewPwService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginDAO loginDAO = new LoginDAO();
		LoginDTO loginDTO = (LoginDTO) session.getAttribute("loginDTO");

		System.out.println(session.getAttribute("loginDTO"));
		String id = loginDTO.getId();
		loginDTO.setPassword(request.getParameter("password"));
		System.out.println("inputNewPwService" + loginDTO);
		loginDTO = loginDAO.setPw(loginDTO);

		if (loginDTO != null) {
			System.out.println("변경 완료 새로 로그인해!");
			session.invalidate();
			//System.out.println("초기화 후 세션__"+session.getAttribute("loginDTO"));
			ActionCommand actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./LoginForm.do");
			return actionCommand;

		}
		return null;
	}
}
