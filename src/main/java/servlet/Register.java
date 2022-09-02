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

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Register() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("添加调用");
		Connection connect=null;
		Statement statement=null;
		String back=null;
		try {
			connect = Link.getConnection();
			statement=connect.createStatement();			
			String Query = "insert into "+link.VariableTable.TABLE_SUSER+" values('"
					+request.getParameter("id")+"','"
					+request.getParameter("name")+"','"
					+request.getParameter("num")+"','"
					+request.getParameter("date")+"')";		
			if(statement.executeUpdate(Query)==1)  back="添加成功";
			else back="添加失败";
			statement.close();
			link.Link.close(connect);
		} catch (SQLException e) {
			back="药品id重复";
		}
		System.out.println(back);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=gb2312");
		response.getWriter().write(back);
	}

}
