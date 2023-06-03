package kr.ac.green.cmds;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.Book;
import kr.ac.green.MyViewResolver;
import kr.ac.green.dao.BookDao;

public class ModifyCmd implements ICmd {
	@Override
	public void action(HttpServletRequest request) {
		BookDao dao = BookDao.getInstance();
		Connection con = dao.connect();
		int num = Integer.parseInt(request.getParameter("num"));
		Book oldBook = dao.getBookByNum(con, num);
		
		String password = request.getParameter("pw");
		Book newBook = new Book(
				num,
				request.getParameter("title"),
				request.getParameter("writer"),
				password,
				Integer.parseInt(request.getParameter("price")),
				request.getParameter("publisher"),
				request.getParameter("comment")
		);
		if(oldBook.getPassword().equals(password)) {
			dao.updateBook(con, newBook);
			request.setAttribute("isRedirect", true);
		} else {
			request.setAttribute("book", newBook);
			request.setAttribute("wrongPassword", "");
			request.setAttribute("nextPage", "view");
		}
		dao.disconnect(con);

	}

}
