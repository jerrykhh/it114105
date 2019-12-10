/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.db;

import java.io.IOException;
import java.sql.*;
import system.bean.LoginBean;

/**
 *
 * @author jerrykwok
 */
public class LoginValid {

    private String url;
    private String username;
    private String password;

    public LoginValid(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return DriverManager.getConnection(url, username, password);
    }

    public boolean validateStaffLogin(LoginBean loginBean) {
        boolean isSuccess = false;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Teacher WHERE id=? and password=? AND title=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, loginBean.getUsername());
            pStmnt.setString(2, loginBean.getPassword());
            pStmnt.setString(3, loginBean.getRole());
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean validateStudentLogin(LoginBean loginBean) {
        boolean isSuccess = false;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM Student WHERE id=? and password=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, loginBean.getUsername());
            pStmnt.setString(2, loginBean.getPassword());
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
}
