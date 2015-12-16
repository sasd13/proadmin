/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.db;

/**
 *
 * @author Samir
 */
public class JDBCInformation {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String HOST = "localhost:3306";
    public static final String DB ="proadmin";
    public static final String URL = "jdbc:mysql://" + HOST + "/" + DB;
    public static final String USER ="root"; 
    public static final String PASSWORD ="";
}
