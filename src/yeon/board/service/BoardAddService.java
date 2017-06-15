package yeon.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import yeon.board.action.Action;
import yeon.board.command.ActionCommand;
import yeon.board.dao.BoardDAO;
import yeon.board.model.BoardDTO;

public class BoardAddService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		ActionCommand actionCommand = new ActionCommand();
		String realFolder = "";
		String saveFolder = "./boardUpload";
		int fileSize = 5 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		boolean result = false;
		try{
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			boardDTO.setid(multipartRequest.getParameter("id"));
			boardDTO.setpassword(multipartRequest.getParameter("password"));
			boardDTO.setstudy_post_title(multipartRequest.getParameter("study_post_title"));
			System.out.println(multipartRequest.getParameter("study_post_title"));
			boardDTO.setstudy_post_contents(multipartRequest.getParameter("study_post_contents"));
			boardDTO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			result = boardDAO.boardInsert(boardDTO);
			if(result == false){
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardList.study");
			return actionCommand;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}