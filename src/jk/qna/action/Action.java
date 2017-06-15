package jk.qna.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jk.qna.command.ActionCommand;
public interface Action {
public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
