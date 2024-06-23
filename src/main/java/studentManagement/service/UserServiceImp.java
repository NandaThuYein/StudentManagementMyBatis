package studentManagement.service;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import studentManagement.persistant.dto.UserRequestDTO;
import studentManagement.persistant.dto.UserResponseDTO;

public class UserServiceImp implements UserService {

	@Override
	public UserResponseDTO userLogin(UserRequestDTO dto) {
			UserResponseDTO res = new UserResponseDTO();
			try (SqlSession sessionSql = MyBatisUtil.getSqlSessionFactory().openSession()){
				res = sessionSql.selectOne("userNameSpace.userLogin",dto);
			}
		return res;
	}
	
	@Override
	public int insertUser(UserRequestDTO dto) {
		int result = 0;
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
			result = session.insert("userNameSpace.insertUser",dto);
			session.commit();
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<UserResponseDTO> searchUser(UserRequestDTO dto) {
			List<UserResponseDTO> list = new ArrayList();
			try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
				list = session.selectList("userNameSpace.searchUser",dto);
			}
		return list;
	}

	@Override
	public int updateUser(UserRequestDTO dto) {
		int result = 0;
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
			result = session.update("userNameSpace.updateUser",dto);
			session.commit();
		}
		return result;
	}

	@Override
	public int deleteUser(UserRequestDTO dto) {
		int result = 0;
		try(SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()){
			result = session.delete("userNameSpace.deleteUser",dto);
			session.commit();
		}
		return result;
	}

}
