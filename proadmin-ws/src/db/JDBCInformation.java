/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Samir
 */
public interface JDBCInformation {
	
	String DRIVER = "com.mysql.jdbc.Driver";
	String HOST = "localhost:3306";
	String DB = "proadmin";
	String URL = "jdbc:mysql://" + HOST + "/" + DB;
	String USER = "root";
	String PASSWORD = "";
}
