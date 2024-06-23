package studentManagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentManagement.model.CourseBean;
import studentManagement.persistant.dto.CourseRequestDTO;
import studentManagement.persistant.dto.CourseResponseDTO;
import studentManagement.service.CourseServiceImp;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseServlet() {
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
				CourseBean bean = new CourseBean();
				CourseServiceImp impl = new CourseServiceImp();
				bean.setCourseId(request.getParameter("courseId"));
				bean.setCourseName(request.getParameter("courseName"));
			if(bean.getCourseId().equals("") || bean.getCourseName().equals("")) {
				request.setAttribute("bean",bean);
				request.setAttribute("error","Field connot be blank !");
			}else {
				List<CourseResponseDTO> list = impl.selectAllCourse();
				if(list.size() != 0) {
					CourseResponseDTO crd = list.stream().filter(c -> c.getCourseName().equals(bean.getCourseName()) ||
							c.getCourseId().equals(bean.getCourseId())).findFirst()
											.orElse(null);
					if(crd != null) {
						request.setAttribute("error","Course Id or Course name have been !");
						request.setAttribute("bean",bean);
					}else {
						CourseRequestDTO dto = new CourseRequestDTO();
						dto.setCourseId(bean.getCourseId());
						dto.setCourseName(bean.getCourseName());
						int ans = impl.insertCourse(dto);
						if(ans == 0) {
							request.setAttribute("bean",bean);
							request.setAttribute("error","Course Registration Failed !");
						}else {
							request.setAttribute("msg","Course Registration Successfully !");
						}
					}
				}
			}
			request.getRequestDispatcher("CourseRegistration.jsp").forward(request,response);
	}

}
