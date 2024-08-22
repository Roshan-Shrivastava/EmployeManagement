package com.Employee.demo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
     EmployeeRepository employeeRepository;

    List<Employee> employees = new ArrayList<>();

    @Override
    public String createEmployee(Employee employee) {
        EmployeEntity employeEntity = new EmployeEntity();
        BeanUtils.copyProperties(employee,employeEntity);
        employeeRepository.save(employeEntity);
        return "Saved Successfully";
    }

    @Override
    public Employee readEmployee(Long id) {

            Optional<EmployeEntity> emp = employeeRepository.findById(id);
            Employee employee= new Employee();
            if(emp.isPresent()){
                EmployeEntity employeList  = employeeRepository.findById(id).get();
                BeanUtils.copyProperties(employeList,employee);
                return employee;
            }else{
                return null;
                
            }
    }

    @Override
    public List<Employee> readEmployees() {
//        List<EmployeEntity> employeeList = employeeRepository.findAll();
//
//        for (EmployeEntity employeEntity : employeeList) {
//            Employee emp = new Employee();
//            emp.setId(employeEntity.getId());
//            emp.setName(employeEntity.getName());
//            emp.setMobileNo(employeEntity.getMobileNo());
//            employees.add(emp);
//        }
//        return employees;
        List<EmployeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for (EmployeEntity employeEntity : employeeEntities) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeEntity, employee);
            employees.add(employee);
        }

        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {

//            EmployeEntity emp = employeeRepository.findById(id).get();
//            employeeRepository.delete(emp);
//            return true;

        Optional<EmployeEntity> empOpt = employeeRepository.findById(id);
        if (empOpt.isPresent()) {
            // If found, delete the employee
            employeeRepository.delete(empOpt.get());
            return true;
        } else {
            // If not found, you could throw an exception or return false
             return false;

        }

    }

    @Override
    public String updateEmployee(Long id, Employee employee){
        EmployeEntity newEmployee = employeeRepository.findById(id).get();

        newEmployee.setMobileNo(employee.getMobileNo());
        newEmployee.setName(employee.getName());
        employeeRepository.save(newEmployee);
        return "Update SuccessFully";
    }
}
