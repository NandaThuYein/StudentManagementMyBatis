package studentManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import studentManagement.model.StudentBean;
import studentManagement.persistant.dto.StudCourseRequestDTO;
import studentManagement.persistant.dto.StudCourseResponseDTO;
import studentManagement.persistant.dto.StudentRequestDTO;
import studentManagement.persistant.dto.StudentResponseDTO;

public class StudentServiceImp implements StudentService {

	@Override
	public int insertStudent(StudentRequestDTO dto) {
		int result = 0;
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
			result = session.insert("studentNameSpace.insertStudent",dto);
			session.commit();
		}
		return result;
	}

	@Override
	public int insertStudCourse(StudCourseRequestDTO dto) {
		int result = 0;
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
			result = session.insert("studentNameSpace.insertStudCourse",dto);
			session.commit();
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<StudentResponseDTO> selectAllStudent() {
		List<StudentResponseDTO> list = new ArrayList();
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
			list = session.selectList("studentNameSpace.selectAllStudent");
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<StudCourseResponseDTO> allStudCourse() {
		List<StudCourseResponseDTO> list = new ArrayList();
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
			list = session.selectList("studentNameSpace.allStudCourse");
		}
		return list;
	}

	@Override
	public int deleteStudCourse(StudentBean bean) {
		int result = 0;
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
			result = session.delete("studentNameSpace.deleteStudCourse",bean);
			session.commit();
		}
		return result;
	}

	@Override
	public int deleteStudent(StudentBean bean) {
		int result = 0;
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
			result = session.delete("studentNameSpace.deleteStudent",bean);
			session.commit();
		}
		return result;
	}

	@Override
	public int updateStudent(StudentBean bean) {
		int result = 0;
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
			result = session.update("studentNameSpace.updateStudent",bean);
			session.commit();
		}
		return result;
	}

}
