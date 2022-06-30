package in.vaibhav.crudApp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vaibhav.crudApp.entity.Employee;
import in.vaibhav.crudApp.exception.EmployeeNotFoundException;
import in.vaibhav.crudApp.repo.EmployeeRepository;
import in.vaibhav.crudApp.serviceI.IEmployeeService;
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;
	@Override
	public Integer saveEmployee(Employee emp) {
		emp=repo.save(emp);
		return emp.getEmpId();
	}

	@Override
	public void updateEmployee(Employee emp) {
		if(emp.getEmpId()==null || ! repo.existsById(emp.getEmpId()))
				throw new EmployeeNotFoundException(emp.getEmpId() +"-not exist");
		else
			repo.save(emp);

	}

	@Override
	public void deleteEmployee(Integer id) {
		repo.delete(getOneEmployee(id));

	}

	@Override
	public Employee getOneEmployee(Integer id) {
		Optional<Employee>opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new EmployeeNotFoundException(id +"-not exist");
		}
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee>list =repo.findAll();
		return list;
	}

}
