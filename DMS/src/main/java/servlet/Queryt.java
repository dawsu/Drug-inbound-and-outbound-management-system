package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import link.Link;

@WebServlet("/Queryt")
public class Queryt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Queryt() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String num=request.getParameter("id");	
		System.out.println("查询:"+num);
		Connection connect=null;
		Statement statement=null;
		ResultSet result=null;		
		String json=null;	
		try {
			connect = Link.getConnection();
			statement=connect.createStatement();
			String Query=null;
			if(num.equals("")) Query = "select * from "+link.VariableTable.TABLE_SUSER2;		
			else Query = "select * from "
					+link.VariableTable.TABLE_SUSER2+" where "
					+link.VariableTable.TABLE_FIEST+" like '%"+num+"%' or "
					+link.VariableTable.TABLE_SECOND+" like '%"+num+"%' or "
					+link.VariableTable.TABLE_THIRD+" like '%"+num+"%' or "
					+link.VariableTable.TABLE_FOURTH+" like '%"+num+"%'";					
			result = statement.executeQuery(Query);
			List<String> list = new ArrayList<String>();		
			while(result.next()){	
				for(int i=1;i<=4;i++) {
					list.add(result.getObject(i).toString());
				}
			}	
			Gson gson = new Gson( );
	        json = gson.toJson(list);	        
			result.close();
			statement.close();
			link.Link.close(connect);
		} catch (SQLException e) {
		}
		System.out.println(json);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=gb2312");
		response.getWriter().write(json);
	}

}
