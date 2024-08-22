package com.Employee.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmpController {

    List<Employee> emp=new ArrayList<>();
    @Autowired
    EmployeeService Employe;
//    EmployeeService Employe =new EmployeeServiceImp();
    @GetMapping("employees")
    public List<Employee> getAllEmploye(){
        return Employe.readEmployees();
    }

    @GetMapping("employeesFind/{id}")
    public Employee getEmploye(@PathVariable Long id){

        return Employe.readEmployee(id);
    }

    @PostMapping("employees")
    public String craeteEmployee(@RequestBody Employee employ) {

        return Employe.createEmployee(employ);

    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployees(@PathVariable Long id){
        if(Employe.deleteEmployee(id)){

            return "Delete successfully";
        }
        else{
            return "Data Not Found";
        }
    }

    @PutMapping("employees/{id}")
        public String putMethodName(@PathVariable Long id,@RequestBody Employee employee) {
            return Employe.updateEmployee(id,employee);
        }



}

