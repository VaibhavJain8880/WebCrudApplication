package in.vaibhav.crudApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.vaibhav.crudApp.entity.Employee;
import in.vaibhav.crudApp.exception.EmployeeNotFoundException;
import in.vaibhav.crudApp.serviceI.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService service;
	
	//1.show Register page
	@GetMapping("register")
	public String showReg() {
		return "EmployeeRegister";
	}
	@PostMapping("save")
	public String saveEmp(
			@ModelAttribute Employee employee,Model model) {
		Integer id=service.saveEmployee(employee);
		String msg="Employee ' "+id +" ' created !!";
		model.addAttribute("message", msg);
		return "EmployeeRegister";
	}
	
	//3.show All Employee
	@GetMapping("/all")
	public String viewAll( 
		@RequestParam(value="mesage" ,required=false )
		String message,Model model)
	{
		List<Employee>list=service.getAllEmployees();
		model.addAttribute("list", list);
		model.addAttribute(message, message);
		return "EmployeeData";
	}
	
	//4.Delete one Employee By id
	@GetMapping("/delete")
	public String delete(
			@RequestParam("id")Integer id,RedirectAttributes attributes)
	{
		String message=null;
		try {
			service.deleteEmployee(id);
			message=id+"-deleted!!";
			
		}catch(EmployeeNotFoundException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
			attributes.addAttribute(message, message);
			return"redirect:all";
		
	}
	@GetMapping("/edit")
	public String showEdit(@RequestParam("id")Integer id,Model model,
			RedirectAttributes attributes)
	{
		String page=null;
		try {
			Employee emp=service.getOneEmployee(id);
			model.addAttribute("Employee", emp);
			page="EmployeeEdit";
		}catch(EmployeeNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page="redirect:all";
		}
		return "page";
	}
	//6.update Employee data on update (submit)
	@PostMapping("/update")
	public String updateEmp(
			@ModelAttribute Employee employee,
			RedirectAttributes attributes)
	{
		service.updateEmployee(employee);
		String message="Employee ' "+employee.getEmpId()+" ' updated !!!";
		attributes.addAttribute("message", message);
		return "redirect:all";
	}
	
	
	
}
