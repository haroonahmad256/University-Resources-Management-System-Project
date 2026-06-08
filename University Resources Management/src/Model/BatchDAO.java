package Model;

import Database.DBConnection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BatchDAO extends CRUDOperations<Batch> {
    DBConnection connection;

    @Override
    public void create(Batch batch) {
        connection= new DBConnection();
        int rowsAffected;
        connection= new DBConnection();
        String sqlQuery="INSERT INTO batches (bactch_id, batchName, strength, semester) VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, batch.getBatchID());
            statement.setString(2, batch.getBatchName());
            statement.setInt(3, batch.getStrength());
            statement.setInt(4, batch.getSemester());
            rowsAffected= statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Batch Added Successfully");
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
        String sqlQuery= "DELETE FROM batches WHERE bactch_id= ?";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, id);
            rowsAffected = statement.executeUpdate();
            if(rowsAffected<1){
                JOptionPane.showMessageDialog(null, "Teacher couldn't be deleted");
            }
            else{
                JOptionPane.showMessageDialog(null, "Batch Data Deleted");
                System.out.println("Success || rows Affected: "+rowsAffected);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void update(Batch batch) {
        connection= new DBConnection();
        int rowsAffected;
        String updateNameSqlQuery = "UPDATE batches SET batchName=?, strength=?, semester=? WHERE bactch_id=?";
        try(PreparedStatement statementUpdate = connection.getDatabaseConnection().prepareStatement(updateNameSqlQuery))
        {
            statementUpdate.setString(1, batch.getBatchName());
            statementUpdate.setInt(2, batch.getStrength());
            statementUpdate.setInt(3, batch.getSemester());
            statementUpdate.setString(4, batch.getBatchID());
            rowsAffected= statementUpdate.executeUpdate();
            System.out.println("All Done || Rows Affected: "+rowsAffected);
            JOptionPane.showMessageDialog(null, "Batch Updated Successfully");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public boolean searchBatch(JTextField idField, JLabel degree, JLabel strength, JLabel semester, JLabel id){
        connection= new DBConnection();
        String sqlQuery= "SELECT * FROM batches WHERE bactch_id=? OR batchName= ?";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, idField.getText());
            statement.setString(2, idField.getText());
            ResultSet resultSet= statement.executeQuery();
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "Batch doesn't exist. Please enter a valid id");
                return false;
            }
            degree.setText(resultSet.getString("batchName"));
            strength.setText(String.valueOf(resultSet.getInt("strength")));
            semester.setText(String.valueOf(resultSet.getInt("semester")));
            id.setText(resultSet.getString("bactch_id"));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return true;
    }
}
