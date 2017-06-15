package yeon.board.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yeon.board.action.Action;
import yeon.board.command.ActionCommand;
import yeon.board.dao.BoardDAO;

public class BoardDeleteService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		boolean result = false;
		boolean usercheck = false;
		int study_post_index = Integer.parseInt(request.getParameter("study_post_index"));
		BoardDAO boardDAO = new BoardDAO();
		usercheck = boardDAO.isBoardWriter(study_post_index, request.getParameter("password"));
		if(usercheck == false){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("location.href='./BoardList.study';");
			out.println("</script>");
			out.close();
			return null;
		}
		result = boardDAO.boardDelete(study_post_index);
		if(result == false){
			System.out.println("게시판 삭제 실패");
			return null;
		}
		System.out.println("게시판 삭제 성공");
		actionCommand.setRedirect(true);
		actionCommand.setPath("./BoardList.study");
		return actionCommand;
	}
}