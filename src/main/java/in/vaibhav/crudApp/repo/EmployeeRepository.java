package in.vaibhav.crudApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vaibhav.crudApp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
