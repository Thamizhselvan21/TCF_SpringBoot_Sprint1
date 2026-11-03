// This program defines the Employee Repository interface
// for performing database operations using Spring Data JPA.
package com.tcf.hrms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tcf.hrms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // This method checks whether an employee exists with the given email.
	//AOT Ahead-of-Time (Processing or Compilation).
	boolean existsByEmail(String email);

}
