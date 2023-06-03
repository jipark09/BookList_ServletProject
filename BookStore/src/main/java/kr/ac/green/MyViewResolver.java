package kr.ac.green;

public class MyViewResolver {
	public static String getNextPage(String name) {
		// /list.book
		return name + ".jsp";
	}
	
}
