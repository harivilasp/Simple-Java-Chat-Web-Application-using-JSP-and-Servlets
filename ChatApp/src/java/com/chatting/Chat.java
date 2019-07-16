package com.chatting;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Chat extends javax.servlet.http.HttpServlet
{
  public Chat() {}
  
  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException
  {
    PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
    try {
      paramHttpServletResponse.setContentType("text/html");
      
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection localConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "root");
      Statement localStatement = localConnection.createStatement();
      
      String str1 = paramHttpServletRequest.getParameter("uname");
      System.out.println(str1);
      String str2 = paramHttpServletRequest.getParameter("pw");
      System.out.println(str2);
      String str3 = "select*from chatting where username='" + str1 + "' AND password='" + str2 + "'";
      
      ResultSet localResultSet = localStatement.executeQuery(str3);
      if (localResultSet.next())
      {
        String str4 = localResultSet.getString("username");
        HttpSession localHttpSession = paramHttpServletRequest.getSession();
        localHttpSession.setAttribute("name", str4);
        localPrintWriter.println("Welcome, " + str4.toUpperCase());
        localPrintWriter.println("<a href='startchat.jsp'>let's Enter the Chat Room</a>");
        localPrintWriter.println("<a href='logout.jsp' class='logout-chat'>Logout</a>");
      } else {
        localPrintWriter.println("Incorrect Username or Password.");
      }
      

      localConnection.close();
    } catch (Exception localException) {
      localException.printStackTrace();
      System.out.println("Invalid User");
    }
  }
  
  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    doGet(paramHttpServletRequest, paramHttpServletResponse);
  }
}