package Unlimited.min.Notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.dao.NoticeDAO;

public class NoticeDeleteService implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) {
		NoticeActionCommand noticeActionCommand = new NoticeActionCommand();
		boolean result = false;
		int no = Integer.parseInt(request.getParameter("no"));
		NoticeDAO noticeDAO = new NoticeDAO();
		result = noticeDAO.boardDelete(no);
		if (result == false) {
			System.out.println("게시판 삭제 실패");
			return null;
		}
		System.out.println("게시판 삭제 성공");
		noticeActionCommand.setRedirect(true);
		noticeActionCommand.setPath("./NoticeListService.notice");
		return noticeActionCommand;
	}
}
