package yeon.board.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import yeon.board.action.Action;
import yeon.board.command.ActionCommand;
import yeon.board.dao.BoardDAO;
import yeon.board.model.BoardDTO;

public class BoardModifyService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boolean result = false;
		String realFolder = "";
		String saveFolder = "./boardUpload";
		int fileSize = 5 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try{
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			int study_post_index = Integer.parseInt(multipartRequest.getParameter("study_post_index"));
			boolean usercheck = boardDAO.isBoardWriter(study_post_index, multipartRequest.getParameter("password"));
			if(usercheck == false){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정할 권한이 없습니다.');");
				out.println("location.href='./BoardList.study';");
				out.println("</script>");
				out.close();
				return null;
			}
			boardDTO.setstudy_post_index(study_post_index);
			boardDTO.setid(multipartRequest.getParameter("id"));
			boardDTO.setstudy_post_title(multipartRequest.getParameter("study_post_title"));
			boardDTO.setstudy_post_contents(multipartRequest.getParameter("study_post_contents"));
			boardDTO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			boardDTO.setOld_file(multipartRequest.getParameter("old_file"));
			result = boardDAO.boardModify(boardDTO);
			if(result == false){
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardDetail.study?study_post_index=" + boardDTO.getstudy_post_index());
			return actionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}