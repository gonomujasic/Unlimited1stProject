package Unlimited.min.Notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.dao.NoticeDAO;
import Unlimited.min.Notice.model.NoticeCommentDTO;

public class NoticeCommentAddService implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		NoticeCommentDTO noticeCommentDTO = new NoticeCommentDTO();
		NoticeDAO noticeDAO = new NoticeDAO();
		noticeCommentDTO.setNo(Integer.parseInt(request.getParameter("no")));
		noticeCommentDTO.setContent(request.getParameter("content"));
		noticeCommentDTO.setWriter(request.getParameter("writer"));
		noticeDAO.commentInsert(noticeCommentDTO);
		
		NoticeActionCommand noticeActionCommand = new NoticeActionCommand();
		noticeActionCommand.setPath("./NoticeDetailService.notice?no="+request.getParameter("no"));
		noticeActionCommand.setRedirect(true);
		
		return noticeActionCommand;
	}

}
