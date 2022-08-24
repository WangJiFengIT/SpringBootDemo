package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {
    Student getStudentByGuid(@Param("guid") String guid);

    public List<Student> getStudentList();

    void delByGuid(@Param("guid")  String guid);

    void updateStudent(Student student);

    void addStudent(Student student);
}
