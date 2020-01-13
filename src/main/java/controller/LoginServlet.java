package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse res
    ){
        try {


            Boolean loggedIn = (Boolean) req.getSession().getAttribute("loggedIn");

            if(loggedIn != null && loggedIn) {
                req.getRequestDispatcher("/WEB-INF/auth/profile.jsp").forward(req,res);
                return;
            }

            req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req,res);

        } catch(IOException | ServletException ex) {
            System.out.printf("ERROR: %s\n", ex);
        }


    }


    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            System.out.println(username);
            System.out.println(password);

            if(username.equals("admin"))
                if(password.equals("password")){
                    req.getSession().setAttribute("loggedIn", true);
                    req.getRequestDispatcher("/WEB-INF/auth/profile.jsp").forward(req,res);
                    return;
                }

            res.sendRedirect("/login?alert=true");

        } catch(IOException | ServletException ex) {
            System.out.printf("ERROR: %s\n", ex);
        }

    }


}
