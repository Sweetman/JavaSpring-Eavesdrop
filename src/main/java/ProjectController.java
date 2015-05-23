import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProjectController {
	
	private ProjectQueryService pqs;

	public ProjectController() {
		
	}
	
	public ProjectController(ProjectQueryService projectQueryService) {
		this.pqs = projectQueryService;
	}
	
	@ResponseBody
    @RequestMapping(value = "/")
    public String helloWorld()
    {
        return "Welcome to assignment 3! To use this application, please go to http://localhost:8080/assignment3/meetings?project={project_name}";
    }
	
	@ResponseBody
    @RequestMapping(value = "/meetings", params = {"project"}, method=RequestMethod.GET)
    public String getProject(@RequestParam("project") String project)
    {
		return this.pqs.getProject(project);
    }
	
	public void setProjectQueryService(ProjectQueryService pqs){
		this.pqs = pqs;
	}
}