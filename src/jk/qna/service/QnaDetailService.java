package jk.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jk.qna.action.Action;
import jk.qna.command.ActionCommand;
import jk.qna.dao.QnaDAO;
import jk.qna.model.QnaDTO;

public class QnaDetailService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnaDAO qnaDAO = new QnaDAO();
		QnaDTO qnaDTO = new QnaDTO();
		int num = Integer.parseInt(request.getParameter("qna_number"));
		qnaDAO.setQnaViewNumberUpdate(num);
		qnaDTO = qnaDAO.getDetail(num);
		if(qnaDTO == null){
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("qnaDTO", qnaDTO);
		ActionCommand actionCommand = new ActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./qna/qna_view.jsp");
		return actionCommand;
	}
}




































