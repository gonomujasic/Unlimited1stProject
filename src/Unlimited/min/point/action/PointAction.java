package Unlimited.min.point.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.point.command.PointActionCommand;


public interface PointAction {
	public PointActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
