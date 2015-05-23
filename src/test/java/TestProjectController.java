import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestProjectController {
	ProjectController projectController = new ProjectController();
	ProjectQueryService mockQuery = null;
	
	@Before
	public void setUp1() {		
		mockQuery = mock(ProjectQueryService.class);		
		projectController.setProjectQueryService(mockQuery);
	}
	
	@Test
	public void thisAlwaysPasses() {
    }
	
	@Test
	public void testHelloWorld() {
		String reply = projectController.helloWorld();
		assertEquals("Welcome to assignment 3! To use this application, please go to http://localhost:8080/assignment3/meetings?project={project_name}", reply);
	}
	
	@Test
	public void testInvalidProjectGetProject() {
		when(mockQuery.getProject("not_a_project_name")).thenReturn("Unknown project not_a_project_name");
		String query = projectController.getProject("not_a_project_name");
		assertEquals("Unknown project not_a_project_name", query);
		verify(mockQuery).getProject("not_a_project_name");
	}
	
	@Test
	public void testEmptyProjectGetProject() {
		when(mockQuery.getProject("")).thenReturn("Unknown project");
		String query = projectController.getProject("");
		assertEquals("Unknown project", query);
		verify(mockQuery).getProject("");
	}
	@Test
	public void testValidGetProject() {
		when(mockQuery.getProject("_trove")).thenReturn("Year &nbsp; Count<br>2013 &nbsp;&nbsp;&nbsp;&nbsp; 1<br>2014 &nbsp;&nbsp;&nbsp;&nbsp; 1<br>");
		String query = projectController.getProject("_trove");
		assertEquals("Year &nbsp; Count<br>2013 &nbsp;&nbsp;&nbsp;&nbsp; 1<br>2014 &nbsp;&nbsp;&nbsp;&nbsp; 1<br>", query);
		verify(mockQuery).getProject("_trove");
	}
}
