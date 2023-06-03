package kr.ac.green.cmds;

import javax.servlet.http.HttpServletRequest;

public interface ICmd {
	void action(HttpServletRequest request);
}
