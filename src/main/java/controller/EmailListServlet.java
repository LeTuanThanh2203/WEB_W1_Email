package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.User;

import java.io.IOException;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/index.jsp";

        // Get current action
        String action = request.getParameter("action");

        if (action == null) {
            action = "join";
        }

        // Perform action
        if (action.equals("join")) {

            // Go to the join page
            url = "/index.jsp";

        } else if (action.equals("add")) {

            // Get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // Create User object
            User user = new User(firstName, lastName, email);

            // Store User object in request
            request.setAttribute("user", user);

            // Go to thanks page
            url = "/thanks.jsp";
        }

        // Forward request to the selected page
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}