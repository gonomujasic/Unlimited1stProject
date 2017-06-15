package Unlimited.min.Notice.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.dao.NoticeDAO;
import Unlimited.min.Notice.model.NoticeCommentDTO;
import Unlimited.min.Notice.model.NoticeDTO;

public class NoticeCommentListService implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<NoticeCommentDTO> commentList = new ArrayList<NoticeCommentDTO>();
		NoticeDAO noticeDAO = new NoticeDAO();
		int no = Integer.parseInt(request.getParameter("no"));
		NoticeDTO noticeDTO = noticeDAO.getDetail(no);
		request.setAttribute("noticeDTO", noticeDTO);
		commentList=noticeDAO.getComment(no, 1, 10);
		request.setAttribute("commentList", commentList);
		NoticeActionCommand noticeActionCommand = new NoticeActionCommand();
		noticeActionCommand.setPath("./Notice/Notice_comment_list.jsp");
		noticeActionCommand.setRedirect(false);
		return noticeActionCommand;
	}

}
