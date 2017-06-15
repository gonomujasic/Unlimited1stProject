package yeon.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import yeon.board.action.Action;
import yeon.board.command.ActionCommand;
import yeon.board.dao.BoardDAO;
import yeon.board.model.BoardDTO;

public class BoardReplyService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		int result = 0;
		String realFolder = "";
		String saveFolder = "./boardUpload";
		int fileSize = 5 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try{
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			boardDTO.setstudy_post_index(Integer.parseInt(multipartRequest.getParameter("study_post_index")));
			boardDTO.setid(multipartRequest.getParameter("id"));
			boardDTO.setpassword(multipartRequest.getParameter("password"));
			boardDTO.setstudy_post_title(multipartRequest.getParameter("study_post_title"));
			boardDTO.setstudy_post_contents(multipartRequest.getParameter("study_post_contents"));
			boardDTO.setAnswer_num(Integer.parseInt(multipartRequest.getParameter("answer_num")));
			boardDTO.setAnswer_lev(Integer.parseInt(multipartRequest.getParameter("answer_lev")));
			boardDTO.setAnswer_seq(Integer.parseInt(multipartRequest.getParameter("answer_seq")));
			boardDTO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			result = boardDAO.boardReply(boardDTO);
			if(result == 0){
				System.out.println("답변 실패");
				return null;
			}
			System.out.println("답변 성공");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardDetail.study?study_post_index=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionCommand;
	}
}