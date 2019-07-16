package com.chatting;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChatStore
  extends HttpServlet
{
  public ChatStore() {}
  
  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException
  {
    PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
    try
    {
      paramHttpServletResponse.setContentType("text/html");
      
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection localConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "root");
      Statement localStatement = localConnection.createStatement();
      

      String str1 = paramHttpServletRequest.getParameter("uname");
      String str2 = paramHttpServletRequest.getParameter("msg");
      
      SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
      SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("hh:mm:ss a");
      Date localDate = new Date();
      String str3 = localSimpleDateFormat1.format(localDate);
      String str4 = localSimpleDateFormat2.format(localDate);
      
      String str5 = "insert into chatting_data values('" + str1 + "','" + str2 + "','" + str3 + "','" + str4 + "',seq_chatting_data.nextval)";
      //Debugging
      System.out.println(str5);
      ResultSet localResultSet = localStatement.executeQuery(str5);
      
      localResultSet.next();
      localConnection.close();
    }
    catch (Exception localException) {
      localException.printStackTrace();
      System.out.println("Invalid User");
    }
  }
  
  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException
  {
    doGet(paramHttpServletRequest, paramHttpServletResponse);
  }
}