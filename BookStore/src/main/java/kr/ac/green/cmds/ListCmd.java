package kr.ac.green.cmds;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import kr.ac.green.dao.BookDao;

public class ListCmd implements ICmd {
	public void action(HttpServletRequest request) {
		
		int perPage = 3;
		int pageNum = 1; //별말 없으면 1페이지 보여주겠다는 거
		String temp = request.getParameter("pageNum");
		if(temp != null) {//처음에 그냥 시작하면 안들어올때도 있음 그러면 1로 시작할거임!
			pageNum = Integer.parseInt(temp);
		}
		
		BookDao dao = BookDao.getInstance();
		Connection con = dao.connect();
		
		int totalCount = dao.getTotalCount(con);
		int pageCount = totalCount / perPage;
		if(totalCount % perPage != 0) {
			pageCount++; //딱 안떨어지면 페이지수를 하나 더 늘려야됨
		}
		request.setAttribute("list", dao.getList(con, pageNum, perPage));
		dao.disconnect(con);
		
		request.setAttribute("pageNum", pageNum); //파라미터랑 일치하지 않을수있어서 다시 넣는다
		request.setAttribute("pageCount", pageCount);
	}
}
