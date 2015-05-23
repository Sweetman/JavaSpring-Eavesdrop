import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestProjectQueryServiceImpl {
	ProjectQueryServiceImpl projectQuery = new ProjectQueryServiceImpl();
	
	@Test
	public void testValidGetYear(){
		String url = "http://eavesdrop.openstack.org/meetings/_trove/";
		String result = projectQuery.getProjectYear(url);
		assertEquals("2013 &nbsp;&nbsp;&nbsp;&nbsp; 1<br>2014 &nbsp;&nbsp;&nbsp;&nbsp; 1<br>", result);
	}
	
	@Test
	public void testInvalidGetYear(){
		String url = "http://eavesdrop.openstack.org/meetings/invalid_project/";
		String result = projectQuery.getProjectYear(url);
		assertEquals("found no years", result);
	}
	
	@Test
	public void testValidGetCount(){
		String url = "http://eavesdrop.openstack.org/meetings/_trove/2013";
		String result = projectQuery.getProjectCount(url);
		assertEquals("1", result);
	}
	
	@Test
	public void testInvalidGetCount(){
		String url = "http://eavesdrop.openstack.org/meetings/_trove/2015";
		String result = projectQuery.getProjectCount(url);
		assertEquals("0", result);
	}
	
	@Test
	public void testInvalidProjectGetProject() {
		String query = projectQuery.getProject("not_a_project_name");
		assertEquals("Unknown project not_a_project_name", query);
	}
	
	@Test
	public void testEmptyProjectGetProject() {
		String query = projectQuery.getProject("");
		assertEquals("Unknown project ", query);
	}
	
	@Test
	public void testValidGetProject() {
		String query = projectQuery.getProject("_trove");
		assertEquals("Year &nbsp; Count<br>2013 &nbsp;&nbsp;&nbsp;&nbsp; 1<br>2014 &nbsp;&nbsp;&nbsp;&nbsp; 1<br>", query);
	}
	
}
