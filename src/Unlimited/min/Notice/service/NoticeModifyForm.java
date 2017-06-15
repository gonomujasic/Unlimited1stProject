package Unlimited.min.Notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.dao.NoticeDAO;
import Unlimited.min.Notice.model.NoticeDTO;

public class NoticeModifyForm implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeDAO noticeDAO = new NoticeDAO();
		NoticeDTO noticeDTO = new NoticeDTO();
		int no = Integer.parseInt(request.getParameter("no"));
//		noticeDAO.setReadcountUpdate(no);
		noticeDTO = noticeDAO.getDetail(no);
		if (noticeDTO == null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공");
		System.out.println(noticeDTO);
		request.setAttribute("noticeDTO", noticeDTO);
		NoticeActionCommand noticeActionCommand = new NoticeActionCommand();
		noticeActionCommand.setRedirect(false);
		noticeActionCommand.setPath("./Notice/Notice_modify.jsp");
		return noticeActionCommand;

	}

}
