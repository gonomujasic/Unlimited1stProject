package jk.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jk.qna.action.Action;
import jk.qna.command.ActionCommand;
import jk.qna.dao.QnaDAO;
import jk.qna.model.QnaDTO;

public class QnaAddService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaDAO qnaDAO = new QnaDAO();
		QnaDTO qnaDTO = new QnaDTO();
		ActionCommand actionCommand = new ActionCommand();
		String realFolder = "";
		String saveFolder = "./qnaUpload";
		int fileSize = 5 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		boolean result = false;
		try{
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			qnaDTO.setId(multipartRequest.getParameter("id"));
			qnaDTO.setQna_pass(multipartRequest.getParameter("qna_pass"));
			qnaDTO.setQna_title(multipartRequest.getParameter("qna_title"));
			qnaDTO.setQna_contents(multipartRequest.getParameter("qna_contents"));
			qnaDTO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			result = qnaDAO.qnaInsert(qnaDTO);
			if(result == false){
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./QnaList.qna");
			return actionCommand;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}