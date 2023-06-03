-- book.sql
CREATE TABLE a_book (
	b_num			INT				PRIMARY KEY		AUTO_INCREMENT,
	b_title			CHAR(200)		NOT NULL,
	b_writer		CHAR(30)		NOT NULL,
	b_password		VARCHAR(10)		NOT NULL,
	b_price			INT				NOT NULL,
	b_publisher		VARCHAR(30)		NOT NULL,
	b_comment		CHAR(200)		NOT NULL
);

-- 전체보기
SELECT * FROM a_book ORDER BY b_num DESC;

DELETE from a_book;

-- 책하나 보기
SELECT * FROM a_book WHERE b_num = ?;


-- 책추가하기
INSERT INTO a_book (b_title, b_writer, b_password, b_price, b_publisher, b_comment) VALUES (?,?,?,?,?,?);

-- 책삭제
DELETE FROM a_book WHERE b_num = ?;

-- 책 수정
UPDATE a_book SET b_title=?, b_writer=?, b_price=?, b_publisher=?, b_comment=? WHERE b_num = ?;

-- 제목으로 책 찾기
SELECT * FROM a_book WHERE b_title = ?;

SELECT * FROM a_book WHERE b_title LIKE '%?%';


-- 페이징
SELECT * FROM a_book ORDER BY b_num DESC LIMIT ?, ?

-- 전체 레코드 수 구하기
SELECT COUNT(*) FROM a_book;