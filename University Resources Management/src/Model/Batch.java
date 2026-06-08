package Model;

import Database.DBConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Batch {
    private String batchID;
    private String batchName;
    private int strength;
    private int semester;
    public Batch(){

    }
    public Batch(String batchID, String batchName, int strength, int semester){
        this.batchID= batchID;
        this.batchName= batchName;
        this.strength= strength;
        this.semester= semester;
    }

    String getBatchID(){
        return this.batchID;
    }
    String getBatchName(){
        return this.batchName;
    }
    int getStrength(){
        return this.strength;
    }
    int getSemester(){
        return this.semester;
    }

    void setBatchID(String batchID){
        this.batchID= batchID;
    }
    void setBatchName(String batchName){
        this.batchName= batchName;
    }
    void setStrength(int strength){
        this.strength= strength;
    }
    void setSemester(int semester){
        this.semester= semester;
    }
    DBConnection connection;

    public void showBatchDataInGUITable(JTable table){
        connection= new DBConnection();
        String[] columnNames= {"Batch ID", "Name", "Strength", "Semester"};
        DefaultTableModel model= new DefaultTableModel(columnNames, 0);
        String sqlQuery= "SELECT * FROM batches";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            ResultSet resultSet= statement.executeQuery();
            while(resultSet.next()){
                this.batchID= resultSet.getString("bactch_id");
                this.batchName= resultSet.getString("batchName");
                this.strength= resultSet.getInt("strength");
                this.semester= resultSet.getInt("semester");

                Object[] row= {this.batchID, this.batchName, this.strength, this.semester};
                model.addRow(row);
            }
            table.setModel(model);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void totalBatch(JLabel label){
        connection= new DBConnection();
        String sqlQuery= "SELECT count(bactch_id) FROM batches;";
        int rowsAffected;
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            ResultSet rs= statement.executeQuery();
            rs.next();
            int count= rs.getInt(1);
            label.setText(String.valueOf(count));
            System.out.println("Success || Total Batches: "+ count);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void FetchBatchInfo(JTextField batchId, JTextField batchName, JSpinner batchStrength, JSpinner semester){
        this.batchID= batchId.getText();
        connection= new DBConnection();
        String sqlQuery= "Select * FROM batches WHERE bactch_id= ?";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, this.batchID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            batchName.setText(resultSet.getString("batchName"));
            batchStrength.setValue(resultSet.getInt("strength"));
            semester.setValue(resultSet.getInt("semester"));
            System.out.println("All value Fetched");

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    public void fetchDeleteBatchInfo(JTextField batchId, JLabel batchName, JLabel batchStrength, JLabel semester, JLabel status, JLabel warning){
        connection= new DBConnection();
        String sqlQuery= "SELECT * FROM batches WHERE bactch_id= ?";
        try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
            statement.setString(1, batchId.getText());
            ResultSet resultSet= statement.executeQuery();
            resultSet.next();

            status.setText("Batch Found");
            status.setForeground(Color.green);
            batchName.setText("Batch Name: "+resultSet.getString("batchName"));
            batchStrength.setText("Strength: "+resultSet.getString("strength"));
            semester.setText("Semester: "+resultSet.getString("semester"));
            warning.setText("Are you sure you want to delete Batch? This Action can not be undone...");
            System.out.println("All done");
        }
        catch(SQLException e){
            status.setText("Batch Not Found");
            status.setForeground(Color.red);
        }
    }

    public void batchWiseSchedule(String IDBatch, JTable batchWiseTimeTable, String choice){
        connection= new DBConnection();
        String[] columns = {"Schedule ID", "Teacher", "Room", "Batch", "Day", "Slot"};
        DefaultTableModel model= new DefaultTableModel(columns, 0);
        if(String.valueOf(choice).equalsIgnoreCase("Batch")){
            String sqlQuery= "SELECT * FROM sechdules WHERE batch_id=?";
            try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
                statement.setString(1, IDBatch);
                ResultSet resultSet= statement.executeQuery();
                if(!resultSet.next()){
                    JOptionPane.showMessageDialog(null, "Invalid Batch ID");
                    return;
                }
                do{
                    String scHId, tId, rId, cDay, bId, slot;
                    scHId= resultSet.getString("schedule_id");
                    tId= resultSet.getString("teacherId");
                    rId= resultSet.getString("roomId");
                    bId= resultSet.getString("batch_id");
                    cDay= resultSet.getString("classDay");
                    slot= resultSet.getTime("startTime")+"--"+resultSet.getTime("endTime");
                    Object[] row= {scHId, tId, rId, bId, cDay, slot};
                    model.addRow(row);
                }while(resultSet.next());
                batchWiseTimeTable.setModel(model);
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            String sqlQuery= "SELECT * FROM sechdules WHERE teacherId=?";
            try(PreparedStatement statement= connection.getDatabaseConnection().prepareStatement(sqlQuery)){
                statement.setString(1, IDBatch);
                ResultSet resultSet= statement.executeQuery();
                if(!resultSet.next()){
                    JOptionPane.showMessageDialog(null, "Invalid Teacher ID");
                    return;
                }
                do{
                    String scHId, tId, rId, cDay, bId, slot;
                    scHId= resultSet.getString("schedule_id");
                    tId= resultSet.getString("teacherId");
                    rId= resultSet.getString("roomId");
                    bId= resultSet.getString("batch_id");
                    cDay= resultSet.getString("classDay");
                    slot= resultSet.getTime("startTime")+"--"+resultSet.getTime("endTime");
                    Object[] row= {scHId, tId, rId, bId, cDay, slot};
                    model.addRow(row);
                }while(resultSet.next());
                batchWiseTimeTable.setModel(model);
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void printPdfTimeTable(JTable batchWiseTimeTable, String filePath){
        Document document = new Document();
        try {
            // 1. Initialize the PDF writer to save the file to the computer
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // 2. Create a PDF table with the same number of columns as your JTable
            PdfPTable pdfTable = new PdfPTable(batchWiseTimeTable.getColumnCount());

            // 3. Add Column Headers from the JTable
            for (int i = 0; i < batchWiseTimeTable.getColumnCount(); i++) {
                pdfTable.addCell(new Phrase(batchWiseTimeTable.getColumnName(i)));
            }

            // 4. Add Rows from the JTable Model
            for (int rows = 0; rows < batchWiseTimeTable.getRowCount(); rows++) {
                for (int cols = 0; cols < batchWiseTimeTable.getColumnCount(); cols++) {
                    Object value = batchWiseTimeTable.getModel().getValueAt(rows, cols);
                    pdfTable.addCell(new Phrase(value != null ? value.toString() : ""));
                }
            }

            // 5. Add the completed table to the document and close it
            document.add(pdfTable);
            document.close();
            JOptionPane.showMessageDialog(null, "PDF Saved");
            System.out.println("PDF saved successfully to: " + filePath);

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
