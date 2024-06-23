package studentManagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studentManagement.persistant.dto.UserRequestDTO;
import studentManagement.persistant.dto.UserResponseDTO;
import studentManagement.service.UserServiceImp;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					request.getSession().removeAttribute("res");
					request.getServletContext().removeAttribute("list");
					request.getRequestDispatcher("UserLogin.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRequestDTO dto = new UserRequestDTO();
		dto.setUserName(request.getParameter("userName"));
		dto.setUserPassword(request.getParameter("userPassword"));
		if(dto.getUserName().equals("") || dto.getUserPassword().equals("")) {
			request.setAttribute("error","Field cannot be blank !");
			request.setAttribute("bean",dto);
			request.getRequestDispatcher("UserLogin.jsp").forward(request,response);
		}else {
				UserServiceImp impl = new UserServiceImp();
			UserResponseDTO res = impl.userLogin(dto);
			if(res == null) {
				request.setAttribute("error","Please check your data again.");
				request.setAttribute("bean",dto);
				request.getRequestDispatcher("UserLogin.jsp").forward(request,response);
			}else {
				request.getSession().setAttribute("res",res);
				request.getRequestDispatcher("TopMenu.jsp").forward(request,response);
			}
		}
	}

}
