package Model;

import Database.DBConnection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO extends CRUDOperations<Teacher>{
    DBConnection connection;
    @Override
    public void create(Teacher teacher) {
        connection= new DBConnection();
        int rowsAffected;
        String sqlQuery= "INSERT INTO teachers (teacherId, name, department, emailAddress, specialization, weeklyHours) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, teacher.getTeacherID());
            statement.setString(2, teacher.getName());
            statement.setString(3, teacher.getDepartment());
            statement.setString(4, teacher.getEmail());
            statement.setString(5, teacher.getSpecilization());
            statement.setInt(6, teacher.getWeeklyHours());
            rowsAffected= statement.executeUpdate();
            System.out.println("Teacher Added || rows Affected: "+rowsAffected);
            JOptionPane.showMessageDialog(null, "Teacher Added Successfully");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        connection= new DBConnection();
        int rowsAffected;
        String sqlQuery= "DELETE FROM teachers WHERE teacherId= ?";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, id);
            rowsAffected = statement.executeUpdate();
            if(rowsAffected<1){
                JOptionPane.showMessageDialog(null, "Couldn't Delete Teacher! Please Try Again");
            }
            System.out.println("All Done || rows Affected: "+rowsAffected);
            JOptionPane.showMessageDialog(null, "Teacher Data Deleted");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void update(Teacher teacher) {
        connection= new DBConnection();
        int rowsAffected;
        String updateNameSqlQuery = "UPDATE teachers SET name=?, department=?, emailAddress=?, specialization=?, weeklyHours=? WHERE teacherId=?";
        try(PreparedStatement statementUpdate = connection.getDatabaseConnection().prepareStatement(updateNameSqlQuery))
        {
            statementUpdate.setString(1, teacher.getName());
            statementUpdate.setString(2, teacher.getDepartment());
            statementUpdate.setString(3, teacher.getEmail());
            statementUpdate.setString(4, teacher.getSpecilization());
            statementUpdate.setInt(5, (Integer) teacher.getWeeklyHours());
            statementUpdate.setString(6, teacher.getTeacherID());
            rowsAffected= statementUpdate.executeUpdate();
            if(rowsAffected<1){
                JOptionPane.showMessageDialog(null, "Couldn't Update Teacher! Please Try Again");
                return;
            }
            System.out.println("All Done || Rows Affected: "+rowsAffected);
            JOptionPane.showMessageDialog(null, "Teacher Updated Successfully");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public boolean searchTeacher(JTextField idField, JLabel id, JLabel name, JLabel department, JLabel email, JLabel specilization, JLabel weeklyHours){
        connection= new DBConnection();
        String sqlQuery= "SELECT * FROM teachers WHERE teacherId=? OR name=?";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, idField.getText());
            statement.setString(2, idField.getText());
            ResultSet resultSet= statement.executeQuery();
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "Teacher doesn't exist. Please enter a valid id");
                return false;
            }

            name.setText(resultSet.getString("name"));
            department.setText(String.valueOf(resultSet.getString("department")));
            email.setText(String.valueOf(resultSet.getString("emailAddress")));
            specilization.setText(resultSet.getString("specialization"));
            weeklyHours.setText(String.valueOf(resultSet.getInt("weeklyHours")));
            id.setText(resultSet.getString("teacherId"));

        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return true;
    }
}
