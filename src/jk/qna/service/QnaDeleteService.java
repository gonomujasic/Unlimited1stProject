package jk.qna.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jk.qna.action.Action;
import jk.qna.command.ActionCommand;
import jk.qna.dao.QnaDAO;

public class QnaDeleteService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		boolean result = false;
		boolean usercheck = false;
		int num = Integer.parseInt(request.getParameter("qna_number"));
		QnaDAO qnaDAO = new QnaDAO();
		usercheck = qnaDAO.isQnaWriter(num, request.getParameter("qna_pass"));
		if(usercheck == false){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("location.href='./QnaList.qna';");
			out.println("</script>");
			out.close();
			return null;
		}
		result = qnaDAO.qnaDelete(num);
		if(result == false){
			System.out.println("게시판 삭제 실패");
			return null;
		}
		System.out.println("게시판 삭제 성공");
		actionCommand.setRedirect(true);
		actionCommand.setPath("./QnaList.qna");
		return actionCommand;
	}
}