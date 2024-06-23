package studentManagement.service;

import java.util.List;

import studentManagement.persistant.dto.CourseRequestDTO;
import studentManagement.persistant.dto.CourseResponseDTO;

public interface CourseService {
	
		public List<CourseResponseDTO> selectAllCourse();
	
		public int insertCourse(CourseRequestDTO dto);
}
