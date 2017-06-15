package yeon.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yeon.board.action.Action;
import yeon.board.command.ActionCommand;
import yeon.board.service.BoardAddService;
import yeon.board.service.BoardDeleteService;
import yeon.board.service.BoardDetailService;
import yeon.board.service.BoardDownloadService;
import yeon.board.service.BoardListService;
import yeon.board.service.BoardModifyDetailService;
import yeon.board.service.BoardModifyService;
import yeon.board.service.BoardReplyMoveService;
import yeon.board.service.BoardReplyService;
import yeon.board.service.BoardSearchListService;
@WebServlet("*.study")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String requestURI = request.getRequestURI();
	String contextPath = request.getContextPath();
	String pathURL = requestURI.substring(contextPath.length());
	ActionCommand actionCommand = null;
	Action action = null;
	if(pathURL.equals("/BoardWrite.study")){
		actionCommand = new ActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./board/board_write.jsp");
	}
	else if(pathURL.equals("/BoardReply.study")){
		action = new BoardReplyService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/BoardDelete.study")){
		actionCommand = new ActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./board/board_delete.jsp");
	} else if(pathURL.equals("/BoardModify.study")){
		action = new BoardModifyDetailService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/BoardAdd.study")){
		action = new BoardAddService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/BoardReplyMove.study")){
		action = new BoardReplyMoveService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/BoardModifyDetail.study")){
		action = new BoardModifyService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/BoardDeleteService.study")){
		action = new BoardDeleteService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/BoardList.study")){
		action = new BoardListService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/BoardDetail.study")){
		action = new BoardDetailService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/BoardSearchList.study")){
		action = new BoardSearchListService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/BoardDownload.study")){
		action = new BoardDownloadService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	if(actionCommand != null){
		if(actionCommand.isRedirect()){
			response.sendRedirect(actionCommand.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
			dispatcher.forward(request, response);
		}
	}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	service(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	service(request, response);
	}
}