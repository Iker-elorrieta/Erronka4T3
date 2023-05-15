package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EditRowFrame extends JFrame implements ActionListener {
    
    private JLabel[] columnLabels;
    private JTextField[] columnFields;
    private JButton saveButton, cancelButton;
    private int numColumns;
    private String tableName;
    private int id;
    private Connection conn;
    
    public EditRowFrame(String tableName, int id, Connection conn) {
        this.tableName = tableName;
        this.id = id;
        this.conn = conn;

        try {
            // Prepare the SQL statement with a parameter
            String query = "SELECT * FROM " + tableName + " WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);

            // Execute the query and get the result set
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            this.numColumns = metaData.getColumnCount() - 1; // Exclude id column
            this.columnLabels = new JLabel[numColumns];
            this.columnFields = new JTextField[numColumns];

            // Set frame properties
            this.setTitle("Edit Row");
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setSize(400, 200);
            this.setLayout(new GridLayout(numColumns + 1, 2));

            // Create column labels and fields
            if (rs.next()) {
                for (int i = 1; i <= numColumns; i++) { // Start at 1 to exclude id column
                    columnLabels[i - 1] = new JLabel(metaData.getColumnName(i) + ":");
                    this.add(columnLabels[i - 1]);
                    columnFields[i - 1] = new JTextField(rs.getString(i));
                    this.add(columnFields[i - 1]);
                }
            }

            // Create buttons
            saveButton = new JButton("Save");
            saveButton.addActionListener(this);
            this.add(saveButton);
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);
            this.add(cancelButton);

            // Close the result set and statement
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Make the frame visible
        this.setVisible(true);
    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            try {
                // Build SQL statement to update the row
                StringBuilder sb = new StringBuilder();
                sb.append("UPDATE ").append(tableName).append(" SET ");
                for (int i = 0; i < numColumns; i++) {
                    sb.append(columnLabels[i].getText().replace(":", "")).append("='").append(columnFields[i].getText()).append("'");
                    if (i < numColumns - 1) {
                        sb.append(", ");
                    }
                }
                sb.append(" WHERE id=").append(id);
                String sql = sb.toString();
                
                // Execute the update statement
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
                
                // Close the frame
                this.dispose();
            } catch (SQLException ex) {
                System.out.println("Error updating row: " + ex.getMessage());
            }
        } else if (e.getSource() == cancelButton) {
            // Close the frame
            this.dispose();
        }
    }
}
