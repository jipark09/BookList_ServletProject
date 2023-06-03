package kr.ac.green;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// state-less : 공유되는 객체는 상태를 가지면 안된다. 

	private void loadProp(String path, Properties prop) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			prop.load(fis);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch(Exception e) {}
		}
	}
	@Override
	public void init(ServletConfig config) throws ServletException {		
		super.init(config);
		
		ServletContext application = config.getServletContext();		
		String path = application.getRealPath(getInitParameter("cmdsInfoPath"));		
		
		Properties prop = new Properties();
		loadProp(path, prop);		
		
		CmdFactory.init(prop);		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc_kr");
		process(request, response);		
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI().substring(request.getContextPath().length());
		
		CmdFactory.doAction(request, cmd);
			
		String temp = (String)request.getAttribute("nextPage");
		if(temp == null) {
			temp = cmd.substring(1, cmd.lastIndexOf("."));
		}
		String nextPage = MyViewResolver.getNextPage(temp);
		
		if(request.getAttribute("isRedirect") != null) {
			response.sendRedirect(request.getContextPath());
		} else {
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
	}
}
















