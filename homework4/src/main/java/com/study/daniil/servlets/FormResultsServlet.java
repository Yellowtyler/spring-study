package com.study.daniil.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name="FormResultsServlet", urlPatterns = "/formResults.html")
public class FormResultsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String city = req.getParameter("city");
        writer.printf("<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Result</title>\n" +
                    "<style>\n" +
                    "table, th, td {\n" +
                    "  border: 1px solid black;\n" +
                    "}\n" +
                    "</style>" +
                "</head>" +
                "<html><body>" +
                "<table> " +
                    "<tr>" +
                        "<td>Name</td>" +
                        "<td>" + name + "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td>Date</td>" +
                        "<td>" + date + "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td>City</td>" +
                        "<td>" + city + "</td>" +
                    "</tr>" +
                " </table>" +
                "</body></html>");
        writer.close();
    }
}
