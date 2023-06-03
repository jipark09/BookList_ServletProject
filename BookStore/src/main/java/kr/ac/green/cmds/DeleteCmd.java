package kr.ac.green.cmds;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.Book;
import kr.ac.green.MyViewResolver;
import kr.ac.green.dao.BookDao;

public class DeleteCmd implements ICmd{

	@Override
	public void action(HttpServletRequest request) {
		BookDao dao = BookDao.getInstance();
		Connection con = dao.connect();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		Book oldBook = dao.getBookByNum(con, num);
		String password = request.getParameter("pw");
		if(oldBook.getPassword().equals(password)) {
			dao.deleteBook(con, num);
			request.setAttribute("isRedirect", true);
		} else {
			request.setAttribute("book", oldBook);
			request.setAttribute("wrongPassword", "");
			request.setAttribute("nextPage", MyViewResolver.getNextPage("view"));
		}
		dao.disconnect(con);
	}
	
}
