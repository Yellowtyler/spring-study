package com.study.daniil.servlets;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="NumResultServlet", urlPatterns = "/numResult.html")
public class NumResultServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Integer num = Integer.valueOf(req.getParameter("num"));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Result</title>\n" +
                    "<style>\n" +
                        "table, th, td {\n" +
                        "  border: 1px solid black;\n" +
                        "}\n" +
                    "</style>" +
                "</head>" +
                "<html><body>" +
                "<table> ");
        for (int i = 0; i < num; i++) {
            stringBuilder.append("<tr>");
            for (int j = 0; j < num; j++) {
                stringBuilder.append("<td>" + String.valueOf(i+1) + "-" + String.valueOf(j+1) + "</td>");
            }
            stringBuilder.append("</tr>");
        }
        stringBuilder.append(" </table>" +
                "</body></html>");

        writer.println(stringBuilder.toString());
        writer.close();
    }
}
