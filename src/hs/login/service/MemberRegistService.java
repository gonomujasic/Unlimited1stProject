package hs.login.service;

/*
 * 20170608 정해선 작성
 * 회원 가입폼에서 입력을 받아서 서비스를 처리하는 클래스
 * 발생오류 : id에서 널값이 나옴
 * 해결방안:loginDTO.setId(request.getParameter("id"));->loginDTO.setId(multipartRequest.getParameter("id"));
 * 상세내용:multipartRequest를 사용하게 되면 request를 무력화시킴. 
 * */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import hs.common.action.Action;
import hs.common.command.ActionCommand;
import hs.login.DAO.LoginDAO;
import hs.login.model.LoginDTO;

public class MemberRegistService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginDAO loginDAO = new LoginDAO();
		LoginDTO loginDTO = new LoginDTO();
		ActionCommand actionCommand = new ActionCommand();
		String realFolder = "";
		String saveFolder = "./memberPicture";
		int fileSize = 5 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		boolean result = false;
		try {
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());
			loginDTO.setId(multipartRequest.getParameter("id"));
			//System.out.println(multipartRequest.getParameter("id"));
			loginDTO.setPassword(multipartRequest.getParameter("password"));
			loginDTO.setName(multipartRequest.getParameter("name"));
			loginDTO.setNickName(multipartRequest.getParameter("nickName"));
			loginDTO.setBirth(multipartRequest.getParameter("birth"));
			loginDTO.setHp(multipartRequest.getParameter("hp"));
			loginDTO.setEmail(multipartRequest.getParameter("email"));
			loginDTO.setGender(multipartRequest.getParameter("gender"));
			loginDTO.setProfilePicture(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));


			result = loginDAO.memberRegist(loginDTO);
			//System.out.println(loginDTO);
			if (result == false) {
				System.out.println("회원가입 실패");
				return null;
			}
			System.out.println("회원 등록 성공");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./MemberRegistSuccess.do");
			return actionCommand;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
