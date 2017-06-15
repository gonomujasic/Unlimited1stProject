package Unlimited.min.Notice.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.dao.NoticeDAO;

public class NoticeListService implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeDAO noticeDAO = new NoticeDAO();
		List<?> noticeList = new ArrayList<Object>();
		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listcount = noticeDAO.getListCount();  //공지목록에 글이 몇개있는지 
		System.out.println(listcount);//찍
		noticeList = noticeDAO.getBoardList(page, limit);  //페이지에 해당하는 공지목록글들 불러오기
		int maxpage = (int) ((double) listcount / limit + 0.95);
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endpage = startpage + 10 - 1; // 리스트 페이지,,, ex) 12페이지에 있다치면 start가
											// 11 end가 20
		if (endpage > maxpage) {
			endpage = maxpage;
		}

		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("noticeList", noticeList);
		NoticeActionCommand noticeActionCommand = new NoticeActionCommand();
		noticeActionCommand.setRedirect(false);
		noticeActionCommand.setPath("./Notice/Notice_list.jsp");

		return noticeActionCommand;
	}

}
