package tukorea.web.club.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import tukorea.web.club.domain.StudentVO;
import tukorea.web.club.persistence.StudentDAO;

@WebServlet(urlPatterns={"/StudentServlet"})
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmdReq = request.getParameter("cmd");

        if (cmdReq.equals("join")) {
            response.sendRedirect("register.html");
            
        } else if (cmdReq.equals("list")) {
            StudentDAO dao = new StudentDAO();
            ArrayList<StudentVO> studentList = dao.getStudentList();
            request.setAttribute("studentList", studentList);
            RequestDispatcher view = request.getRequestDispatcher("student_list.jsp");
            view.forward(request, response);
            
        } else if(cmdReq.equals("delete")) {
        	StudentDAO dao = new StudentDAO();
        	String strId = request.getParameter("id");
        	dao.delete(strId);
        	
        	ArrayList<StudentVO> studentList = dao.getStudentList();
        	request.setAttribute("studentList", studentList);
        	RequestDispatcher view = request.getRequestDispatcher("student_list.jsp");
        	view.forward(request, response);
        	
        } else if(cmdReq.equals("update")) {
        	StudentDAO dao = new StudentDAO();
        	StudentVO student = dao.read(request.getParameter("id"));
        	request.setAttribute("student", student);
        	RequestDispatcher view = request.getRequestDispatcher("update.jsp");
        	view.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
    	String cmdReq = request.getParameter("cmd");
        String message = "";

        if (cmdReq.equals("join")) {
            StudentVO studentVO = new StudentVO();

            studentVO.setId(request.getParameter("id"));
            studentVO.setPasswd(request.getParameter("passwd"));
            studentVO.setUsername(request.getParameter("username"));
            studentVO.setSnum(request.getParameter("snum"));
            studentVO.setDepart(request.getParameter("depart"));
            studentVO.setMobile(request.getParameter("mobile"));
            studentVO.setEmail(request.getParameter("email"));

            StudentDAO studentDao = new StudentDAO();
            
     
            if (studentDao.add(studentVO)) {
                message = "가입 축하합니다";
            } else {
                message = "가입 실패입니다";
            }

            request.setAttribute("greetings", message);
            request.setAttribute("student", studentVO);

            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request, response);
            
        }
        else if(cmdReq.equals("update")) {
        	StudentVO studentVO = new StudentVO();
        	
        	studentVO.setId(request.getParameter("id"));
            studentVO.setPasswd(request.getParameter("passwd"));
            studentVO.setUsername(request.getParameter("username"));
            studentVO.setSnum(request.getParameter("snum"));
            studentVO.setDepart(request.getParameter("depart"));
            studentVO.setMobile(request.getParameter("mobile"));
            studentVO.setEmail(request.getParameter("email"));
            
            StudentDAO dao = new StudentDAO();
            if(dao.update(studentVO)) message = "수정이 완료되었습니다.";
            else message = "수정 실패입니다.";
            
            request.setAttribute("greetings", message);
            request.setAttribute("student", studentVO);
            
            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request, response);
        }
    }
}
