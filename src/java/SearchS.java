/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author MNI
 */
public class SearchS extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs;
        String user = "root";
        String pass = "parola11";
        
        String nume = request.getParameter("nume"); if( nume.isEmpty() ) nume = null;
        String prenume = request.getParameter("prenume"); if( prenume.isEmpty() ) prenume = null;
        String telefonMobil = request.getParameter("telefonMobil"); if( telefonMobil.isEmpty() ) telefonMobil = null;
        String telefonFix = request.getParameter("telefonFix"); if( telefonFix.isEmpty() ) telefonFix = null;
        String email = request.getParameter("email"); if( email.isEmpty() ) email = null;
        String adresa = request.getParameter("adresa"); if( adresa.isEmpty() ) adresa = null;
        String oras = request.getParameter("oras"); if( oras.isEmpty() ) oras = null;
        String judet = request.getParameter("judet"); if( judet.isEmpty() ) judet = null;
        String codPostal = request.getParameter("codPostal"); if( codPostal.isEmpty() ) codPostal = null;
        
        try{
                
                Class.forName("com.mysql.jdbc.Driver");
                //1.Get a connection to database
                myConn = DriverManager.getConnection("jdbc:mysql://localhost/phonebook",user,pass);
                //2.Create a statement
                myStmt = myConn.createStatement();
                //3.Execute SQL query
                String sql = "SELECT * FROM contact WHERE "+"NUME='"+nume+"' OR prenume='"+prenume+"' OR telefonMobil= '"+telefonMobil+"'"
                    +"OR telefonFix='"+telefonFix+"' OR email='"+email+"' OR adresa='"+adresa+"' OR oras='"+oras+"'"
                    +"OR judet='"+judet+"' OR codPostal='"+codPostal+"'";
                myRs = myStmt.executeQuery(sql);
                //4.Process the result set
        
            try (PrintWriter out = response.getWriter()) {
            
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet SearchS</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Au fost gasite: </h2>");    
                out.println("<table border=\"1\" style=\"width:100%\">");
                out.println("<tr>");
                out.println("<th>Nume</th>");
                out.println("<th>Prenume</th>");
                out.println("<th>Telefon mobil</th>");
                out.println("<th>Telefon fix</th>");
                out.println("<th>Email</th>");
                out.println("<th>Adresa</th>");
                out.println("<th>Oras</th>");
                out.println("<th>Judet</th>");
                out.println("<th>Cod postal</th>");
                out.println("</tr>");
                while(myRs.next()){
                out.println("<tr>");
                out.println("<td>"+myRs.getString("nume")+"</td>");
                out.println("<td>"+myRs.getString("prenume")+"</td>");
                out.println("<td>"+myRs.getString("telefonMobil")+"</td>");
                out.println("<td>"+myRs.getString("telefonFix")+"</td>");
                out.println("<td>"+myRs.getString("email")+"</td>");
                out.println("<td>"+myRs.getString("adresa")+"</td>");
                out.println("<td>"+myRs.getString("oras")+"</td>");
                out.println("<td>"+myRs.getString("judet")+"</td>");
                out.println("<td>"+myRs.getString("codPostal")+"</td>");
                out.println("</tr>");
            }
          
 
            out.println("</table>");
            
  
                out.println("</body>");
                out.println("</html>");
            } // end PrintWriter try
            catch(SQLException e){}
        }catch(SQLException se){
        //Handle errors for JDBC
        }catch(Exception e){
         //Handle errors for Class.forName
        }finally{
         //finally block used to close resources
         try{
            if(myStmt!=null)
               myStmt.close();
         }catch(SQLException se2){
         }// nothing we can do
         try{
            if(myConn!=null)
            myConn.close();
         }catch(SQLException se){
         }//end finally try
      } //end try
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
