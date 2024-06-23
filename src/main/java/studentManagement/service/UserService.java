package studentManagement.service;

import java.util.List;

import studentManagement.persistant.dto.UserRequestDTO;
import studentManagement.persistant.dto.UserResponseDTO;

public interface UserService {
	public UserResponseDTO userLogin(UserRequestDTO dto);
	
	public int insertUser(UserRequestDTO dto);
	
	public List<UserResponseDTO> searchUser(UserRequestDTO dto);
	
	public int updateUser(UserRequestDTO dto);
	
	public int deleteUser(UserRequestDTO dto);
	
}
