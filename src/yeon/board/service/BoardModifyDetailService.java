package yeon.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yeon.board.action.Action;
import yeon.board.command.ActionCommand;
import yeon.board.dao.BoardDAO;
import yeon.board.model.BoardDTO;

public class BoardModifyDetailService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		int study_post_index = Integer.parseInt(request.getParameter("study_post_index"));
		boardDTO = boardDAO.getDetail(study_post_index);
		if(boardDTO == null){
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공");
		request.setAttribute("boardDTO", boardDTO);
		actionCommand.setRedirect(false);
		actionCommand.setPath("./board/board_modify.jsp");
		return actionCommand;
	}
}