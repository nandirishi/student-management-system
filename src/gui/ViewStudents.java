package gui;

import database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewStudents extends JFrame {

    JTable table;

    JScrollPane scrollPane;

    public ViewStudents() {

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
}