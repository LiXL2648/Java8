package com.li.java8.domain;

import java.util.Objects;

public class Employee {

    private Integer id;

    private String empName;

    private Integer gender;

    private Integer age;

    private Double salary;

    private Status status;

    public Employee() {
    }

    public Employee(String empName) {
        this.empName = empName;
    }

    public Employee(Integer id, String empName, Integer gender, Integer age, Double salary, Status status) {
        this.id = id;
        this.empName = empName;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    public Employee(Integer id, String empName, Integer gender, Integer age, Double salary) {
        this.id = id;
        this.empName = empName;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(empName, employee.empName) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(salary, employee.salary) &&
                status == employee.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empName, gender, age, salary, status);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }

    public enum Status {
        FREE, BUSY, VOCATION;
    }
}
