package in.vaibhav.crudApp.serviceI;

import java.util.List;

import in.vaibhav.crudApp.entity.Employee;

public interface IEmployeeService {
	
	Integer saveEmployee(Employee emp);
	void updateEmployee(Employee emp);
	void deleteEmployee(Integer id);
	Employee getOneEmployee(Integer id);
	List<Employee>getAllEmployees();
}
