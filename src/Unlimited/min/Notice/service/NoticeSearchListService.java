package Unlimited.min.Notice.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.dao.NoticeDAO;


public class NoticeSearchListService implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text; charset=UTF-8");
		String keyword = null;
		keyword = (String) request.getParameter("keyword");
		
		String keyfield = null;
		keyfield = (String) request.getParameter("keyfield");
		NoticeDAO noticeDAO = new NoticeDAO();
		List<?> searchNoticeList = new ArrayList<Object>();
		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int searchlistcount = noticeDAO.getSearchListCount(keyword, keyfield);
		System.out.println(keyfield + keyword);
		searchNoticeList = noticeDAO.getSearchList(keyword, keyfield, page, limit);

		int maxpage = (int) ((double) searchlistcount / limit + 0.95);
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endpage = startpage + 10 - 1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("searchlistcount", searchlistcount);
		request.setAttribute("searchNoticeList", searchNoticeList);
		request.setAttribute("keyword", keyword);
		request.setAttribute("keyfield", keyfield);
		NoticeActionCommand actionCommand = new NoticeActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./Notice/Notice_search_list.jsp");
		return actionCommand;
	}

}
