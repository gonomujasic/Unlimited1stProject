package min.unlimited.chatting.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.unlimited.chatting.service.ChattingService;
import min.unlimited.chatting.service.ChattingServiceFactory;

/**
 * Servlet implementation class ChattingServlet
 */
@WebServlet("/ChattingServlet")
public class ChattingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChattingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("command:"+request.getParameter("command"));
		
		response.setContentType("text/html; charset=UTF-8");
		//Cache-Control 과 Pragma는 캐시된 문서를 사용하지 말고 매번 서버로부터 새로운 문서를 다시 전송받아 사용하도록 알리는 헤더
		response.setHeader("Cache-Control", "private");//HTTP 1.1
		response.setHeader("Pragma", "no-cache");//HTTP 1.0 프로토콜을 모르기에 둘다 써줌.
		response.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");

		ChattingServiceFactory cFactory = ChattingServiceFactory.getInstance();
		ChattingService service = cFactory.getChattingService(command);

		if (service != null) {
			service.execute(request, response);
		}

	}

}
