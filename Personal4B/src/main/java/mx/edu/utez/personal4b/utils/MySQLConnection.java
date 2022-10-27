package mx.edu.utez.personal4b.utils;

import java.sql.*;

public class MySQLConnection {
    public static Connection getConnection(){
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","root");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void close(Connection conn, PreparedStatement pstm, ResultSet rs){
        try{
            if(conn!= null){
                conn.close();
            }
            if(pstm!=null){
                pstm.close();
            }
            if(rs!= null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            Connection connection =getConnection();
            if(connection!= null){
                System.out.println("Conectado");
            }else{
                System.out.println("No conectado");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}