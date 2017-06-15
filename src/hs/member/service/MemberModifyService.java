package hs.member.service;

/*
 * 20170608 정해선 작성
 * 회원 정보 수정시 처리하는 서비스
 * 발생오류:java.lang.classcastexception: memberDTO와 loginDTO의 형변환이 안된다
 *해결방안:PersonalChkPwService 에서 	session.setAttribute("loginDTO", memberDTO);->	session.setAttribute("loginDTO", loginDTO);로 바꿈
 * 상세내용: 	LoginDTO loginDTO = (LoginDTO) session.getAttribute("loginDTO");에서 자꾸 오류가 났었는데
 * 세션에서 가져온 로그인을 사용하고 memberDTO로 저장하고서는 자꾸 loginDTO로 불러내서 형변환이 안된다는 오류가 났었음.
 * 통일시켜주기 위해 loginDTO로 바꿈
 * => 추후 세션은 필요한 값만 따로 설정을하였음.
 * 
 * 발생오류:새로 입력한 nickName 값이 널이나옴
 * 해결방안:
 * 상세내용: multipartRequest.getParameter("nickName")는 제대로 나오나, memberDTO.getNickName()는 null 이 나옴
 * 
 *  * */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import hs.common.action.Action;
import hs.common.command.ActionCommand;

import hs.member.DAO.MemberDAO;
import hs.member.model.MemberDTO;

public class MemberModifyService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionCommand actionCommand = new ActionCommand();
		HttpSession session = request.getSession();
		// System.out.println("session:"+session);
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		boolean result = false;

		// System.out.println("MemberModifyService:" + memberDTO);
		String realFolder = "";
		String saveFolder = "./memberPicture";
		int fileSize = 5 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try {

			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());
			String id = (String) session.getAttribute("id");
			memberDTO.setId(id);
			memberDTO.setName(multipartRequest.getParameter("name"));
			memberDTO.setNickName(multipartRequest.getParameter("nickName"));
			//	System.out.println("사용자 입력시 가져온 값__" + multipartRequest.getParameter("nickName"));
			//System.out.println("MemberDTO에 넣어둔 값__  : " + memberDTO.getNickName());
			memberDTO.setBirth(multipartRequest.getParameter("birth"));
			memberDTO.setHp(multipartRequest.getParameter("hp"));
			memberDTO.setEmail(multipartRequest.getParameter("email"));
			memberDTO.setGender(multipartRequest.getParameter("gender"));
			memberDTO.setProfilePicture(
					multipartRequest.getFilesystemName((String) multipartRequest.getFileNames().nextElement()));
			memberDTO.setTempFile(multipartRequest.getParameter("tempFile"));

			memberDTO.setIntroduceMySelf(multipartRequest.getParameter("introduceMySelf"));
			result = memberDAO.memberInfoModify(memberDTO);

			if (result == false) {
//				System.out.println("회원정보 수정 실패");
				return null;
			}
			session.setAttribute("name", memberDTO.getName());
			session.setAttribute("nickName", memberDTO.getNickName());
//			System.out
//					.println("수정 후 session 값 name: " + memberDTO.getName() + ", nickName: " + memberDTO.getNickName());
//			System.out.println("MemberModifyService:" + memberDTO);
//			System.out.println("회원정보 수정 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./MemberModifySuccessForm.do");
			return actionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}