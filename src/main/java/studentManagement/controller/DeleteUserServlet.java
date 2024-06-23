package studentManagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studentManagement.persistant.dto.UserRequestDTO;
import studentManagement.persistant.dto.UserResponseDTO;
import studentManagement.service.UserServiceImp;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String userId = request.getParameter("id");
				UserServiceImp impl = new UserServiceImp();
				UserRequestDTO dto = new UserRequestDTO();
				dto.setUserId(userId);
				int ans = impl.deleteUser(dto);
				if(ans == 0) {
					request.setAttribute("error","User Delete Failed !");
				}else {
					List<UserResponseDTO> list = (List<UserResponseDTO>) request.getServletContext().getAttribute("list");
					UserResponseDTO ur = list.stream().filter(u -> u.getUserId().equals(userId)).findFirst()
											.orElse(null);
					UserResponseDTO usr = (UserResponseDTO) request.getSession().getAttribute("res");
					if(ur.getUserId().equals(usr.getUserId())) {
						request.getRequestDispatcher("UserLoginServlet").forward(request,response);
					}
					if(ur != null) {
						list.remove(ur);
					}
					request.setAttribute("msg","User Delete Successfully !");
				}
				request.getRequestDispatcher("SearchUser.jsp").include(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
