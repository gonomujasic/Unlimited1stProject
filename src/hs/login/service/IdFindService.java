package hs.login.service;

/*
 * 20170610 정해선
 * 아이디 찾기 처리하는 서비스
 * dao에서 찾은 값을 dto에 넣어주고,view에서 보여준다
 * */

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.login.DAO.LoginDAO;
import hs.login.model.LoginDTO;

public class IdFindService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginDAO loginDAO = new LoginDAO();
		LoginDTO loginDTO = new LoginDTO();

		loginDTO.setName(request.getParameter("name"));
		loginDTO.setEmail(request.getParameter("email"));
		System.out.println("idFindService의 loginDTO___" + loginDTO);
		loginDTO = loginDAO.idFindProcess(loginDTO);
		
		if (loginDTO != null) {
			// 정보가 있으면 회원의 아이디를 보여줄것.
			//System.out.println("id 찾기 성공");
			ActionCommand actionCommand = new ActionCommand();
			request.setAttribute("loginDTO", loginDTO);
			actionCommand.setRedirect(false);
			actionCommand.setPath("./IdFindSuccess.do");
			return actionCommand;
		} else {
			// 정보가 없으면 입력된 정보가 없다는 alert 창을 띄워줄것.
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('일치하는 회원의 정보가 없습니다.');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.flush();
			//writer.println("<a href=./IdFindService.do></a>");
			return null;
		}

	}
}
