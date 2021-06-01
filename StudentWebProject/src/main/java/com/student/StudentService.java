package com.student;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
	private StudentMapper mapper;

	public StudentService(StudentMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<StudentDTO> selectStudentMode(String mode, String search) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mode", mode);
		map.put("search", search);
		return mapper.selectStudentMode(map);
	}

	public List<StudentDTO> selectAllStudent() {
		return mapper.selectAllStudent();
	}

	public int insertLog(String log_date, int error_code, String content) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("log_date", log_date);
		map.put("error_code", error_code);
		map.put("content", content);
		return mapper.insertLog(map);
	}
	
	
	
}
