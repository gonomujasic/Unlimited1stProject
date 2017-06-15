package jk.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jk.qna.action.Action;
import jk.qna.command.ActionCommand;
import jk.qna.dao.QnaDAO;
import jk.qna.model.QnaDTO;

public class QnaReplyService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		QnaDAO qnaDAO = new QnaDAO();
		QnaDTO qnaDTO = new QnaDTO();
		int result = 0;
		String realFolder = "";
		String saveFolder = "./qnaUpload";
		int fileSize = 5 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
//		System.out.println(realFolder); 찍
		try{
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			qnaDTO.setQna_number(Integer.parseInt(multipartRequest.getParameter("qna_number")));
//			System.out.println(multipartRequest.getParameter("qna_number")); 찍
			qnaDTO.setId(multipartRequest.getParameter("id"));
			qnaDTO.setQna_pass(multipartRequest.getParameter("qna_pass"));
			qnaDTO.setQna_title(multipartRequest.getParameter("qna_title"));
			qnaDTO.setQna_contents(multipartRequest.getParameter("qna_contents"));
			qnaDTO.setAnswer_num(Integer.parseInt(multipartRequest.getParameter("answer_num")));
			qnaDTO.setAnswer_lev(Integer.parseInt(multipartRequest.getParameter("answer_lev")));
			qnaDTO.setAnswer_seq(Integer.parseInt(multipartRequest.getParameter("answer_seq")));
			qnaDTO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			result = qnaDAO.qnaReply(qnaDTO);
			if(result == 0){
				System.out.println("답변 실패");
				return null;
			}
			System.out.println("답변 성공");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./QnaDetail.qna?qna_number=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionCommand;
	}
}