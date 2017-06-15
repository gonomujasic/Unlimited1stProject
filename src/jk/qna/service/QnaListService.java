package jk.qna.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jk.qna.action.Action;
import jk.qna.command.ActionCommand;
import jk.qna.dao.QnaDAO;

public class QnaListService implements Action{

@Override
public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
QnaDAO qnaDAO = new QnaDAO();
List<?> qnaList = new ArrayList<Object>();
int page = 1;
int limit = 10;
if(request.getParameter("page") != null){
	page = Integer.parseInt(request.getParameter("page"));
}
int listcount = qnaDAO.getListCount();
qnaList = qnaDAO.getQnaList(page, limit);
int maxpage = (int)((double)listcount/limit + 0.95);
int startpage = (((int)((double)page/10+0.9))-1)*10+1;
int endpage = startpage + 10 - 1;
if(endpage > maxpage){
	endpage = maxpage;
}
request.setAttribute("page", page);
request.setAttribute("maxpage", maxpage);
request.setAttribute("startpage", startpage);
request.setAttribute("endpage", endpage);
request.setAttribute("listcount", listcount);
request.setAttribute("qnaList", qnaList);
ActionCommand actionCommand = new ActionCommand();
actionCommand.setRedirect(false);
actionCommand.setPath("./qna/qna_list.jsp");
return actionCommand;
}
}
