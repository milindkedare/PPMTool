package web;
import domain.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import services.MapvalidationErrorService;
import services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController 
{
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapvalidationErrorService mapValidationService;
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Validated @RequestBody Project project, BindingResult result)
	{
		ResponseEntity<?> errorMap = mapValidationService.MapValidationService(result);
		if(errorMap != null) return errorMap;
		
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project,HttpStatus.CREATED);
		
	}
	@GetMapping("/{projectId")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId)
	{
		Project project = projectService.findProjectByIdentifier(projectId);
		return new ResponseEntity<Project>(project,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects()
	{
		return projectService.findAllProjects();
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectID)
	{
		projectService.deleteProjectByIdentifier(projectID);
		
		return new ResponseEntity<String>("Project with Id : '"+projectID+"'was deleted",HttpStatus.OK);
	}
}
