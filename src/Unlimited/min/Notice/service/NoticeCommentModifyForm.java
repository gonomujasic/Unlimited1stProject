package Unlimited.min.Notice.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.dao.NoticeDAO;
import Unlimited.min.Notice.model.NoticeCommentDTO;

public class NoticeCommentModifyForm implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		NoticeDAO noticeDAO = new NoticeDAO();
		int comment_no = Integer.parseInt(request.getParameter("comment_no"));
		int no = Integer.parseInt(request.getParameter("no"));
		//고칠댓글 번호 가져오고
		ArrayList<NoticeCommentDTO>commentList = noticeDAO.getComment(Integer.parseInt(request.getParameter("no")),1,10);
		//전체댓글 가져오고

		request.setAttribute("no", no);
		request.setAttribute("commentList", commentList);
		request.setAttribute("comment_no", comment_no);
		NoticeActionCommand noticeActionCommand=new NoticeActionCommand();
		noticeActionCommand.setPath("./Notice/Notice_comment_modify.jsp");
		noticeActionCommand.setRedirect(false);
		// getComment는 코멘트리스트 다불러오는거고 이건 댓글번호에 해당하는거만
		
		
		
		return noticeActionCommand;
	}

}
