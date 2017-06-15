package jk.qna.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jk.qna.action.Action;
import jk.qna.command.ActionCommand;
import jk.qna.dao.QnaDAO;

public class QnaSearchListService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String keyword = null;
		keyword = (String)request.getParameter("keyword");
		String keyfield = null;
		keyfield = (String)request.getParameter("keyfield");
		//System.out.println(keyword + keyfield); 찍
		QnaDAO qnaDAO = new QnaDAO();
		List<?> searchQnalist = new ArrayList<Object>();
		int page = 1;
		int limit = 10;
		if(request.getParameter("page")!=null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		int searchlistcount = qnaDAO.getSearchListCount(keyword, keyfield);
		searchQnalist = qnaDAO.getSearchList(keyword, keyfield, page, limit);
		//System.out.println(searchQnalist); 찍
		int maxpage = (int)((double)searchlistcount/limit + 0.95);
		int startpage =(((int)((double)page/10+0.9))-1)*10+1;
		int endpage = startpage + 10 - 1;
		if(endpage > maxpage){
			endpage = maxpage;
		}
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("searchlistcount", searchlistcount);
		request.setAttribute("searchQnalist", searchQnalist);
		request.setAttribute("keyword", keyword);
		request.setAttribute("keyfield", keyfield);
		ActionCommand actionCommand = new ActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./qna/qna_search_list.jsp");
		return actionCommand;
	}
}