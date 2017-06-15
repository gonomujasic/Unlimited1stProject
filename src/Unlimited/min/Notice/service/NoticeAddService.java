package Unlimited.min.Notice.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Unlimited.min.Notice.action.NoticeAction;
import Unlimited.min.Notice.command.NoticeActionCommand;
import Unlimited.min.Notice.dao.NoticeDAO;
import Unlimited.min.Notice.model.NoticeDTO;

public class NoticeAddService implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) {
		NoticeDAO noticeDAO = new NoticeDAO();
		NoticeDTO noticeDTO = new NoticeDTO();
		NoticeActionCommand noticeActionCommand = new NoticeActionCommand();
		
		String realFolder ="";
		String saveFolder="./NoticeUpload";
		int fileSize = 5*1024*1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		int result = 0;
		try{
			MultipartRequest multipartRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			noticeDTO.setWriter(multipartRequest.getParameter("writer")); //인풋히든으로 세션아이디속성받음
			noticeDTO.setTitle(multipartRequest.getParameter("title"));
			noticeDTO.setContent(multipartRequest.getParameter("content"));
			noticeDTO.setAttached_file(multipartRequest.getParameter("attached_file"));
			
			result=noticeDAO.boardInsert(noticeDTO);
			//액션커맨드 경로를 작성한글 자세히 보기로 가기위해 boardInsert에서 작성글번호를 반환
			if(result==0){
				System.out.println("게시글 등록 실패");
				return null;
			}
			System.out.println("공지사항 등록 성공");
			noticeActionCommand.setRedirect(true);
			noticeActionCommand.setPath("./NoticeDetailService.notice?no="+result);
		
		}catch (Exception e) {
			// TODO: handle exception
		}
		return noticeActionCommand;
	}

}
