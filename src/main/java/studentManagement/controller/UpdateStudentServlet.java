package studentManagement.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import studentManagement.persistant.dto.StudCourseResponseDTO;
import studentManagement.persistant.dto.StudentResponseDTO;
import studentManagement.service.CourseServiceImp;
import studentManagement.service.StudentServiceImp;

/**
 * Servlet implementation class UpdateStudentServlet
 */
@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					String studentId = request.getParameter("studentId");
					StudentServiceImp simpl = new StudentServiceImp();
					List<StudentResponseDTO> studentList = simpl.selectAllStudent();
					CourseServiceImp impl = new CourseServiceImp();
					List<CourseResponseDTO> courses = impl.selectAllCourse(); 
					if(studentList.size() != 0) {
					StudentResponseDTO sdto = studentList.stream().filter(s -> s.getStudentId().equals(studentId)).findFirst().orElse(null);
					request.setAttribute("userinfo",sdto);
					}
					List<StudCourseResponseDTO> scList = simpl.allStudCourse();
					List<String> list = new ArrayList();
					if(scList.size() != 0) {
						for(StudCourseResponseDTO sc : scList) {
							if(sc.getStudentId().equals(studentId)) {
								list.add(sc.getCourseId());
							}
						}
						request.setAttribute("list",list);
					}
					request.setAttribute("courses",courses);
					request.getRequestDispatcher("StudentUpdate.jsp").forward(request,response);
					
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						StudentBean bean = new StudentBean();
						StudentServiceImp impl = new StudentServiceImp();
						CourseServiceImp cimpl = new CourseServiceImp();
						List<CourseResponseDTO> courses = cimpl.selectAllCourse();
					bean.setStudentId(request.getParameter("studentId"));
					bean.setStudentName(request.getParameter("studentName"));
					bean.setStudentDob(request.getParameter("studentDob"));
					bean.setStudentGender(request.getParameter("studentGender"));
					bean.setStudentPhone(request.getParameter("studentPhone"));
					bean.setStudentEducation(request.getParameter("studentEducation"));
					bean.setAttendCourse(request.getParameterValues("attendCourse"));
					List<String> list = new ArrayList<String>();
				if(bean.getAttendCourse() != null) {
					list = Arrays.asList(bean.getAttendCourse());
				}
				if(bean.getStudentId().equals("") || bean.getStudentName().equals("") || bean.getStudentDob().equals("") ||
				bean.getStudentGender() == null || bean.getStudentPhone().equals("") || bean.getStudentEducation().equals("") || bean.getAttendCourse() == null) {
				request.setAttribute("error","Field cannot be blank !");
				request.setAttribute("userinfo",bean);
				request.setAttribute("list",list);
				}else {
					int ans = impl.updateStudent(bean);
					if(ans == 0) {
						request.setAttribute("userinfo",bean);
						request.setAttribute("list",list);
						request.setAttribute("error","Student Update Failed !");
					}else {
						int i = impl.deleteStudCourse(bean);
						if(i != 0) {
							StudCourseRequestDTO cdto;
							for(String c : bean.getAttendCourse()) {
								cdto = new StudCourseRequestDTO();
								cdto.setStudentId(bean.getStudentId());
								cdto.setCourseId(c);
								impl.insertStudCourse(cdto);
							}
							request.setAttribute("msg","Student Update Successfully !");
						}
					}
				}
			
				request.setAttribute("courses",courses);
				request.getRequestDispatcher("StudentUpdate.jsp").forward(request,response);
	}

}
