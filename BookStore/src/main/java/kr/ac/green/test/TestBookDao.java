package kr.ac.green.test;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.ac.green.Book;
import kr.ac.green.dao.BookDao;

public class TestBookDao {
	private static BookDao dao;
	private Connection con;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = BookDao.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		con = dao.connect();
		//con.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		//con.rollback();
		dao.disconnect(con);
	}

	@Test
	public void testInsertBook() {
		Book book = new Book("testTitle", "testWriter", "password", 100, "testPublisher", "testComment");
		int result = dao.insertBook(con, book);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void testDeleteBook() {
		int result = dao.deleteBook(con, 4);
		Assert.assertEquals(1, result);
	
	}
	
	@Test
	public void testUpdateBook() {
		Book book = new Book(1, "title1", "writer1", "password1", 200, "publisher1", "comment1");
		Assert.assertEquals(1, dao.updateBook(con, book));
	}
	
	@Test
	public void testGetAll() {
		Assert.assertEquals(2, dao.getAll(con).size());
	}
	
	@Test
	public void testGetBookByNum() {
		int num = 1;
		Book book = dao.getBookByNum(con, num);
		Assert.assertEquals(num, book.getNum());
		Assert.assertEquals("comment1", book.getComment());
	}
	
	@Test
	public void testGetBooksByTitle() {
		Assert.assertEquals(1, dao.getBooksByTitle(con, "testTitle").size());
	}
}















