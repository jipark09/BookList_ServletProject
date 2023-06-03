package kr.ac.green.cmds;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.dao.BookDao;

public class ViewCmd implements ICmd {

	@Override
	public void action(HttpServletRequest request) {
		BookDao dao = BookDao.getInstance();
		Connection con = dao.connect();
		
		int num = Integer.parseInt(request.getParameter("num"));
		request.setAttribute("book", dao.getBookByNum(con, num));
		dao.disconnect(con);
	}
}
