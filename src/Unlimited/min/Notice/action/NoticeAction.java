package Unlimited.min.Notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.Notice.command.NoticeActionCommand;

public interface NoticeAction {
	public NoticeActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
