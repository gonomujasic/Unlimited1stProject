package hs.login.service;

/*
 * 20170607 정해선
 * 아이디 중복확인 처리 서비스
 * loginDAO에서 아이디를 찾아 확인한 값을 newId에 넣어줌
 * */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.login.DAO.LoginDAO;


public class IdCheckService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LoginDAO loginDAO = new LoginDAO();

		String id = request.getParameter("id");
		// loginDAO에서 가져온 id를 newId에 할당한다.
		String newId = loginDAO.IdCheckProcess(id);
		// request객체에 이름이 newId인변수에 newId값을 할당
		request.setAttribute("newId", newId);
		ActionCommand actionCommand = new ActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./login/IdCheckForm.jsp");

		return actionCommand;
	}
}