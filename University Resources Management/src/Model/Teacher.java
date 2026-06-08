package Model;

import Database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Teacher {
    private String teacherID;
    private String name;
    private String department;
    private String email;
    private String specilization;
    private int weeklyHours;

    public Teacher(){}

    public Teacher(String teacherID, String name, String department, String email, String specilization, int weeklyHours){
        this.teacherID= teacherID;
        this.name= name;
        this.department= department;
        this.email= email;
        this.specilization= specilization;
        this.weeklyHours= weeklyHours;
    }

    //GetterSetter
    String getTeacherID(){
        return this.teacherID;
    }
    String getName(){
        return this.name;
    }
    String getDepartment(){
        return this.department;
    }
    String getEmail(){
        return this.email;
    }
    String getSpecilization(){
        return this.specilization;
    }
    int getWeeklyHours(){
        return this.weeklyHours;
    }
    void setTeacherID(String teacherID){
        this.teacherID= teacherID;
    }
    void setName(String name){
        this.name= name;
    }
    void setDepartment(String department){
        this.department= department;
    }
    void setEmail(String email){
        this.email= email;
    }
    void setSpecilization(String specilization){
        this.specilization= specilization;
    }
    void setWeeklyHours(int weeklyHours){
        this.weeklyHours= weeklyHours;
    }
    DBConnection connection;

    public void showTeachersDataInGUITable(JTable table){
        connection= new DBConnection();
        String[] columnsName= {"Teacher ID", "Name", "Department", "Email", "Specialization", "Weekly Hours"};
        DefaultTableModel model= new DefaultTableModel(columnsName, 0);
        String sqlQuery= "SELECT * FROM teachers";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            ResultSet resultSet= statement.executeQuery();
            while(resultSet.next()){
                this.teacherID= resultSet.getString("teacherId");
                this.name= resultSet.getString("name");
                this.department= resultSet.getString("department");
                this.email= resultSet.getString("emailAddress");
                this.specilization= resultSet.getString("specialization");
                this.weeklyHours= resultSet.getInt("weeklyHours");

                Object[] row= {teacherID, name, department, email, specilization, weeklyHours};
                model.addRow(row);
            }
            table.setModel(model);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void totalTeachers(JLabel label){
        connection= new DBConnection();
        String sqlQuery= "SELECT COUNT(teacherId) FROM teachers";
        try(PreparedStatement statement = connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            ResultSet resultSet= statement.executeQuery();
            resultSet.next();
            int count= resultSet.getInt(1);
            label.setText(String.valueOf(count));
            System.out.println("Success || count: "+count);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void FetchTeacherInfo(JTextField teacherId, JTextField teacherFullName, JTextField department, JTextField specialization, JTextField email, JSpinner weeklyHours){
        this.teacherID= teacherId.getText();
        connection= new DBConnection();
        String sqlQuery= "Select * FROM teachers WHERE teacherId= ?";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, this.teacherID);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "Invalid Teacher ID! Please Enter Valid Teacher ID");
                return;
            }
            teacherFullName.setText(resultSet.getString("name"));
            department.setText(resultSet.getString("department"));
            specialization.setText(resultSet.getString("specialization"));
            email.setText(resultSet.getString("emailAddress"));
            weeklyHours.setValue(resultSet.getInt("weeklyHours"));
            System.out.println("All value Fetched");

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    public void fetchDeleteTeacherInfo(JTextField teacherId, JLabel teacherName, JLabel teacherDepartment, JLabel teacherSpecilization, JLabel warning, JLabel teacherFoundNOt){
        connection= new DBConnection();
        String sqlQuery= "SELECT * FROM teachers WHERE teacherId= ?";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, teacherId.getText());
            ResultSet resultSet= statement.executeQuery();
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "Invalid Teacher ID! Please Enter Valid Teacher ID");
                return;
            }

            teacherFoundNOt.setText("Teacher Found");
            teacherFoundNOt.setForeground(Color.green);
            teacherName.setText("Teacher Name: "+resultSet.getString("name"));
            teacherDepartment.setText("Department: "+resultSet.getString("department"));
            teacherSpecilization.setText("Specilization: "+resultSet.getString("specialization"));
            warning.setText("Are you sure you want to delete Teacher? This Action can not be undone...");
            System.out.println("All done");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
