package Unlimited.min.Notice.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.dao.NoticeDAO;
import Unlimited.min.Notice.model.NoticeCommentDTO;
import Unlimited.min.Notice.model.NoticeDTO;

public class NoticeDetailService implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeDAO noticeDAO = new NoticeDAO();
		NoticeDTO noticeDTO = new NoticeDTO();
		int no = Integer.parseInt(request.getParameter("no"));
		noticeDAO.setReadcountUpdate(no);
		noticeDTO = noticeDAO.getDetail(no);
		if (noticeDTO == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
//		ArrayList<NoticeCommentDTO> commentList = new ArrayList<NoticeCommentDTO>();
//		commentList=noticeDAO.getComment(no, 1, 10);
		request.setAttribute("noticeDTO", noticeDTO);
//		request.setAttribute("commentList", commentList);
		NoticeActionCommand noticeActionCommand = new NoticeActionCommand();
		noticeActionCommand.setRedirect(false);
		noticeActionCommand.setPath("./Notice/Notice_view.jsp");
		return noticeActionCommand;
	}
}
