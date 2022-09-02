package unitTest;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import servlet.Register;

public class Rso {
	private Register servlet;   
    private HttpServletRequest mockRequest;   
    private HttpServletResponse mockResponse; 

	@Before
	public void setUp() throws Exception {
		servlet = new Register();     
        //创建request和response的Mock   
        mockRequest = createMock(HttpServletRequest.class);
		mockResponse = createMock(HttpServletResponse.class);
	}

	@After
	public void tearDown() throws Exception {
		//为了验证指定的调用行为确实发生了，要调用verify(mock)进行验证。   
        verify(mockRequest);   
        verify(mockResponse);   
	}

	@Test
	public void test() throws Exception {
		//录制request和response的动作
        mockRequest.setCharacterEncoding("utf-8"); 
		mockRequest.getParameter("id");
		expectLastCall().andReturn("99999999");
		mockRequest.getParameter("name");
		expectLastCall().andReturn("测试名称");
		mockRequest.getParameter("num");
		expectLastCall().andReturn("9999");
		mockRequest.getParameter("date");
		expectLastCall().andReturn("测试日期");
		
		StringWriter output = new StringWriter();
        PrintWriter contentWriter = new PrintWriter(output);
        expect(mockResponse.getWriter()).andReturn(contentWriter);  
        mockResponse.setHeader("Cache-Control", "no-cache");
    	mockResponse.setContentType("text/json;charset=gb2312");
		
        //回放   
        replay(mockRequest);
        replay(mockResponse);
           
        //开始测试Servlet的doPost方法   
        servlet.doPost(mockRequest, mockResponse);
	}

}
