package hs.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hs.common.command.ActionCommand;

public interface Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
