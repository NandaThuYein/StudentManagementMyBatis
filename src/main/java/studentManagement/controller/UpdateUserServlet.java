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
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String userId = request.getParameter("id");
				List<UserResponseDTO> list = (List<UserResponseDTO>) request.getServletContext().getAttribute("list");
				if(list.size() != 0) {
				UserResponseDTO bean = list.stream().filter(u -> u.getUserId().equals(userId)).findFirst()
													.orElse(null);
				if(bean != null) {
					request.setAttribute("bean",bean);
				}
			}
				request.getRequestDispatcher("UpdateUser.jsp").include(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					UserRequestDTO dto = new UserRequestDTO();
					dto.setUserId(request.getParameter("userId"));
					dto.setUserName(request.getParameter("userName"));
					dto.setUserEmail(request.getParameter("userEmail"));
					dto.setUserPassword(request.getParameter("userPassword"));
					dto.setConfPassword(request.getParameter("confPassword"));
					dto.setUserRole(request.getParameter("userRole"));
			if(dto.getUserId().equals("") || dto.getUserName().equals("") || dto.getUserEmail().equals("") || 
					dto.getUserPassword().equals("") || dto.getConfPassword().equals("") || dto.getUserRole().equals("")) {
					request.setAttribute("error","Field cannot be blank !");
					request.setAttribute("bean",dto);
				}else if(!dto.getUserPassword().equals(dto.getConfPassword())){
					request.setAttribute("bean",dto);
					request.setAttribute("error","Please check your confirm password again.");
				}else {
					UserServiceImp impl = new UserServiceImp();
					int ans = impl.updateUser(dto);
					if(ans == 0) {
						request.setAttribute("error","User Update Failed  !");
					}else {
						List<UserResponseDTO> list = (List<UserResponseDTO>) request.getServletContext().getAttribute("list");
						
						UserResponseDTO ur = list.stream().filter(u -> u.getUserId().equals(dto.getUserId())).findFirst()
												.orElse(null);
						
						UserResponseDTO usr = (UserResponseDTO) request.getSession().getAttribute("res");
						if(ur.getUserId().equals(usr.getUserId())) {
							request.getSession().removeAttribute("res");
							UserResponseDTO usr1 = new UserResponseDTO();
							usr1.setUserId(dto.getUserId());
							usr1.setUserName(dto.getUserName());
							request.getSession().setAttribute("res",usr1);
						}
						if(ur != null) {
							list.remove(ur);
						}
						request.setAttribute("msg","User Update Successfully !");
					}
				}
			request.getRequestDispatcher("UpdateUser.jsp").include(request,response);
	}

}
