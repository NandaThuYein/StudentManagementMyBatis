package studentManagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studentManagement.persistant.dto.StudCourseResponseDTO;
import studentManagement.persistant.dto.StudentResponseDTO;
import studentManagement.service.StudentServiceImp;

/**
 * Servlet implementation class SearchStudentServlet
 */
@WebServlet("/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						
						StudentServiceImp impl = new StudentServiceImp();
						List<StudentResponseDTO> studentList = impl.selectAllStudent();
						if(studentList.size() == 0) {
							request.setAttribute("noStudent","No Student");
						}else {
							List<StudCourseResponseDTO> scList = impl.allStudCourse();
							if(scList.size() != 0) {
								request.setAttribute("scList",scList);
							}
							request.setAttribute("studentList",studentList);
						}
				
				request.getRequestDispatcher("SearchStudent.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
	}

}
