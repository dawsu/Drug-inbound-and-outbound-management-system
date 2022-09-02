package integrationTest;
 
import java.io.IOException;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import javax.servlet.ServletException;

import unitTest.*;

public class Intest {
 
	private RegisterTest registerTest;
	private QueryTest queryTest;
	private LibraryTest libraryTest;
	private QuerytTest querytTest;
	private ReduceTest reduceTest;
	private CancellationTest cancellationTest;

    
	@Before
    public void setUp(){
		System.out.println("开始测试");
		registerTest=new RegisterTest();
		queryTest=new QueryTest();
		libraryTest=new LibraryTest();
		querytTest=new QuerytTest();
		reduceTest=new ReduceTest();
		cancellationTest=new CancellationTest();
    }
       
    @After
    public void tearDown(){
       System.out.println("测试通过");
    }
  

    @Test
    public void test() throws IOException, ServletException{
    	registerTest.setUp();
    	registerTest.test();
    	registerTest.tearDown();
    	System.out.println("registerTest测试通过");
    	queryTest.setUp();
    	queryTest.test();
    	queryTest.tearDown();
    	System.out.println("queryTest测试通过");
    	libraryTest.setUp();
    	libraryTest.test();
    	libraryTest.tearDown();
    	System.out.println("libraryTest测试通过");
    	querytTest.setUp();
    	querytTest.test();
    	querytTest.tearDown();
    	System.out.println("querytTest测试通过");
    	reduceTest.setUp();
    	reduceTest.test();
    	reduceTest.tearDown();
    	System.out.println("reduceTest测试通过");
    	cancellationTest.setUp();
    	cancellationTest.test();
    	cancellationTest.tearDown();
    	System.out.println("cancellationTest测试通过");
    }   
	
}