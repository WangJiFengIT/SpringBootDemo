package com.example.demo.service.impl;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import com.example.demo.utils.ResultMsg;
import com.example.demo.utils.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceimpl implements StudentService {

    @Autowired
    private StudentMapper mapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public ResultMsg getStudentByGuid(String guid) {
        return new ResultMsg(ResultStatus.OK, mapper.getStudentByGuid(guid));
    }

    @Override
    public ResultMsg getStudentList() {
        try {
            List<Student> students = mapper.getStudentList();
            return new ResultMsg(ResultStatus.OK, students);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg(ResultStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResultMsg delByGuid(String guid) {
        mapper.delByGuid(guid);
        return new ResultMsg(ResultStatus.OK, "delByGuid success");
    }

    @Override
    public ResultMsg updateStudent(Student student) {
        mapper.updateStudent(student);
        return new ResultMsg(ResultStatus.OK, "updateStudent success");
    }

    @Override
    public ResultMsg addStudent(Student student) {
        mapper.addStudent(student);
        return new ResultMsg(ResultStatus.OK, "addStudent success");
    }

    //Redis获取集合 0，-1 返回所有对象
    @Override
    public ResultMsg getRedisResult() {
        try {
            //获取单个value
            Object f = redisTemplate.opsForValue().get("abcdf");
            //获取一个map value
            List<String> map = redisTemplate.opsForList().range("6010:list12704", 0, -1);
            return new ResultMsg(ResultStatus.OK, map);
        } catch (Exception e) {
            return new ResultMsg(ResultStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
