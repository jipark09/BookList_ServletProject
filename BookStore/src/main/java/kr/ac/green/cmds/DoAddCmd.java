package kr.ac.green.cmds;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.Book;
import kr.ac.green.dao.BookDao;

public class DoAddCmd implements ICmd {
	@Override
	public void action(HttpServletRequest request) {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String password = request.getParameter("pw");
		int price = Integer.parseInt(request.getParameter("price"));
		String publisher = request.getParameter("publisher");
		String comment = request.getParameter("comment");
		
		Book book = new Book(title, writer, password, price, publisher, comment);
		BookDao dao = BookDao.getInstance();
		Connection con = dao.connect();
		dao.insertBook(con, book);		
		request.setAttribute("isRedirect", true);
		dao.disconnect(con);
	}
}
