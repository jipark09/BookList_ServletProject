package kr.ac.green.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.PreparedStatement;

import kr.ac.green.Book;

/*
 * JDBC(java database connectivity)
 * 
 * 1. 드라이버로드(1번만 수행하면 된다)
 * 2. 연결
 * 3. 질의
 * 4. 자원해제
 */
public class BookDao {
	
	private static final BookDao INSTANCE = new BookDao();
	private BasicDataSource ds;
	
	private BookDao() {
//		// 1. 드라이버 로드
		ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/bookDB");
		ds.setUsername("root");
		ds.setPassword("12345678");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		
		ds.setInitialSize(10); // 처음 가지고 몇개 시작할-> 어플리케이션의 규모에 따라서 달라질 수 있다.
		ds.setMaxActive(10); // 최대 몇개까지 connection을 커버하는가. 10명까지 주는데 11명이 들어왔다. 한놈의 끝낼때까지 대기탄다.
		ds.setMaxIdle(10);
		ds.setMinIdle(10);
	}
	
	public static BookDao getInstance() {
		return INSTANCE;
	}
	
	
	public Connection connect() {
		Connection con = null;
		try {
			con = ds.getConnection(); // 풀에서 한개를 꺼내온다.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void disconnect(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int insertBook(Connection con, Book book) {
		int result = 0;
		Statement stmt = null;
		
		try {
			/*
			 * executeUpdate : INSERT, UPDATE, DELETE => int리턴
			 * executeQuery : SELECT
			 */
			stmt = con.createStatement();
			/*
			String sql = "INSERT INTO book (b_title, b_writer, b_password, b_price, b_publisher, b_comment) "
				+ "VALUES ('"+ book.getTitle() +"','" + book.getWriter() + "','" + book.getPassword() 
				+ "'," + book.getPrice() + ",'" + book.getPublisher() + "','" + book.getComment() + "')";
			*/
			String sql = "INSERT INTO a_book (b_title, b_writer, b_password, b_price, b_publisher, b_comment) "
				+ "VALUES ('%s','%s','%s',%d,'%s','%s')";
			sql = String.format(
				sql, 
				toEn(book.getTitle()), 
				toEn(book.getWriter()), 
				book.getPassword(), 
				book.getPrice(), 
				toEn(book.getPublisher()), 
				toEn(book.getComment())
			);
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return result;
	}
	
	public int deleteBook(Connection con, int num) {
		int result = 0;
		Statement stmt = null;		
		try {
			stmt = con.createStatement();
			String sql = "DELETE FROM a_book WHERE b_num = " + num;
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return result;
	}
	
	public int updateBook(Connection con, Book book) {
		int result = 0;
		PreparedStatement pStmt = null;
		String sql = "UPDATE a_book SET b_title=?, b_writer=?, b_price=?, b_publisher=?, b_comment=? "
			+ "WHERE b_num = ?";
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, toEn(book.getTitle()));
			pStmt.setString(2, toEn(book.getWriter()));
			pStmt.setInt(3, book.getPrice());
			pStmt.setString(4, toEn(book.getPublisher()));
			pStmt.setString(5, toEn(book.getComment()));
			pStmt.setInt(6, book.getNum());
			
			result = pStmt.executeUpdate();
			pStmt.clearParameters();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pStmt);
		}		
		return result;
	}
	
	public Vector<Book> getAll(Connection con) {
		Vector<Book> list = new Vector<Book>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM a_book ORDER BY b_num DESC";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {				
				list.add(rowMapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}	
		return list;
	}
	private Book rowMapping(ResultSet rs) throws SQLException {
		int b_num = rs.getInt("b_num");
		String b_title = toKor(rs.getString("b_title"));
		String b_writer = toKor(rs.getString("b_writer"));
		int b_price = rs.getInt("b_price");
		String b_password = rs.getString("b_password");
		String b_publisher = toKor(rs.getString("b_publisher"));
		String b_comment = toKor(rs.getString("b_comment"));
		
		return new Book(b_num, b_title, b_writer, b_password, b_price, b_publisher, b_comment);
	}
	public Book getBookByNum(Connection con, int num) {
		Book book = null;
		Statement stmt = null;
		ResultSet rs = null;		
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM a_book WHERE b_num =" + num;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				book = rowMapping(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return book;
	}
	
	public Vector<Book> getBooksByTitle(Connection con, String b_title) {
		Vector<Book> list = new Vector<Book>();
		PreparedStatement pStmt = null;
		ResultSet rs = null;		
		String sql = "SELECT * FROM a_book WHERE b_title LIKE ?";		
		
		try {
			pStmt = con.prepareStatement(sql);
			// 와일드 카드 적용
			pStmt.setString(1, "%" + toEn(b_title) + "%");
			rs = pStmt.executeQuery();
			while(rs.next()) {
				list.add(rowMapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//0601 추가
	public Vector<Book> getList(Connection con, int pageNum, int perPage) {
		Vector<Book> list = new Vector<Book>();
		String sql = "SELECT * FROM a_book ORDER BY b_num DESC LIMIT ?, ?";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, (pageNum -1) * perPage);
			pStmt.setInt(2, perPage);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				list.add(rowMapping(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pStmt);
		}
		return list;
		
	}
	
	public int getTotalCount(Connection con) {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM a_book";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			pStmt = con.prepareStatement(sql);
			rs = pStmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); //열 순서임! 이름으로 들고오면 문제가 있다. 접근하는 속도도 이게 더 빠름			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pStmt);
		}
		return count;
	}
	
	public Book[] getAllbyArray(Connection con) {
		Book[] arr =null;
		PreparedStatement pStmt = null;
		ResultSet rs =  null;
		String sql = "SELECT * FROM a_book";
		
		try {
			pStmt = con.prepareStatement(sql);
			rs = pStmt.executeQuery();
			/*길이를 예상할 수 없음. rs의 next가 다음줄을 말해주는건데 그럴려면 현재위치라는 게 존재한다는 거! 
			 * 최초위치는 열이름있는 부분. 현재위치 담고있는 부분을 cursor라고 함.
			 * rs.last()가 제일 마지막 레코드로 이동함.
			 * rs.getRow()가 몇번째 위치에 있는지 알려줌 
			*/
			rs.last();
			int count = rs.getRow();
			arr = new Book[count];
			
			int idx=0;
			//제일마지막으로 커서를 옮겨놔서 next해도 안나옴 다시 처음위치로 옮겨줘야함. 
			rs.beforeFirst();	//최초위치로 옮겨줌 
			
			while(rs.next()) {
				arr[idx++] = rowMapping(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pStmt);
		}
		return arr;
	}
	
	
	private void close(ResultSet rs) {
		try {
			rs.close();
		} catch(Exception e) {}
	}
	private void close(Statement stmt) {
		try {
			stmt.close();
		} catch(Exception e) {}
	}
	
	/*
	 * DB :	8859_1
	 * Java : EUC_KR
	 * 
	 * INSERT, UPDATE : EUC_KR -> 8859_1 : toEn(kor:String) : String 
	 * SELECT : 8859_1 -> EUC_KR : toKor(en:String) : String
	 */
	
	public String toEn(String kor) {
		String en = null;
		try {
			en = new String(kor.getBytes("EUC_KR"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return en;
	}
	public String toKor(String en) {
		String kor = null;
		try {
			kor = new String(en.getBytes("8859_1"), "EUC_KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return kor;
	}
}
