package jk.qna.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jk.qna.action.Action;
import jk.qna.command.ActionCommand;
import jk.qna.dao.QnaDAO;
import jk.qna.model.QnaDTO;

public class QnaModifyService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		QnaDAO qnaDAO = new QnaDAO();
		QnaDTO qnaDTO = new QnaDTO();
		boolean result = false;
		String realFolder = "";
		String saveFolder = "./qnaUpload";
		int fileSize = 5 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try{
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multipartRequest.getParameter("qna_number"));
			boolean usercheck = qnaDAO.isQnaWriter(num, multipartRequest.getParameter("qna_pass"));
			if(usercheck == false){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정할 권한이 없습니다.');");
				out.println("location.href='./QnaList.qna';");
				out.println("</script>");
				out.close();
				return null;
			}
			qnaDTO.setQna_number(num);
			qnaDTO.setId(multipartRequest.getParameter("id"));
			qnaDTO.setQna_title(multipartRequest.getParameter("qna_title"));
			qnaDTO.setQna_contents(multipartRequest.getParameter("qna_contents"));
			qnaDTO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			qnaDTO.setOld_file(multipartRequest.getParameter("old_file"));
			result = qnaDAO.qnaModify(qnaDTO);
			if(result == false){
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./QnaDetail.qna?qna_number=" + qnaDTO.getQna_number());
			return actionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}