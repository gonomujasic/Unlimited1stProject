package hs.login.service;
/*
 * 20170607 정해선 작성 주요 기능 : 발생 오류 : Cannot call sendRedirect() after the
 * response has been committed 해결 방안 : response.sendRedirect(경로)->
 * writer.println("<a href=경로></a>"); sendRedirect를 다시 불러낼 수 없다는 오류.
 * request랑 response 를 사용하면서 sendRedirect까지 사용해서 발생한 충돌 오류. sendRedirect 대신
 * 경로를 직접 설정해줬음.
 */

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.login.DAO.LoginDAO;
import hs.login.model.LoginDTO;

public class LoginService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 입력된 값을 세션에 저장한다.
		// 세션이 유지되는 동안에는 값이 있도록!
		HttpSession session = request.getSession();
		LoginDAO loginDAO = new LoginDAO();
		LoginDTO loginDTO = new LoginDTO();

		loginDTO.setId(request.getParameter("id"));
		loginDTO.setPassword(request.getParameter("password"));
		loginDTO = loginDAO.loginProcess(loginDTO);
		System.out.println("LoginService" + loginDTO);

		if (loginDTO != null) {
			// 저장된 세션이름이 id, 나중에 세션을 불러쓸때 이 이름을 사용함
			session.setAttribute("id", loginDTO.getId());
			session.setAttribute("name", loginDTO.getName());
			session.setAttribute("nickName", loginDTO.getNickName());
			session.setAttribute("point", loginDTO.getPoint());
			session.setAttribute("grade_number", loginDTO.getGrade_number());
			// System.out.println("로그인후 세션 값 확인= id: " + loginDTO.getId() + ",
			// name: " + loginDTO.getName() + ", nickName: "
			// + loginDTO.getNickName() + ", point: " + loginDTO.getPoint() + ",
			// grade_number: "
			// + loginDTO.getGrade_number());
			ActionCommand actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./main2.jsp");
			return actionCommand;
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.println("alert('아이디 혹은 비밀번호가 맞지 않습니다.');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.flush();
			writer.println("<a href=./login/LoginForm.jsp></a>");
			return null;
		}
	}
}
