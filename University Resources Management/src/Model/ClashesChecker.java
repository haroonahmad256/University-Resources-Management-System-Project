package Model;

import Database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Date;

public class ClashesChecker {
    DBConnection connection;

    public boolean teacherClashWarning(JSpinner startTime, JSpinner endTime, JComboBox<String> day, JComboBox<String> id){
        connection = new DBConnection();
        String sqlQuery= "SELECT * FROM sechdules WHERE teacherId=? And classDay=? And ?<endTime AND ?>startTime";
        try(PreparedStatement statement = connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, id.getSelectedItem().toString());
            statement.setString(2, day.getSelectedItem().toString());

            Date startDate = (Date) startTime.getValue();
            Date endDate = (Date) endTime.getValue();

            Time start = new Time(startDate.getTime());
            Time end = new Time(endDate.getTime());

            statement.setTime(3, start);
            statement.setTime(4, end);
            ResultSet resultSet= statement.executeQuery();
            if(!resultSet.next()){
                return false;
            }
            else{
                System.out.println("Clash Detected");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean roomClashWarning(JSpinner startTime, JSpinner endTime, JComboBox<String> day, JComboBox<String> id){
        connection = new DBConnection();
        String sqlQuery= "SELECT * FROM sechdules WHERE roomId=? And classDay=? And ?<endTime AND ?>startTime";
        try(PreparedStatement statement = connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, id.getSelectedItem().toString());
            statement.setString(2, day.getSelectedItem().toString());

            Date startDate = (Date) startTime.getValue();
            Date endDate = (Date) endTime.getValue();

            Time start = new Time(startDate.getTime());
            Time end = new Time(endDate.getTime());

            statement.setTime(3, start);
            statement.setTime(4, end);
            ResultSet resultSet= statement.executeQuery();
            if(!resultSet.next()){
                return false;
            }
            else{
                System.out.println("Clash Detected");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean batchClashWarning(JSpinner startTime, JSpinner endTime, JComboBox<String> day, JComboBox<String> id){
        connection= new DBConnection();
        String sqlQuery= "SELECT * FROM sechdules WHERE batch_id=? And classDay=? And ?<endTime AND ?>startTime";
        try(PreparedStatement statement = connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, id.getSelectedItem().toString());
            statement.setString(2, day.getSelectedItem().toString());

            Date startDate = (Date) startTime.getValue();
            Date endDate = (Date) endTime.getValue();

            Time start = new Time(startDate.getTime());
            Time end = new Time(endDate.getTime());

            statement.setTime(3, start);
            statement.setTime(4, end);
            ResultSet resultSet= statement.executeQuery();
            if(!resultSet.next()){
                return false;
            }
            else{
                System.out.println("Clash Detected");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public void checkResources(JComboBox<String> resourceList, JSpinner startTime, JSpinner endTime, JComboBox<String> day, JTextField id, JLabel statusLabel){
        connection=  new DBConnection();
        String resources = (String) resourceList.getSelectedItem();
        if (resources.equalsIgnoreCase("Teacher")){
            String sqlQuerySchedule = "SELECT * FROM sechdules WHERE teacherId=?";

            try (PreparedStatement statementSchedule =
                         connection.getDatabaseConnection().prepareStatement(sqlQuerySchedule)) {

                statementSchedule.setString(1, id.getText());
                ResultSet resultSet = statementSchedule.executeQuery();

                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Teacher doesn't exist!");
                    return;
                }

                Date startDate = (Date) startTime.getValue();
                Date endDate = (Date) endTime.getValue();

                Time start = new Time(startDate.getTime());
                Time end = new Time(endDate.getTime());

                boolean isAvailable = true;

                do {
                    Time dbStart = resultSet.getTime("startTime");
                    Time dbEnd = resultSet.getTime("endTime");

                    String dayFromDatabase= resultSet.getString("classDay").trim();
                    String daySelected= day.getSelectedItem().toString().trim();

                    System.out.println("Db day: "+dayFromDatabase);
                    System.out.println("selected Day: "+daySelected);
                    if (start.before(dbEnd) && end.after(dbStart) && daySelected.equalsIgnoreCase(dayFromDatabase)) {
                        isAvailable = false;
                        break;
                    }

                } while (resultSet.next());

                if (!isAvailable) {
                    statusLabel.setText("Not Available");
                    JOptionPane.showMessageDialog(null, "Teacher is not available");
                } else {
                    statusLabel.setText("Available");
                    JOptionPane.showMessageDialog(null, "Teacher is available");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if(resources.equalsIgnoreCase("Room")){
            String sqlQuerySchedule= "SELECT * FROM sechdules WHERE roomId=?";
            try (PreparedStatement statementSchedule =
                         connection.getDatabaseConnection().prepareStatement(sqlQuerySchedule)) {

                statementSchedule.setString(1, id.getText());
                ResultSet resultSet = statementSchedule.executeQuery();

                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Room doesn't exist!");
                    return;
                }

                Date startDate = (Date) startTime.getValue();
                Date endDate = (Date) endTime.getValue();

                Time start = new Time(startDate.getTime());
                Time end = new Time(endDate.getTime());

                boolean isAvailable = true;

                do {
                    Time dbStart = resultSet.getTime("startTime");
                    Time dbEnd = resultSet.getTime("endTime");
                    String dayFromDatabase= resultSet.getString("classDay").trim();
                    String daySelected= day.getSelectedItem().toString().trim();

                    if (start.before(dbEnd) && end.after(dbStart) && daySelected.equalsIgnoreCase(dayFromDatabase)) {
                        isAvailable = false;
                        break;
                    }
                } while (resultSet.next());

                if (!isAvailable) {
                    statusLabel.setText("Not Available");
                    JOptionPane.showMessageDialog(null, "Room is not available");
                } else {
                    statusLabel.setText("Available");
                    JOptionPane.showMessageDialog(null, "Room is available");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if(resources.equalsIgnoreCase("Batch")) {
            String sqlQuerySchedule = "SELECT * FROM sechdules WHERE batch_id=?";
            try (PreparedStatement statementSchedule =
                         connection.getDatabaseConnection().prepareStatement(sqlQuerySchedule)) {

                statementSchedule.setString(1, id.getText());
                ResultSet resultSet = statementSchedule.executeQuery();

                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Batch doesn't exist!");
                    return;
                }

                Date startDate = (Date) startTime.getValue();
                Date endDate = (Date) endTime.getValue();

                Time start = new Time(startDate.getTime());
                Time end = new Time(endDate.getTime());

                boolean isAvailable = true;

                do {
                    Time dbStart = resultSet.getTime("startTime");
                    Time dbEnd = resultSet.getTime("endTime");

                    String dayFromDatabase= resultSet.getString("classDay").trim();
                    String daySelected= day.getSelectedItem().toString().trim();

                    if (start.before(dbEnd) && end.after(dbStart) && daySelected.equalsIgnoreCase(dayFromDatabase)) {
                        isAvailable = false;
                        break;
                    }

                } while (resultSet.next());

                if (!isAvailable) {
                    statusLabel.setText("Not Available");
                    JOptionPane.showMessageDialog(null, "Batch is not available");
                } else {
                    statusLabel.setText("Available");
                    JOptionPane.showMessageDialog(null, "Batch is available");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public void setDashBoardTable(JTable table){
        String[] columnNames= {"Schedule ID", "Teacher ID", "Room ID", "Batch ID", "Class Day", "Start Time", "End Time"};
        DefaultTableModel tableModel= new DefaultTableModel(columnNames, 0);
        connection= new DBConnection();
        connection.getDatabaseConnection();
        String sqlQuery= "SELECT * FROM sechdules \n" +
                "ORDER BY FIELD(classDay, 'Monday', 'Tuesday' , 'Wednesday', 'Thursday', 'Friday') LIMIT 10;";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            ResultSet resultSet= statement.executeQuery();

            while(resultSet.next()){
                String scheduleId= resultSet.getString("schedule_id");
                String teacherId= resultSet.getString("teacherId");
                String roomId= resultSet.getString("roomId");
                String batchId= resultSet.getString("batch_id");
                String classDay= resultSet.getString("classDay");
                Time startTime= resultSet.getTime("startTime");
                Time endTime= resultSet.getTime("endTime");

                Object[] row= {scheduleId, teacherId, roomId, batchId, classDay, startTime, endTime};
                tableModel.addRow(row);
            }
            table.setModel(tableModel);
        }
        catch(SQLException e){
            System.out.println("Something Bad Happened!");
        }
    }


}
