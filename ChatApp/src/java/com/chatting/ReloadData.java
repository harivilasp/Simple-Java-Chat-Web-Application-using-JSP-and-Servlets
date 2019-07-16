package com.chatting;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class ReloadData
  extends HttpServlet
{
  public ReloadData() {}
  
  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    try
    {
      paramHttpServletResponse.setContentType("text/html");
      
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection localConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "root");
      String str1 = "select*from chatting_data";
      PreparedStatement localPreparedStatement = localConnection.prepareStatement(str1);
      
      ResultSet localResultSet = localPreparedStatement.executeQuery();
      
      while (localResultSet.next()) {
        String str2 = localResultSet.getString(1);
        String str3 = localResultSet.getString(2);
        String str4 = localResultSet.getString(3);
        String str5 = localResultSet.getString(4);
        PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
        localPrintWriter.print("<p>" + str2 + "-<g>" + str3 + "</g><br><small>" + str4 + " " + str5 + "</small></p>");
      }
      localConnection.close();

    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      System.out.println("Something went wrong");
    }
  }
  

  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    doGet(paramHttpServletRequest, paramHttpServletResponse);
  }
}