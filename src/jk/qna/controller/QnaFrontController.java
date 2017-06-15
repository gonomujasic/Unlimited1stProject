package jk.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jk.qna.action.Action;
import jk.qna.command.ActionCommand;
import jk.qna.service.QnaAddService;
import jk.qna.service.QnaDeleteService;
import jk.qna.service.QnaDetailService;
import jk.qna.service.QnaDownloadService;
import jk.qna.service.QnaListService;
import jk.qna.service.QnaModifyDetailService;
import jk.qna.service.QnaModifyService;
import jk.qna.service.QnaReplyMoveService;
import jk.qna.service.QnaReplyService;
import jk.qna.service.QnaSearchListService;
@WebServlet("*.qna")
public class QnaFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String requestURI = request.getRequestURI();
	String contextPath = request.getContextPath();
	String pathURL = requestURI.substring(contextPath.length());
	ActionCommand actionCommand = null;
	Action action = null;
	if(pathURL.equals("/QnaWrite.qna")){
		actionCommand = new ActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./qna/qna_write.jsp");
	}
	else if(pathURL.equals("/QnaReply.qna")){
		action = new QnaReplyService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/QnaDelete.qna")){
		actionCommand = new ActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./qna/qna_delete.jsp");
	} else if(pathURL.equals("/QnaModify.qna")){
		action = new QnaModifyDetailService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/QnaAdd.qna")){
		action = new QnaAddService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/QnaReplyMove.qna")){
		action = new QnaReplyMoveService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/QnaModifyDetail.qna")){
		action = new QnaModifyService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/QnaDeleteService.qna")){
		action = new QnaDeleteService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/QnaList.qna")){
		action = new QnaListService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/QnaDetail.qna")){
		action = new QnaDetailService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/QnaSearchList.qna")){
		action = new QnaSearchListService();
		try{
			actionCommand = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} else if(pathURL.equals("/QnaDownload.qna")){
		action = new QnaDownloadService();
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