package com.example.demo;

import com.example.demo.Patient.dto.PatientDto;
import com.example.demo.Patient.dto.PatientNameDto;
import com.example.demo.Patient.entity.Patient;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    void exp1(){

        List<String> list =List.of("1","4","100");
        /*Function<String,Integer> mapper=s -> Integer.parseInt(s);
        List<Integer> integers =list.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());*/
        List<Integer> integers =list.stream().map(Integer::parseInt)
                .map(i->i*i)
                .filter(i-> i>=4)
                .toList();
                //.collect(Collectors.toList());
        //integers.forEach(i->System.out.println(i));
        integers.forEach(System.out::println);
        //System.out.println(integers);
System.out.println("=====================");
        //Afficher la longeur de ces caracteres :
        list.stream().map(s->s.length()).forEach(System.out::println);
        System.out.println("=====================");
        list.stream().map(String::length).forEach(System.out::println);
        System.out.println("-------------------------");
        List<Patient>patients=List.of(Patient.builder().id(1l).name("p1").build(),Patient.builder().id(2l).name("p2").build());
        patients.stream().map(Patient::getName).forEach(System.out::println);
        System.out.println("**********************");
        //System.out.println(PatientDto.builder().id(2l).name("fatimazahra").build());
        PatientNameDto dto = new PatientNameDto(1l,"name", Patient.Gender.MALE);
        System.out.println(dto);
        System.out.println(dto.name());

        System.out.println(PatientNameDto.builder().id(3l).name("name2").build());



    }
    Main(){
        exp1();
    }
    public static void main(String[] args) {

        new Main();
    }
}
