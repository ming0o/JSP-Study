package tukorea.websvc.club.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tukorea.websvc.club.domain.StudentVO;
import tukorea.websvc.club.persistence.StudentDAO;

@WebServlet(urlPatterns={"/StudentServlet"})
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 이동할 URL 패턴이나 페이지를 설정
        String targetPage = "register.html";

        // RequestDispatcher를 사용하여 forward
        RequestDispatcher view = request.getRequestDispatcher(targetPage);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String cmdReq = "";
        cmdReq = request.getParameter("cmd");

        if (cmdReq.equals("join")) {
            StudentVO studentVO = new StudentVO();

            studentVO.setId(request.getParameter("id"));
            studentVO.setPasswd(request.getParameter("passwd"));
            studentVO.setUsername(request.getParameter("username"));
            studentVO.setSnum(request.getParameter("snum"));
            studentVO.setDepart(request.getParameter("depart"));
            studentVO.setMobile(request.getParameter("mobile"));
            studentVO.setEmail(request.getParameter("email"));

            StudentDAO studentDAO = new StudentDAO();

            String message = "";
            if (studentDAO.join(studentVO))
                message = "가입 성공";
            else
                message = "가입 실패";
            request.setAttribute("message", message);
            
            
         // 학생 정보를 JSP 페이지로 전달
            request.setAttribute("id", studentVO.getId());
            request.setAttribute("username", studentVO.getUsername());
            request.setAttribute("snum", studentVO.getSnum());
            request.setAttribute("depart", studentVO.getDepart());
            request.setAttribute("mobile", studentVO.getMobile());
            request.setAttribute("email", studentVO.getEmail());
            
        }

        // 결과를 result.jsp로 전달
        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        view.forward(request, response);
    }
}
