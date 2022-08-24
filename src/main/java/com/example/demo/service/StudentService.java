package com.example.demo.service;

import com.example.demo.pojo.Student;
import com.example.demo.utils.ResultMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
    ResultMsg getStudentByGuid(String guid);

    ResultMsg getStudentList();

    ResultMsg delByGuid(String guid);

    ResultMsg updateStudent(Student student);

    ResultMsg addStudent(Student student);

    ResultMsg getRedisResult();
}
