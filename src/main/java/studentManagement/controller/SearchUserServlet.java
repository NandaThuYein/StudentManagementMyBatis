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
 * Servlet implementation class SearchUserServlet
 */
@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserServlet() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				UserRequestDTO dto = new UserRequestDTO();
				dto.setUserId(request.getParameter("userId"));
				dto.setUserName(request.getParameter("userName"));
				if(dto.getUserId().equals("") && dto.getUserName().equals("")) {
					request.setAttribute("error","Please you put id or name !");
				}else {
							UserServiceImp impl = new UserServiceImp();
					List<UserResponseDTO> list = impl.searchUser(dto);
					if(list.size() == 0) {
						request.setAttribute("error","No Using this id or name");
					}else { 
						List<UserResponseDTO> list2 = (List<UserResponseDTO>) request.getServletContext().getAttribute("list");
						if(list2 == null) {
							request.getServletContext().setAttribute("list",list);
						}else {
							request.getServletContext().removeAttribute("list");
							request.getServletContext().setAttribute("list",list);
						}
					}
				}
				request.getRequestDispatcher("SearchUser.jsp").forward(request,response);
	}

}
