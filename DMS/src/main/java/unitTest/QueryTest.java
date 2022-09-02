package unitTest;
 
import static org.easymock.EasyMock.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.Query;

public class QueryTest {
 
	private Query servlet;   
    private HttpServletRequest mockRequest;   
    private HttpServletResponse mockResponse;  
    
    
	@Before
    public void setUp(){
           
        servlet = new Query();     
        //创建request和response的Mock   
        mockRequest = createMock(HttpServletRequest.class);
		mockResponse = createMock(HttpServletResponse.class);
    }
       
    @After
    public void tearDown(){
        //为了验证指定的调用行为确实发生了，要调用verify(mock)进行验证。   
        verify(mockRequest);   
        verify(mockResponse);   
    }
  

    @Test
    public void test() throws IOException, ServletException{
        
        //录制request和response的动作
    	mockRequest.setCharacterEncoding("utf-8"); 
    	mockRequest.getParameter("id");
		expectLastCall().andReturn("99999999");
		
		StringWriter output = new StringWriter();  
        PrintWriter contentWriter = new PrintWriter(output);  
        expect(mockResponse.getWriter()).andReturn(contentWriter);  
        mockResponse.setHeader("Cache-Control", "no-cache");
    	mockResponse.setContentType("text/json;charset=gb2312");
		 
        replay(mockRequest);
        replay(mockResponse);
             
        servlet.doPost(mockRequest, mockResponse);
        
    }   
	
}