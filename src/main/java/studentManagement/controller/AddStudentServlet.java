package studentManagement.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentManagement.model.StudentBean;
import studentManagement.persistant.dto.CourseResponseDTO;
import studentManagement.persistant.dto.StudCourseRequestDTO;
import studentManagement.persistant.dto.StudentRequestDTO;
import studentManagement.service.CourseServiceImp;
import studentManagement.service.StudentServiceImp;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					CourseServiceImp impl = new CourseServiceImp();
					List<CourseResponseDTO> courses = impl.selectAllCourse();
					if(courses.size() != 0) {
						request.setAttribute("courses",courses);
					}else {
						request.setAttribute("error","No Course,Please Course Registration");
						request.getRequestDispatcher("CourseRegistration.jsp").forward(request,response);
					}
					request.getRequestDispatcher("StudentRegistration.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						StudentBean bean = new StudentBean();
						StudentServiceImp sdao = new StudentServiceImp();
						CourseServiceImp impl = new CourseServiceImp();
						List<CourseResponseDTO> courses = impl.selectAllCourse();
					bean.setStudentId(request.getParameter("studentId"));
					bean.setStudentName(request.getParameter("studentName"));
					bean.setStudentDob(request.getParameter("studentDob"));
					bean.setStudentGender(request.getParameter("studentGender"));
					bean.setStudentPhone(request.getParameter("studentPhone"));
					bean.setStudentEducation(request.getParameter("studentEducation"));
					bean.setAttendCourse(request.getParameterValues("attendCourse"));
					List<String> list = null;
			if(bean.getAttendCourse() != null) {
					list = Arrays.asList(bean.getAttendCourse());
			}
			if(bean.getStudentId().equals("") || bean.getStudentName().equals("") || bean.getStudentDob().equals("") ||
				bean.getStudentGender() == null || bean.getStudentPhone().equals("") || bean.getStudentEducation().equals("") || bean.getAttendCourse() == null) {
				request.setAttribute("error","Field cannot be blank !");
				request.setAttribute("userinfo",bean);
				request.setAttribute("list",list);
			}else {
				StudentRequestDTO dto = new StudentRequestDTO(bean.getStudentId(),bean.getStudentName(),bean.getStudentDob(),
											bean.getStudentGender(),bean.getStudentPhone(),bean.getStudentEducation());
				int ans = sdao.insertStudent(dto);
				if(ans == 0) {
					request.setAttribute("error","Student Registration Failed !");
					request.setAttribute("userinfo",bean);
					request.setAttribute("list",list);
				}else {
					StudCourseRequestDTO cdto;
					for(String c : bean.getAttendCourse()) {
						cdto = new StudCourseRequestDTO();
						cdto.setStudentId(bean.getStudentId());
						cdto.setCourseId(c);
						sdao.insertStudCourse(cdto);
					}
					request.setAttribute("msg","Student Registration Successfully !");
				}
			}
			request.setAttribute("courses",courses);
			request.getRequestDispatcher("StudentRegistration.jsp").forward(request,response);
	}

}
