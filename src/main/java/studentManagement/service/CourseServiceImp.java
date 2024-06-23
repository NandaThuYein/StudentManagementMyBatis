package studentManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import studentManagement.persistant.dto.CourseRequestDTO;
import studentManagement.persistant.dto.CourseResponseDTO;

public class CourseServiceImp implements CourseService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CourseResponseDTO> selectAllCourse() {
				List<CourseResponseDTO> list = new ArrayList();
				try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
					list = session.selectList("courseNameSpace.selectAllCourse");
				}
		return list;
	}

	@Override
	public int insertCourse(CourseRequestDTO dto) {
		int result = 0;
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
			result = session.insert("courseNameSpace.insertCourse",dto);
			session.commit();
		}
		return result;
	}

}
