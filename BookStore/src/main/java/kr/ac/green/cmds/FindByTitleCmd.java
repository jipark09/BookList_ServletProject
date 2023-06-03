package kr.ac.green.cmds;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.dao.BookDao;

public class FindByTitleCmd implements ICmd {

	@Override
	public void action(HttpServletRequest request) {	
		BookDao dao = BookDao.getInstance();
		String findTitle = dao.toKor(request.getParameter("findTitle"));
		
		Connection con = dao.connect();
		request.setAttribute("list", dao.getBooksByTitle(con, findTitle));
		dao.disconnect(con);
		
		request.setAttribute("nextPage", "list");
	}	
}
