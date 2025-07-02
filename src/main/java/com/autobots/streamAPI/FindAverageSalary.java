package com.autobots.streamAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindAverageSalary {
    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee(1, "Alice", 28, 3000, "IT"),
                new Employee(2, "Bob", 35, 4000, "HR"),
                new Employee(3, "Charlie", 40, 5000, "Finance"),
                new Employee(4, "David", 25, 3500, "IT"),
                new Employee(5, "Eva", 30, 4200, "Marketing"),
                new Employee(6, "Frank", 45, 6000, "Finance"),
                new Employee(7, "Grace", 32, 4100, "HR"),
                new Employee(8, "Henry", 29, 3900, "IT"),
                new Employee(9, "Isabel", 38, 4500, "Marketing"),
                new Employee(10, "Jack", 27, 3600, "IT"));

        Map<String,Double> averageSalaryByDep = new HashMap<>();
        Map<String,Integer> averageByDep = new HashMap<>();
        for (Employee employee: employees){
            averageSalaryByDep.put(employee.getDepartment(),
                    averageSalaryByDep.getOrDefault(employee.getDepartment(),0.0)
                            +employee.getSalary());
            averageByDep.put(employee.getDepartment(),
                    averageByDep.getOrDefault(employee.getDepartment(),0) + 1);
        }

        double averageSal = 0.0;
        for (String dep: averageSalaryByDep.keySet()){
           averageSal = averageSalaryByDep.get(dep) / averageByDep.get(dep);
            System.out.printf("%s -> $%.2f%n" , dep,averageSal);
        }

        Map<String,Double> averageSalaryBydepWithStream = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));

        averageSalaryBydepWithStream.forEach((dep,averSalary) ->
                System.out.println(dep + " -> $" + averSalary));

        for (Map.Entry<String,Double> entry: averageSalaryBydepWithStream.entrySet()){
            System.out.println(entry.getKey() + " -> $" + entry.getValue());
        }
    }
}
