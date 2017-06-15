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


public class NoticeModifyService implements NoticeAction {

	@Override
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		NoticeActionCommand noticeActionCommand = new NoticeActionCommand();
		NoticeDAO noticeDAO = new NoticeDAO();
		NoticeDTO noticeDTO = new NoticeDTO();
		boolean result = false;
		String realFolder ="";
		String saveFolder ="./NoticeUpload";
		int fileSize = 5*1024*1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try{
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request, realFolder, fileSize,"UTF-8", new DefaultFileRenamePolicy());
			int no = Integer.parseInt(multipartRequest.getParameter("no"));
			// boolean usercheck = noticeDAO.isBoardWriter(num,
			// multipartRequest.getParameter("writer"));
			// if(usercheck == false){
			// response.setContentType("text/html;charset=UTF-8");
			// PrintWriter out = response.getWriter();
			// out.println("<script>"
			// + "alert('수정할 권한이 없습니다.');"
			// + "location.href='./BoardList.notice';"
			// + "</script>");
			// out.close();
			// return null;
			// }
			noticeDTO.setNo(no);
			noticeDTO.setWriter(multipartRequest.getParameter("writer"));
			noticeDTO.setTitle(multipartRequest.getParameter("title"));
			noticeDTO.setContent(multipartRequest.getParameter("content"));
			noticeDTO.setAttached_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			noticeDTO.setOld_file(multipartRequest.getParameter("old_file"));
			result = noticeDAO.noticeModify(noticeDTO);
			if(result == false){
				System.out.println("공지 수정 실패");
				return null;
			}
			System.out.println("공지 수정 완료");
			noticeActionCommand.setRedirect(true);
			noticeActionCommand.setPath("./NoticeDetailService.notice?no="+noticeDTO.getNo());
			//BoardDetailServcie.notice 에서 BoardDetail.notice로 고침
			return noticeActionCommand;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
