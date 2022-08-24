package com.example.demo.controller;

import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import com.example.demo.utils.ResultMsg;
import com.example.demo.utils.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService service;

    @RequestMapping("/getStudentList")
    public ResultMsg getStudentList() {
        return service.getStudentList();
    }

    @RequestMapping("/getStudentByGuid")
    public ResultMsg getStudentByGuid(String guid) {
        return service.getStudentByGuid(guid);
    }

    @RequestMapping("/add")
    public ResultMsg add() {
        Student student = new Student();
        student.Guid = UUID.randomUUID().toString();
        student.Name = "lebron";
        student.Age = 37;
        return service.addStudent(student);
    }

    @RequestMapping("/update")
    public ResultMsg update() {
        Student student = new Student();
        student.Guid = "000286a5-d2cd-4723-a";
        student.Name = "lebron James";
        student.Age = 37;
        return service.updateStudent(student);
    }

    @RequestMapping("/del")
    public ResultMsg delete(String guid) {
        return service.delByGuid(guid);
    }

    @RequestMapping("/reids_getList")
    public ResultMsg getRedisList() {
        return service.getRedisResult();
    }
}
