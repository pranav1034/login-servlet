package com.bridgelabz.servlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description="Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name="user",value = "Pranav"),
                @WebInitParam(name="password",value = "pranav123")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String username = req.getParameter("user");
        String pass = req.getParameter("pwd");

        //get servlet config
        String userId = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        if(userId.equals(username) && password.equals(pass)){
            req.setAttribute("user", username);
            RequestDispatcher rd = req.getRequestDispatcher("LoginSuccess.jsp");
            rd.forward(req, res);
        }
        else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = res.getWriter();
            out.println("<font color='red'>Invalid username or password</font>");
            rd.include(req,res);
        }

    }
}
