package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/contactForm")
public class subContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public subContact() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String cname=request.getParameter("name");
		String cemail=request.getParameter("email");
	String cphone	=request.getParameter("phone");
	String cmessage	=request.getParameter("message");
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/equation","root","");
		PreparedStatement ps=con.prepareStatement("insert into userdata (cname,cemail,cphone,cmessage)values(?,?,?,?)");
		
		ps.setString(1, cname);
		ps.setString(2, cemail);
		ps.setString(3, cphone);
		ps.setString(4, cmessage);
		
		int rowCount=ps.executeUpdate();
//		dispatcher=request.getRequestDispatcher("registration.jsp");
		
		if(rowCount>0) {
//			request.setAttribute("status", "success");
			pw.print("<h1>account is created successfully</h1>");
			request.getRequestDispatcher("registration.jsp").include(request, response);
		}
		else{
			pw.print("<h1>sorry unable to create account</h1>");
			request.getRequestDispatcher("registration.jsp").include(request, response);
//			request.setAttribute("status", "fail");
			
			pw.close();
		}
//		dispatcher.forward(request, response);
	}
	catch(Exception e) {
		e.printStackTrace();
	
	}
	}
		
		
		
		
	}


