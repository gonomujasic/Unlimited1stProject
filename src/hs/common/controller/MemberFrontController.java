package hs.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hs.login.service.IdCheckService;
import hs.login.service.IdFindService;
import hs.login.service.PwFindService;
import hs.login.service.inputNewPwService;
import hs.member.service.MemberDeleteService;
import hs.member.service.MemberModifyDetailService;
import hs.member.service.MemberModifyService;
import hs.member.service.PersonalChkPwService;
import hs.member.service.PwChangeService;
import hs.mentor.service.MentorAddService;
import hs.mentor.service.MentorInfoExistChkService;
import hs.mentor.service.MentorModifyDetailService;
import hs.mentor.service.MentorModifyService;
import hs.studypage.service.MemberHistoryDetailService;
import hs.studypage.service.MentorHistoryDetailService;
import hs.login.service.LoginService;
import hs.login.service.MemberRegistService;
import hs.login.service.PwFindService;
import hs.common.action.Action;
import hs.common.command.ActionCommand;

@WebServlet("*.do")
public class MemberFrontController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());
		ActionCommand actionCommand = null;
		Action action = null;

		// 로그인
		// 로그인 처리하는 부분 - 서비스가 필요한 부분
		if (pathURL.equals("/LoginService.do")) {
			action = new LoginService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 로그인 폼으로 가는 부분 - 서비스가 필요없다.
		} else if (pathURL.equals("/LoginForm.do")) {
			// System.out.println("loginForm");
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./login/LoginForm.jsp");
		}
		// 메인으로 가는 부분
		else if (pathURL.equals("/MainForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./main/main.jsp");

		}
		// 로그아웃 처리하는 부분
		else if (pathURL.equals("/LogoutForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./login/LogoutForm.jsp");
		}

		// ID/PW 찾기
		// id/pw찾는 폼으로 가는 부분- 서비스 불필요
		else if (pathURL.equals("/IdPwFind.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./login/IdPwFindForm.jsp");
		}
		// 아이디 찾기
		else if (pathURL.equals("/IdFindService.do")) {
			action = new IdFindService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // id찾기 성공시 결과 보여주는 화면- 서비스 불필요
		else if (pathURL.equals("/IdFindSuccess.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./login/IdFindSuccess.jsp");
		}

		// 이메일 인증하는 폼으로 가는 부분-서비스 불필요

		else if (pathURL.equals("/EmailChkForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./login/EmailChkForm.jsp");
		}

		// 비밀번호 찾기

		else if (pathURL.equals("/PwFindService.do")) {
			action = new PwFindService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// ID 중복검사

		}
		// 아이디 중복 검사하는 부분
		else if (pathURL.equals("/IdCheckService.do")) {
			action = new IdCheckService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 회원가입
		// 회원가입 폼으로 가는 부분-서비스 불필요
		else if (pathURL.equals("/MemberRegist.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/MemberRegistForm.jsp");
			// 회원가입 완료 폼으로 가는 부분 -서비스 불필요
		} else if (pathURL.equals("/MemberRegistSuccess.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/MemberRegistSuccess.jsp");
		} // 회원가입 처리하는 부분-서비스 필요
		else if (pathURL.equals("/MemberRegistService.do")) {
			action = new MemberRegistService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 마이페이지 폼으로 가는 부분
		else if (pathURL.equals("/MemberInfoForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/MemberInfoList.jsp");
		} // 학습페이지 폼으로 가는 부분
		else if (pathURL.equals("/StudyPage.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./studypage/studypageMain.jsp");
		}
		// 개인정보 관리 폼으로 가는 부분-서비스 불필요
		else if (pathURL.equals("/PersonalChkForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/PersonalChkForm.jsp");
			// 개인정보 관리폼에서 비밀번호 입력시 확인하는 부분
		} else if (pathURL.equals("/PersonalChkPwService.do")) {
			action = new PersonalChkPwService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // 개인정보 관리폼에 원래 저장되어 있던 정보를 불러줌.
		else if (pathURL.equals("/MemberModifyDetailService.do")) {
			action = new MemberModifyDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 개인정보 수정 폼으로 가는 부분
		else if (pathURL.equals("/MemberModifyForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/MemberInfoModify.jsp");
		}
		// 개인 정보 수정 처리하는 부분-서비스 필요
		else if (pathURL.equals("/MemberModifyService.do")) {
			action = new MemberModifyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 개인정보 수정이 완료된 후 가는 폼
		else if (pathURL.equals("/MemberModifySuccessForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/MemberInfoModifySuccess.jsp");
		}

		// 개인정보 수정-비밀번호 변경 폼으로 가는 부분
		else if (pathURL.equals("/ChangePwForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/MemberChangePw.jsp");
		}
		// 개인정보 수정-비밀번호 변경 처리하는 부분-서비스 필요
		else if (pathURL.equals("/PwChangeService.do")) {
			action = new PwChangeService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		// 새로운 비밀번호 설정 폼으로 가는 부분
		else if (pathURL.equals("/inputNewPw.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./login/inputNewPw.jsp");
		} // 새로운 비밀번호 처리하는 부분-서비스 필요
		else if (pathURL.equals("/inputNewPwService.do")) {
			action = new inputNewPwService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 개인정보 수정-비밀번호 변경완료 폼으로 가는 부분
		else if (pathURL.equals("/ChangePwSuccessForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/MemberChangePwSuccess.jsp");
		}

		// 회원 탈퇴 내용에 관한 폼으로 이동하는 부분
		else if (pathURL.equals("/MemberDeleteForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/MemberInfoDelete.jsp");

		} // 회원 탈퇴 처리하는 부분
		else if (pathURL.equals("/MemberDeleteService.do")) {
			action = new MemberDeleteService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원 탈퇴 완료후 안내사항에 관한 폼으로 이동하는 부분
		else if (pathURL.equals("/MemberDeleteSuccess.do")) {
			actionCommand = new ActionCommand();
			// 탈퇴 여부만 확인하면 되므로 false로 지정
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/MemberInfoDeleteSuccess.jsp");

		}

		// 멘토 정보 등록 여부 확인하는 부분
		else if (pathURL.equals("/MentorInfoExistChkService.do")) {
			action = new MentorInfoExistChkService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // 멘토 메인 페이지 폼으로 가는 부분
		else if (pathURL.equals("/MentorMainForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./mentor/MentorMain.jsp");

		} // 멘토 정보 입력 폼으로 가는 부분
		else if (pathURL.equals("/MentorInfoAdd.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./mentor/MentorInfoAdd.jsp");
		} // 멘토 정보 등록 처리하는 부분-서비스 필요
		else if (pathURL.equals("/MentorAddService.do")) {
			action = new MentorAddService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 멘토 정보 수정 폼에 원래 저장되어 있던 정보를 불러주기 위한 부분
		else if (pathURL.equals("/MentorModifyDetailService.do")) {
			action = new MentorModifyDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 멘토 정보수정 폼으로 가는 부분
		else if (pathURL.equals("/MentorModifyForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./mentor/MentorInfoModify.jsp");
		}
		// 멘토 정보 수정 처리하는 부분-서비스 필요
		else if (pathURL.equals("/MentorModifyService.do")) {
			action = new MentorModifyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // 멘토 정보 수정이 완료된 후 가는 폼
		else if (pathURL.equals("/MentorModifySuccessForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./mentor/MentorInfoModifySuccess.jsp");
		}

		// 멘토 지난 강의 내역 클릭시 정보를 가져와 보여줌
		else if (pathURL.equals("/MentorHistoryTeachDetail.do")) {
			action = new MentorHistoryDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 멘토 지난 강의 내역 보여주는 폼
		else if (pathURL.equals("/MentorHistoryTeachForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./studypage/historyTeachForm.jsp");
		}
		// 멘티 지난 수강 내역 클릭시 정보를 가져와 보여줌
		else if (pathURL.equals("/MemberHistoryDetail.do")) {
			action = new MemberHistoryDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 멘티 지난 수강 내역 보여주는 폼
		else if (pathURL.equals("/MemberHistoryForm.do")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./studypage/historyForm.jsp");
		}

		/*
		 * actionCommand 객체의 값이 널이 아니면 객체에 전송되어 온 포워딩 방식에 따라 isRedirect()=false
		 * =>dispatch방식 / isRedirect()=true =>redirect 방식으로 뷰 페이지로 지정된 URL로 포워딩을
		 * 처리한다
		 */
		if (actionCommand != null) {
			if (actionCommand.isRedirect()) {
				response.sendRedirect(actionCommand.getPath());

			} else {
				// System.out.println(request);
				// System.out.println(actionCommand.getPath());
				/*
				 * 발생 오류 : java.lang.NullPointerException/
				 * dispatcher.forward(request, response);에서 발생 해결 방안 : 경로 잘못 설정
				 * 상세 내용 : LoginForm.do을 실행하였을 때 nullpointexception 오류발생. 각각의
				 * 값들을 찍어본 결과 dispactcher에서 null이 출력되었음. 경로가 잘못설정이 되어 있어서
				 * 바꿔줌(../login/LoginForm.jsp->./login/LoginForm.jsp)
				 */
				RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
				// System.out.println(dispatcher);
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

}
