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
public class EditS extends HttpServlet {

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
        
        String snume = request.getParameter("snume");
        String sprenume = request.getParameter("sprenume");
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
             /*   String sql = "UPDATE contact SET "
            +"nume='"+nume+"' , prenume='"+prenume+"' , telefonMobil= '"+telefonMobil+"' "
            +", telefonFix='"+telefonFix+"' , email='"+email+"' , oras='"+oras+"' "
            +", judet='"+judet+"' , codPostal='"+codPostal+"' , adresa='"+adresa+"' "
            +"where nume='"+snume+"' and prenume='"+sprenume+"'";
             */
             if(nume != null){
                 String sql = "UPDATE contact SET nume='"+nume+"' WHERE nume='"+snume+"' AND prenume='"+sprenume+"'";
                 myStmt.executeUpdate(sql);
             }
             if(prenume != null){
                 String sql = "UPDATE contact SET prenume='"+prenume+"' WHERE nume='"+snume+"' AND prenume='"+sprenume+"'";
                 myStmt.executeUpdate(sql);
             }
             if(telefonMobil != null){
                 String sql = "UPDATE contact SET telefonMobil='"+telefonMobil+"' WHERE nume='"+snume+"' AND prenume='"+sprenume+"'";
                 myStmt.executeUpdate(sql);
             }
            if(telefonFix != null){
                 String sql = "UPDATE contact SET telefonFix='"+telefonFix+"' WHERE nume='"+snume+"' AND prenume='"+sprenume+"'";
                 myStmt.executeUpdate(sql);
             }
            if(email != null){
                 String sql = "UPDATE contact SET email='"+email+"' WHERE nume='"+snume+"' AND prenume='"+sprenume+"'";
                 myStmt.executeUpdate(sql);
             }
            if(adresa != null){
                 String sql = "UPDATE contact SET adresa='"+adresa+"' WHERE nume='"+snume+"' AND prenume='"+sprenume+"'";
                 myStmt.executeUpdate(sql);
             }
            if(oras != null){
                 String sql = "UPDATE contact SET oras='"+oras+"' WHERE nume='"+snume+"' AND prenume='"+sprenume+"'";
                 myStmt.executeUpdate(sql);
             }
            if(judet != null){
                 String sql = "UPDATE contact SET judet='"+judet+"' WHERE nume='"+snume+"' AND prenume='"+sprenume+"'";
                 myStmt.executeUpdate(sql);
             }
            if(codPostal != null){
                 String sql = "UPDATE contact SET codPostal='"+codPostal+"' WHERE nume='"+snume+"' AND prenume='"+sprenume+"'";
                 myStmt.executeUpdate(sql);
             }
                //4.Process the result set
        
            try (PrintWriter out = response.getWriter()) {
            
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet SearchS</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<center>");
                out.println("<h2>Modificarea datelor s-a realizat cu succes !</h2?");
                out.println("</center>");
                out.println("</body>");
                out.println("</html>");
            } // end PrintWriter try
            
        }catch(SQLException | ClassNotFoundException se){
        //Handle errors for JDBC
        }
        //Handle errors for Class.forName
        finally{
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
