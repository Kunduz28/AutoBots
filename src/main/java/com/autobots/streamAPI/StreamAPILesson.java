package com.autobots.streamAPI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamAPILesson {

    public static void main(String[] args) {

        List<String> names = List.of("Anna", "Bob", "Alex");

        List<String> nameWithA = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("A")) {
                nameWithA.add(name.toUpperCase());
                Collections.sort(nameWithA);
            }
            System.out.println(nameWithA);
        }

//            List<String> result = names.stream()
//                    .filter(name -> name.startsWith("A"))
//                    .map(String::toUpperCase)
//                    .sorted()
//                    .collect(Collectors.toList());
//
//            System.out.println(result);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int r1 = 0;
        for (int num : numbers) {
            r1 += num;
        }
        System.out.println(r1);

        int totalSum = numbers.stream()
                .mapToInt(num -> num)
                .sum();
        System.out.println(totalSum);

        int totalElements = (int) numbers.stream()
                .count();
        System.out.println(totalElements);

        numbers.stream()
                .min(Integer::compareTo)
                .ifPresent(System.out::println);

//        List<String> list = List.of("one","two","three","four","five");

//        list.stream()
//                .filter(s -> s.length() > 3)
//                .findFirst()
//                .ifPresent(System.out::println);

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

//        List<Employee> itEmployees = employees.stream()
//                .filter(s -> s.getDepartment().equals("IT"))
//                .collect(Collectors.toList());
//        System.out.println(itEmployees);
//
//        List<Employee> itEmployees2 = new ArrayList<>();
//        for (Employee employee : employees){
//            if (employee.getDepartment().equalsIgnoreCase("IT")){
//                itEmployees2.add(employee);
//            }
//        }
//        System.out.println(itEmployees2);


        Map<String, List<Employee>> employeesByDepartment = employees
                .stream().collect(Collectors.groupingBy(Employee::getDepartment));
        employeesByDepartment.forEach((department, list) -> {
            System.out.println("Отдел " + department);
            list.forEach(System.out::println);
        });

        Map<String, List<Employee>> employeeByDepartment2 = new HashMap<>();

        for (Employee employee : employees) {
            employeeByDepartment2.putIfAbsent(employee.getDepartment(), new ArrayList<>());
            employeeByDepartment2.get(employee.getDepartment()).add(employee);
        }

        for (Map.Entry<String, List<Employee>> entry : employeeByDepartment2.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            System.out.println(entry.getValue() + ": ");

        }
    }
}


