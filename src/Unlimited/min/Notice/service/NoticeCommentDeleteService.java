package Unlimited.min.Notice.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.dao.NoticeDAO;
import Unlimited.min.Notice.model.NoticeCommentDTO;
import Unlimited.min.Notice.model.NoticeDTO;

public class NoticeCommentDeleteService implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
//		System.out.println("dkdkdkdkdkkdks");
		
		int comment_no = Integer.parseInt(request.getParameter("comment_no"));
		System.out.println(comment_no);
		
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(no);
		NoticeDAO noticeDAO = new NoticeDAO();
		NoticeDTO noticeDTO = new NoticeDTO();
//		System.out.println(no);
//		System.out.println(comment_no);
		noticeDTO = noticeDAO.getDetail(no);
		request.setAttribute("noticeDTO", noticeDTO);
		ArrayList<NoticeCommentDTO> commentList = new ArrayList<NoticeCommentDTO>();

		noticeDAO.commentDelete(comment_no);
		commentList = noticeDAO.getComment(no, 1, 10);
		request.setAttribute("commentList", commentList);
		NoticeActionCommand noticeActionCommand = new NoticeActionCommand();
		noticeActionCommand.setPath("./Notice/Notice_comment_list.jsp");
		noticeActionCommand.setRedirect(false);
		
		return noticeActionCommand;
	}

}
