package Model;

import Database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Schedule {
    private String scheduleId;
    private String teacherId;
    private String roomId;
    private String batchId;
    private String classDay;
    private Time startTime;
    private Time endTime;

    public Schedule(){
    }

    public Schedule(String scheduleId, String teacherId, String roomId, String batchId, String classDay, Time startTime, Time endTime){
        this.scheduleId= scheduleId;
        this.teacherId= teacherId;
        this.roomId= roomId;
        this.batchId= batchId;
        this.classDay= classDay;
        this.startTime= startTime;
        this.endTime= endTime;
    }

    String getScheduleId(){
        return this.scheduleId;
    }
    String getTeacherId(){
        return this.teacherId;
    }
    String getRoomId(){
        return this.roomId;
    }
    String getBatchId(){
        return this.batchId;
    }
    String getClassDay(){
        return this.classDay;
    }
    Time getStartTime(){
        return this.startTime;
    }
    Time getEndTime(){
        return this.endTime;
    }

    void setScheduleId(String scheduleId){
        this.scheduleId= scheduleId;
    }
    void setTeacherId(String teacherId){
        this.teacherId= teacherId;
    }
    void setRoomId(String roomId){
        this.roomId= roomId;
    }
    void setBatchId(String batchId){
        this.batchId= batchId;
    }
    void setClassDay(String classDay){
        this.classDay= classDay;
    }
    void setStartTime(Time startTime){
        this.startTime= startTime;
    }
    void setEndTime(Time endTime){
        this.endTime= endTime;
    }
    DBConnection dbConnection;

    public void showScheduleDataInGUITable(JTable mytable){

        String[] columnNames= {"Schedule ID", "Teacher ID", "Room ID", "Batch ID", "Class Day", "Start Time", "End Time"};
        DefaultTableModel tableModel= new DefaultTableModel(columnNames, 0);
        dbConnection= new DBConnection();
        dbConnection.getDatabaseConnection();
        String sqlQuery= "SELECT * FROM sechdules \n" +
                "ORDER BY FIELD(classDay, 'Monday', 'Tuesday' , 'Wednesday', 'Thursday', 'Friday');";
        try(PreparedStatement statement= dbConnection.getDatabaseConnection().prepareStatement(sqlQuery)){
            ResultSet resultSet= statement.executeQuery();

            while(resultSet.next()){
                this.scheduleId= resultSet.getString("schedule_id");
                this.teacherId= resultSet.getString("teacherId");
                this.roomId= resultSet.getString("roomId");
                this.batchId= resultSet.getString("batch_id");
                this.classDay= resultSet.getString("classDay");
                this.startTime= resultSet.getTime("startTime");
                this.endTime= resultSet.getTime("endTime");

                Object[] row= {this.scheduleId, this.teacherId, this.roomId, this.batchId, this.classDay, this.startTime, this.endTime};
                tableModel.addRow(row);
            }
            mytable.setModel(tableModel);
        }
        catch(SQLException e){
            System.out.println("Something Bad Happened!");
        }
    }

    public void addScheduleComboBoxValues(JComboBox<String> teacherId, JComboBox<String> batchId, JComboBox<String> roomID){
        String sqlQueryTeachers = "SELECT * FROM teachers";
        String sqlQueryBatch = "SELECT * FROM batches";
        String sqlQueryRoom= "SELECT * FROM rooms";
        dbConnection= new DBConnection();
        try(PreparedStatement statement= dbConnection.getDatabaseConnection().prepareStatement(sqlQueryTeachers) ;
            PreparedStatement statementBatch= dbConnection.getDatabaseConnection().prepareStatement(sqlQueryBatch);
            PreparedStatement statementRoom= dbConnection.getDatabaseConnection().prepareStatement(sqlQueryRoom))
        {
            ResultSet resultSet= statement.executeQuery();
            ResultSet resultSetBatch= statementBatch.executeQuery();
            ResultSet resultSetRoom= statementRoom.executeQuery();

            DefaultComboBoxModel<String> modelTeacherId= new DefaultComboBoxModel<>();
            DefaultComboBoxModel<String> modelBatchId= new DefaultComboBoxModel<>();
            DefaultComboBoxModel<String> modelRoomId= new DefaultComboBoxModel<>();

            while(resultSet.next()){
                this.teacherId = resultSet.getString("teacherId");
                modelTeacherId.addElement(this.teacherId);
            }
            while(resultSetRoom.next()){
                this.roomId= resultSetRoom.getString("roomId");
                modelRoomId.addElement(this.roomId);
            }
            while(resultSetBatch.next()){
                this.batchId= resultSetBatch.getString("bactch_id");
                modelBatchId.addElement(this.batchId);
            }
            teacherId.setModel(modelTeacherId);
            batchId.setModel(modelBatchId);
            roomID.setModel(modelRoomId);
            System.out.println("All Added");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addSchedule(JTextField scheduleId, JComboBox<String> teacher, JComboBox<String> batch, JComboBox<String> room, JComboBox<String> day, JSpinner startTime, JSpinner endTime){
        int rowsAffected;
        dbConnection= new DBConnection();
        String sqlQuery= "INSERT INTO sechdules (schedule_id, teacherId, roomId, batch_id, classDay, startTime, endTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement= dbConnection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, scheduleId.getText() );
            statement.setString(2, (String) teacher.getSelectedItem());
            statement.setString(3, (String) room.getSelectedItem());
            statement.setString(4, (String) batch.getSelectedItem());
            statement.setString(5, (String) day.getSelectedItem());
            Date startDate=(Date) startTime.getValue();
            Date endDate= (Date) endTime.getValue();
            Time start= new Time(startDate.getTime());
            Time end= new Time(endDate.getTime());
            statement.setTime(6, start);
            statement.setTime(7, end);
            rowsAffected= statement.executeUpdate();
            if(rowsAffected<1){
                JOptionPane.showMessageDialog(null, "Couldn't Add Schedule! Please Try Again");
                return;
            }
            System.out.println("Success || Rows Affected: "+rowsAffected);
            JOptionPane.showMessageDialog(null, "Schedule Added Successfully");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void FetchScheduleInfo(JTextField scheduleId, JComboBox<String> teacher, JComboBox<String> batch, JComboBox<String> room, JComboBox<String> day, JSpinner startTime, JSpinner endTime){
        this.scheduleId= scheduleId.getText();
        dbConnection= new DBConnection();
        String sqlQuery= "Select * FROM sechdules WHERE schedule_id= ?";
        try(PreparedStatement statement= dbConnection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, this.scheduleId);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "Invalid Schedule ID! Please Enter a valid ID");
                return;
            }
            teacher.setSelectedItem(resultSet.getString("teacherId"));
            batch.setSelectedItem(resultSet.getString("batch_id"));
            room.setSelectedItem(resultSet.getString("roomId"));
            day.setSelectedItem(resultSet.getString("classDay"));
            startTime.setValue(resultSet.getTime("startTime"));
            endTime.setValue(resultSet.getTime("endTime"));
            System.out.println("All value Fetched");

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void updateScheduleInfo(JTextField scheduleId, JComboBox<String> teacher, JComboBox<String> batch, JComboBox<String> room, JComboBox<String> day, JSpinner startTime, JSpinner endTime){
        dbConnection= new DBConnection();
        this.scheduleId=scheduleId.getText();
        int rowsAffected;
        String updateNameSqlQuery = "UPDATE sechdules SET teacherId=?, roomId=?, batch_id=?, classDay=?, startTime=?, endTime=? WHERE schedule_id=?";
        try(PreparedStatement statementUpdate = dbConnection.getDatabaseConnection().prepareStatement(updateNameSqlQuery))
        {
            statementUpdate.setString(1, (String) teacher.getSelectedItem());
            statementUpdate.setString(2, (String) room.getSelectedItem());
            statementUpdate.setString(3, (String) batch.getSelectedItem());
            statementUpdate.setString(4, (String) day.getSelectedItem());
            Date startDate= (Date) startTime.getValue();
            Date endDate= (Date) endTime.getValue();
            Time sqlStartTime= new Time(startDate.getTime());
            Time sqlEndTime= new Time(endDate.getTime());
            statementUpdate.setTime(5, sqlStartTime);
            statementUpdate.setTime(6, sqlEndTime);
            statementUpdate.setString(7, scheduleId.getText());
            rowsAffected= statementUpdate.executeUpdate();
            if(rowsAffected<1){
                JOptionPane.showMessageDialog(null, "Couldn't Update Schedule! Please Try Again");
                return;
            }
            System.out.println("All Done || Rows Affected: "+rowsAffected);

            JOptionPane.showMessageDialog(null, "Schedule Updated Successfully");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void fetchDeleteScheduleInfo(JTextField scheduleId, JLabel teacher, JLabel room, JLabel timeSlot, JLabel warning, JLabel teacherFoundNOt){
        dbConnection= new DBConnection();
        String sqlQuery= "SELECT * FROM sechdules WHERE schedule_id= ?";
        try(PreparedStatement statement= dbConnection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, scheduleId.getText());
            ResultSet resultSet= statement.executeQuery();
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "Invalid Schedule ID! Please Enter a valid ID");
                return;
            }

            teacherFoundNOt.setText("Schedule Found");
            teacherFoundNOt.setForeground(Color.green);
            teacher.setText("Teacher: "+resultSet.getString("teacherId"));
            room.setText("Room: "+resultSet.getString("roomId"));
            timeSlot.setText("Slot: "+resultSet.getTime("startTime")+"--"+resultSet.getTime("endTime"));
            warning.setText("Are you sure you want to delete Schedule? This Action can not be undone...");
            System.out.println("All done");
        }
        catch(SQLException e){
            teacherFoundNOt.setText("Schedule Not Found");
            teacherFoundNOt.setForeground(Color.red);
        }
    }

    public void deleteSchedule(JTextField SId){
        dbConnection= new DBConnection();
        int rowsAffected;
        String sqlQuery= "DELETE FROM sechdules WHERE schedule_id= ?";
        try(PreparedStatement statement= dbConnection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, SId.getText());
            rowsAffected = statement.executeUpdate();
            if(rowsAffected<1){
                JOptionPane.showMessageDialog(null, "Schedule Couldn't be Deleted! Please Try Again");
                return;
            }
            System.out.println("All Done || rows Affected: "+rowsAffected);
            JOptionPane.showMessageDialog(null, "Schedule Data Deleted");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public ArrayList<String> freeTeacherSlotList(JComboBox<String> day, JComboBox<String> resourceId, String resource) {

        ArrayList<String> list = new ArrayList<>();
        dbConnection = new DBConnection();

        // Query to fetch allocated classes sorted chronologically
        String sqlQuery = "SELECT startTime, endTime FROM sechdules WHERE classDay=? AND teacherId=? ORDER BY startTime";

        // Define the operational boundaries of your university day
        LocalTime currentPos = LocalTime.of(8, 45, 0);  // Timeline slider starts at 08:00 AM
        LocalTime dayEnd = LocalTime.of(16, 30, 0);     // Hard stop at 04:00 PM

        // Time formatter to keep your output clean (HH:mm)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try (PreparedStatement statement = dbConnection.getDatabaseConnection().prepareStatement(sqlQuery)) {
            statement.setString(1, day.getSelectedItem().toString());
            statement.setString(2, resourceId.getSelectedItem().toString());
            ResultSet resultSet = statement.executeQuery();

            // CASE 1: The Teacher has NO classes scheduled on this day (Completely Free)
            if (!resultSet.isBeforeFirst()) {
                list.add(currentPos.format(formatter) + " - " + dayEnd.format(formatter));
            } else {
                // CASE 2: The Teacher has scheduled classes. Let's calculate the gaps.
                while (resultSet.next()) {
                    // Parse database String values into calculable LocalTime objects
                    LocalTime classStart = LocalTime.parse(resultSet.getString("startTime"));
                    LocalTime classEnd = LocalTime.parse(resultSet.getString("endTime"));

                    // If our slider position is behind the next class start time, a gap exists!
                    if (currentPos.isBefore(classStart)) {
                        list.add(currentPos.format(formatter) + " - " + classStart.format(formatter));
                    }

                    // Move our timeline slider to the end of this busy block
                    currentPos = classEnd;
                }

                // AFTER THE LOOP: Check if there is remaining time left before 4:00 PM
                if (currentPos.isBefore(dayEnd)) {
                    list.add(currentPos.format(formatter) + " - " + dayEnd.format(formatter));
                }
            }

            // Debug or direct update: Verify your arraylist contents
            System.out.println("Free slots calculated: " + list);

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Time Parsing Error: Make sure your DB times are stored in HH:mm:ss format. " + e.getMessage());
        }
        return list;
    }

    public ArrayList<String> freeBatchSlotList(JComboBox<String> day, JComboBox<String> resourceId) {

        ArrayList<String> list = new ArrayList<>();
        dbConnection = new DBConnection();

        // Query to fetch allocated classes sorted chronologically
        String sqlQuery = "SELECT startTime, endTime FROM sechdules WHERE classDay=? AND batch_id=? ORDER BY startTime";

        // Define the operational boundaries of your university day
        LocalTime currentPos = LocalTime.of(8, 45, 0);  // Timeline slider starts at 08:00 AM
        LocalTime dayEnd = LocalTime.of(16, 30, 0);     // Hard stop at 04:00 PM

        // Time formatter to keep your output clean (HH:mm)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try (PreparedStatement statement = dbConnection.getDatabaseConnection().prepareStatement(sqlQuery)) {
            statement.setString(1, day.getSelectedItem().toString());
            statement.setString(2, resourceId.getSelectedItem().toString());
            ResultSet resultSet = statement.executeQuery();

            // CASE 1: The Teacher has NO classes scheduled on this day (Completely Free)
            if (!resultSet.isBeforeFirst()) {
                list.add(currentPos.format(formatter) + " - " + dayEnd.format(formatter));
            } else {
                // CASE 2: The Teacher has scheduled classes. Let's calculate the gaps.
                while (resultSet.next()) {
                    // Parse database String values into calculable LocalTime objects
                    LocalTime classStart = LocalTime.parse(resultSet.getString("startTime"));
                    LocalTime classEnd = LocalTime.parse(resultSet.getString("endTime"));

                    // If our slider position is behind the next class start time, a gap exists!
                    if (currentPos.isBefore(classStart)) {
                        list.add(currentPos.format(formatter) + " - " + classStart.format(formatter));
                    }

                    // Move our timeline slider to the end of this busy block
                    currentPos = classEnd;
                }

                // AFTER THE LOOP: Check if there is remaining time left before 4:00 PM
                if (currentPos.isBefore(dayEnd)) {
                    list.add(currentPos.format(formatter) + " - " + dayEnd.format(formatter));
                }
            }

            // Debug or direct update: Verify your arraylist contents
            System.out.println("Free slots calculated: " + list);

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Time Parsing Error: Make sure your DB times are stored in HH:mm:ss format. " + e.getMessage());
        }
        return list;
    }

    public ArrayList<String> freeRoomSlotList(JComboBox<String> day, JComboBox<String> resourceId, String resource) {

        ArrayList<String> list = new ArrayList<>();
        dbConnection = new DBConnection();

        // Query to fetch allocated classes sorted chronologically
        String sqlQuery = "SELECT startTime, endTime FROM sechdules WHERE classDay=? AND roomId=? ORDER BY startTime";

        // Define the operational boundaries of your university day
        LocalTime currentPos = LocalTime.of(8, 45, 0);  // Timeline slider starts at 08:00 AM
        LocalTime dayEnd = LocalTime.of(16, 30, 0);     // Hard stop at 04:00 PM

        // Time formatter to keep your output clean (HH:mm)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try (PreparedStatement statement = dbConnection.getDatabaseConnection().prepareStatement(sqlQuery)) {
            statement.setString(1, day.getSelectedItem().toString());
            statement.setString(2, resourceId.getSelectedItem().toString());
            ResultSet resultSet = statement.executeQuery();

            // CASE 1: The Teacher has NO classes scheduled on this day (Completely Free)
            if (!resultSet.isBeforeFirst()) {
                list.add(currentPos.format(formatter) + " - " + dayEnd.format(formatter));
            } else {
                // CASE 2: The Teacher has scheduled classes. Let's calculate the gaps.
                while (resultSet.next()) {
                    // Parse database String values into calculable LocalTime objects
                    LocalTime classStart = LocalTime.parse(resultSet.getString("startTime"));
                    LocalTime classEnd = LocalTime.parse(resultSet.getString("endTime"));

                    // If our slider position is behind the next class start time, a gap exists!
                    if (currentPos.isBefore(classStart)) {
                        list.add(currentPos.format(formatter) + " - " + classStart.format(formatter));
                    }

                    // Move our timeline slider to the end of this busy block
                    currentPos = classEnd;
                }

                // AFTER THE LOOP: Check if there is remaining time left before 4:00 PM
                if (currentPos.isBefore(dayEnd)) {
                    list.add(currentPos.format(formatter) + " - " + dayEnd.format(formatter));
                }
            }

            // Debug or direct update: Verify your arraylist contents
            System.out.println("Free slots calculated: " + list);

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Time Parsing Error: Make sure your DB times are stored in HH:mm:ss format. " + e.getMessage());
        }
        return list;
    }
}
