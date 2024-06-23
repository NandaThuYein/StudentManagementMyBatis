package studentManagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentManagement.model.StudentBean;
import studentManagement.service.StudentServiceImp;

/**
 * Servlet implementation class DeleteStudentServlet
 */
@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String studentId = request.getParameter("studentId");
			StudentServiceImp impl = new StudentServiceImp();
			StudentBean bean = new StudentBean();
			bean.setStudentId(studentId);
			int ans = impl.deleteStudent(bean);
			if(ans == 0) {
				request.setAttribute("error","Delete Student Failed !");
				request.getRequestDispatcher("UpdateStudentServlet?studentId="+studentId).forward(request,response);
			}else {
				response.sendRedirect("SearchStudentServlet");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
