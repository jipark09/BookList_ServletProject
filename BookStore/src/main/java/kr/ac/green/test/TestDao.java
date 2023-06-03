package kr.ac.green.test;

import java.sql.Connection;

import kr.ac.green.Book;
import kr.ac.green.dao.BookDao;

public class TestDao {
	public static void main(String[] args) {
		BookDao dao = BookDao.getInstance();
		
		Connection con = dao.connect();
		
		int result = dao.insertBook(
			con, new Book("power java", "inkuk", "1234", 28000, "infinity books", "wow!! good")
		);
		
		dao.disconnect(con);
		System.out.println(result);
	}
}
