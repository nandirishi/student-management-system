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
    JButton updateButton;

    public ViewStudents() {

        deleteButton = new JButton("Delete Student");
        updateButton = new JButton("Update Student");

        deleteButton.setBounds(250, 330, 180, 40);
        updateButton.setBounds(450,330,180,40);

        add(deleteButton);
        add(updateButton);

        deleteButton.addActionListener(this);
        updateButton.addActionListener(this);

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

            if (e.getSource()==updateButton) {

            }
        }

        if (e.getSource()==updateButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow==-1) {
                JOptionPane.showMessageDialog(this,
                        "Please select a student");
                return;
            }

            int id = (int) table.getValueAt(selectedRow,
                    0);
            String name =
                    table.getValueAt(selectedRow, 1).toString();

            String roll =
                    table.getValueAt(selectedRow, 2).toString();

            String dept =
                    table.getValueAt(selectedRow, 3).toString();

            int attendance =
                    Integer.parseInt(
                            table.getValueAt(selectedRow, 4)
                                    .toString()
                    );

            double marks =
                    Double.parseDouble(
                            table.getValueAt(selectedRow, 5)
                                    .toString()
                    );

            new UpdateStudentForm(
                    id,
                    name,
                    roll,
                    dept,
                    attendance,
                    marks
            );
        }
    }
}