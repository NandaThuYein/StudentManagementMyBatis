package studentManagement.service;

import java.util.List;

import studentManagement.model.StudentBean;
import studentManagement.persistant.dto.StudCourseRequestDTO;
import studentManagement.persistant.dto.StudCourseResponseDTO;
import studentManagement.persistant.dto.StudentRequestDTO;
import studentManagement.persistant.dto.StudentResponseDTO;

public interface StudentService {
	public int insertStudent(StudentRequestDTO dto);
	
	public int insertStudCourse(StudCourseRequestDTO dto);
	
	public List<StudentResponseDTO> selectAllStudent();
	
	public List<StudCourseResponseDTO> allStudCourse();
	
	public int updateStudent(StudentBean bean);
	
	public int deleteStudent(StudentBean bean);
	
	public int deleteStudCourse(StudentBean bean);
}
