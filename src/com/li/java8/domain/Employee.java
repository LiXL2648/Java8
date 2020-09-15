package com.li.java8.domain;

public class Employee {

    private Integer id;

    private String empName;

    private Integer gender;

    private Integer age;

    private Double salary;

    public Employee() {
    }

    public Employee(String empName) {
        this.empName = empName;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
