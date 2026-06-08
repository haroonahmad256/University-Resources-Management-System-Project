package Model;

import Database.DBConnection;
import com.mysql.cj.xdevapi.SqlStatement;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

public class Login {
    public static String userName;
    public static String password;
    DBConnection connection;

    public static String NameUser;
    public static String role;

    public boolean loginLogic(JTextField user, JTextField pass) {
        userName= user.getText();
        password= pass.getText();
        connection = new DBConnection();
        String sqlQuery = "SELECT * FROM users WHERE userName=? AND accPassword=?";
        try (PreparedStatement statement = connection.getDatabaseConnection().prepareStatement(sqlQuery)) {
            statement.setString(1, user.getText());
            statement.setString(2, pass.getText());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                return false;
            } else {
                System.out.println("Successfully Login for " + resultSet.getString("userName"));
                NameUser= resultSet.getString("firstName")+" "+resultSet.getString("lastName");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean createAccount(JTextField firstName, JTextField lastName, JTextField userName, JTextField emailAddress, JPasswordField password) {
        connection = new DBConnection();
        int rowsAffected;
        boolean shouldCreaet = false;
        String sqlQuery = "INSERT INTO users (userName, accPassword, emailId, firstName, lastName) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.getDatabaseConnection().prepareStatement(sqlQuery)) {
            statement.setString(1, userName.getText());
            statement.setString(2, password.getText());
            statement.setString(3, emailAddress.getText());
            statement.setString(4, firstName.getText());
            statement.setString(5, lastName.getText());
            rowsAffected = statement.executeUpdate();
            if (rowsAffected < 1) {
                JOptionPane.showMessageDialog(null, "Couldn't Add Account! Try Again");
            } else {
                JOptionPane.showMessageDialog(null, "Account Successfully Created! Move on to Login");
                shouldCreaet = true;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                String message = e.getMessage();
                if (message.contains("PRIMARY") || message.contains("id_UNIQUE")) {
                    JOptionPane.showMessageDialog(null, "ID ALready Exist! Please Enter a different ID");
                } else if (message.contains("users.emailId")) {
                    JOptionPane.showMessageDialog(null, "Email Already Exist! Use different Email");
                } else {
                    JOptionPane.showMessageDialog(null, "Duplicate Entry Detected");
                }
            }
            System.out.println(e.getMessage());
        }
        return shouldCreaet;
    }

    public boolean verifyEmailForPasswordReset(JTextField email, JLabel status) {
        connection = new DBConnection();
        boolean existence = false;
        String finEmailQuery = "SELECT * FROM users WHERE emailId=?";
        try (PreparedStatement statementEmailConfirm = connection.getDatabaseConnection().prepareStatement(finEmailQuery)) {
            statementEmailConfirm.setString(1, email.getText());
            ResultSet resultSet = statementEmailConfirm.executeQuery();
            if (!resultSet.next()) {
                status.setText("Email Not Found");
            } else {
                status.setText("Email Found");
                existence = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return existence;
    }

    public boolean changePassword(JTextField email, JTextField password, JTextField confirmPass) {
        connection= new DBConnection();
        int rowsAffected;
        boolean passwordChangeNot= false;
        String sqlQuery = "UPDATE users SET accPassword= ? WHERE emailId=?";

        try (PreparedStatement statement = connection.getDatabaseConnection().prepareStatement(sqlQuery);) {
            if (Objects.equals(password.getText(), confirmPass.getText())) {
                statement.setString(1, password.getText());
                statement.setString(2, email.getText());
                rowsAffected = statement.executeUpdate();
                if (rowsAffected < 1) {
                    JOptionPane.showMessageDialog(null, "Couldn't Change Password! Try Again");
                } else {
                    JOptionPane.showMessageDialog(null, "Password Changed Successfully");
                    passwordChangeNot= true;
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Both Passwords Should Be Equal");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return passwordChangeNot;
    }
}

