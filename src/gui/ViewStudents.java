package gui;

import database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewStudents extends JFrame implements ActionListener {

    JTable table;

    JScrollPane scrollPane;

    JButton deleteButton;

    public ViewStudents() {

        deleteButton = new JButton("Delete Student");deleteButton = new JButton("Delete Student");

        deleteButton.setBounds(250, 330, 180, 40);

        add(deleteButton);

        deleteButton.addActionListener(this);

        setTitle("All Students");

        setSize(700, 400);

        setLayout(null);

        String[] columns = {
                "ID",
                "Name",
                "Roll No",
                "Department",
                "Attendance",
                "Marks"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        scrollPane = new JScrollPane(table);

        scrollPane.setBounds(20, 20, 640, 300);

        add(scrollPane);

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while(rs.next()) {

                int id = rs.getInt("id");

                String name = rs.getString("name");

                String roll = rs.getString("roll_no");

                String dept = rs.getString("department");

                int attendance = rs.getInt("attendance");

                double marks = rs.getDouble("marks");

                Object[] row = {
                        id,
                        name,
                        roll,
                        dept,
                        attendance,
                        marks
                };

                model.addRow(row);
            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == deleteButton) {

            int selectedRow = table.getSelectedRow();

            if(selectedRow == -1) {

                JOptionPane.showMessageDialog(this,
                        "Please select a student");

                return;
            }

            int id = (int) table.getValueAt(selectedRow, 0);

            try {

                Connection con = DBConnection.getConnection();

                String query =
                        "DELETE FROM students WHERE id=?";

                PreparedStatement ps =
                        con.prepareStatement(query);

                ps.setInt(1, id);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Student Deleted!");

                con.close();

                dispose();

                new ViewStudents();

            } catch(Exception ex) {

                ex.printStackTrace();
            }
        }
    }
}