package com.student;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {

	List<StudentDTO> selectStudentMode(HashMap<String, Object> map);
	List<StudentDTO> selectAllStudent();
	int insertLog(HashMap<String, Object> map);

}
