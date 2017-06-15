package hs.login.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.login.DAO.LoginDAO;
import hs.login.model.LoginDTO;

public class PwFindService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginDAO loginDAO = new LoginDAO();
		LoginDTO loginDTO = new LoginDTO();

		loginDTO.setId(request.getParameter("id"));
		loginDTO.setName(request.getParameter("name"));
		// 이메일은 입력받은 이메일 사용
		String email = request.getParameter("email");

		loginDTO = loginDAO.PwFindProcess(loginDTO);
		System.out.println("pwFindService의 loginDTO___" + loginDTO);
		if (loginDTO != null) {
			// 정보가 있으면 이메일 인증 단계로 넘어가기
			// System.out.println("id 찾기 성공");
			SendEmailService sendEmailService = new SendEmailService();
			String num = sendEmailService.RandomNum();
			System.out.println(num);
			sendEmailService.sendMail(null, email, num);
			ActionCommand actionCommand = new ActionCommand();
			session.setAttribute("num", num);
			session.setAttribute("loginDTO", loginDTO);
			System.out.println(session.getAttribute("loginDTO"));

			System.out.println(session.getAttribute("num"));
			actionCommand.setRedirect(false);
			actionCommand.setPath("./EmailChkForm.do");

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
			// writer.println("<a href=./PwFindService.do></a>");
			return null;
		}

	}
}
