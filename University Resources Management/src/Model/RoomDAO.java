package Model;

import Database.DBConnection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAO extends CRUDOperations<Room>{
    DBConnection connection;
    @Override
    public void create(Room room) {
        connection= new DBConnection();
        int rowsAffected;
        connection= new DBConnection();
        String sqlQuery="INSERT INTO rooms (roomId, roomNumber, capacity, is_Lab) VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, room.getID());
            statement.setString(2, room.getRoomNumber());
            statement.setInt(3, room.getCapacity());
            statement.setBoolean(4,room.getIsLab());
            rowsAffected= statement.executeUpdate();
            if(rowsAffected<1){
                JOptionPane.showMessageDialog(null, "Couldn't Add Room! Please Try Again");
                return;
            }
            JOptionPane.showMessageDialog(null, "Room Added Successfully");
            System.out.println("Successfull || rows Affected: "+rowsAffected);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        connection= new DBConnection();
        int rowsAffected;
        String sqlQuery= "DELETE FROM rooms WHERE roomId= ?";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, id);
            rowsAffected = statement.executeUpdate();
            if(rowsAffected<1){
                JOptionPane.showMessageDialog(null, "Room couldn't be deleted! Please Try Again");
            }
            else{
                JOptionPane.showMessageDialog(null, "Room Data Deleted");
                System.out.println("Success || rows Affected: "+rowsAffected);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void update(Room room) {
        connection= new DBConnection();
        int rowsAffected;
        String updateNameSqlQuery = "UPDATE rooms SET roomNumber=?, capacity=?, is_Lab=? WHERE roomId=?";
        try(PreparedStatement statementUpdate = connection.getDatabaseConnection().prepareStatement(updateNameSqlQuery))
        {
            statementUpdate.setString(1, room.getRoomNumber());
            statementUpdate.setInt(2, room.getCapacity());
            statementUpdate.setBoolean(3,room.getIsLab());
            statementUpdate.setString(4, room.getID());
            rowsAffected= statementUpdate.executeUpdate();
            if(rowsAffected<1){
                JOptionPane.showMessageDialog(null, "Couldn't Update Information! Try Again");
                System.out.println("Rows Affected: "+rowsAffected);
            }
            else{
                System.out.println("All Done || Rows Affected: "+rowsAffected);
                JOptionPane.showMessageDialog(null, "Room Updated Successfully");
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public boolean searchRoom(JTextField search, JLabel roomID, JLabel roomNumber, JLabel capacity, JLabel isLab){
        connection = new DBConnection();
        String sqlQuery= "SELECT * FROM rooms WHERE roomId= ? OR roomNumber=?";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, search.getText());
            statement.setString(2, search.getText());
            ResultSet resultSet= statement.executeQuery();
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "Room doesn't exist. Please enter a valid id");
                return false;
            }
            else{
                roomID.setText(resultSet.getString("roomId"));
                roomNumber.setText(resultSet.getString("roomNumber"));
                capacity.setText(String.valueOf(resultSet.getInt("capacity")));
                isLab.setText(String.valueOf(resultSet.getBoolean("is_Lab")));
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return true;
    }
}
