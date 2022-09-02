package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import link.Link;

@WebServlet("/Cancellation")
public class Cancellation extends HttpServlet {
	private static final long serialVersionUID = 1L;    
    public Cancellation() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("调用删除");
		Connection connect=null;
		Statement statement=null;
		int flag=0;	
		try {
			connect = Link.getConnection();
			statement=connect.createStatement();			
			String Query = "delete from "
					+link.VariableTable.TABLE_SUSER+" where "
					+link.VariableTable.TABLE_FIEST+" = '"
					+request.getParameter("id")+"'";
			flag = statement.executeUpdate(Query);
			
			statement.close();
			link.Link.close(connect);
		} catch (SQLException e) {
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=gb2312");
		if(flag==1) {
			response.getWriter().write("删除成功");
			System.out.println("删除成功");
		}else {
			response.getWriter().write("删除失败");
			System.out.println("删除失败");
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
