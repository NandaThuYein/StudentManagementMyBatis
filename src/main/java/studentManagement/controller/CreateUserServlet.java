package studentManagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studentManagement.persistant.dto.UserRequestDTO;
import studentManagement.service.UserServiceImp;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
				request.setAttribute("bean",dto);
				request.setAttribute("error","Field cannot be blank !");
			}else if(!dto.getUserPassword().equals(dto.getConfPassword())){
				request.setAttribute("bean",dto);
				request.setAttribute("error","Please check your confirm password again.");
			}else {
					UserServiceImp impl = new UserServiceImp();
				int result = impl.insertUser(dto);
				if(result == 0) {
					request.setAttribute("bean",dto);
					request.setAttribute("error","Create Account Failed !");
				}else {
					request.setAttribute("succ","Create Account Successfully !");
					request.getRequestDispatcher("UserLogin.jsp").forward(request,response);
				}
			}
			request.getRequestDispatcher("CreateUser.jsp").include(request,response);
	}

}
