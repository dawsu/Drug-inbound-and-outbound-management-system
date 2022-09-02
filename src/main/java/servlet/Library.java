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

@WebServlet("/Library")
public class Library extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Library() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String flag=request.getParameter("zf");
		System.out.print("调度:");
		int numy=Integer.parseInt(request.getParameter("numy"));
		int numc=Integer.parseInt(request.getParameter("num"));
		System.out.println(numy+flag+numc);
		int x;
		if(flag.equals("+")) {
			x=numy+numc;
		}else {
			x=numy-numc;
		}
		
		Connection connect=null;
		Statement statement=null;
		String back=null;
		try {
			connect = Link.getConnection();
			statement=connect.createStatement();			
			String Query = "update "
					+link.VariableTable.TABLE_SUSER+" set "
					+link.VariableTable.TABLE_THIRD+" ='"
					+ x +"'  where "
					+link.VariableTable.TABLE_FIEST+" = " 
					+ request.getParameter("id") +"";	
			if(statement.executeUpdate(Query)==1)  back="修改成功";
			statement.close();
			link.Link.close(connect);
		} catch (SQLException e) {
			back="修改失败";
		}
		try {
			connect = Link.getConnection();
			statement=connect.createStatement();			
			String Query = "insert into "+link.VariableTable.TABLE_SUSER2+" values('"
					+request.getParameter("id")+"','"
					+request.getParameter("name")+"','"
					+request.getParameter("zf")+request.getParameter("num")+"','"
					+request.getParameter("date")+"')";		
			if(statement.executeUpdate(Query)==1)  back="操作成功";
			else back="操作失败";
			statement.close();
			link.Link.close(connect);
		} catch (SQLException e) {
			back="连接错误";
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=gb2312");
		response.getWriter().write(back);
	}

}
