package gui;

import database.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudentForm extends JFrame implements ActionListener {

    JLabel headingLabel;

    JLabel nameLabel;
    JLabel rollLabel;
    JLabel deptLabel;
    JLabel attendanceLabel;
    JLabel marksLabel;

    JTextField nameField;
    JTextField rollField;
    JTextField deptField;
    JTextField attendanceField;
    JTextField marksField;

    JButton saveButton;

    public AddStudentForm() {

        setTitle("Add Student");

        setSize(500, 500);

        setLayout(null);

        headingLabel = new JLabel("Add Student");

        headingLabel.setBounds(180, 20, 200, 30);

        add(headingLabel);

        // Name

        nameLabel = new JLabel("Name");

        nameLabel.setBounds(50, 80, 100, 30);

        add(nameLabel);

        nameField = new JTextField();

        nameField.setBounds(180, 80, 200, 30);

        add(nameField);

        // Roll No

        rollLabel = new JLabel("Roll No");

        rollLabel.setBounds(50, 130, 100, 30);

        add(rollLabel);

        rollField = new JTextField();

        rollField.setBounds(180, 130, 200, 30);

        add(rollField);

        // Department

        deptLabel = new JLabel("Department");

        deptLabel.setBounds(50, 180, 100, 30);

        add(deptLabel);

        deptField = new JTextField();

        deptField.setBounds(180, 180, 200, 30);

        add(deptField);

        // Attendance

        attendanceLabel = new JLabel("Attendance");

        attendanceLabel.setBounds(50, 230, 100, 30);

        add(attendanceLabel);

        attendanceField = new JTextField();

        attendanceField.setBounds(180, 230, 200, 30);

        add(attendanceField);

        // Marks

        marksLabel = new JLabel("Marks");

        marksLabel.setBounds(50, 280, 100, 30);

        add(marksLabel);

        marksField = new JTextField();

        marksField.setBounds(180, 280, 200, 30);

        add(marksField);

        // Save Button

        saveButton = new JButton("Save Student");

        saveButton.setBounds(150, 360, 180, 40);

        saveButton.addActionListener(this);

        add(saveButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            String name = nameField.getText();

            String roll = rollField.getText();

            String dept = deptField.getText();

            int attendance = Integer.parseInt(attendanceField.getText());

            double marks = Double.parseDouble(marksField.getText());

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO students(name, roll_no, department, attendance, marks) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);

            ps.setString(2, roll);

            ps.setString(3, dept);

            ps.setInt(4, attendance);

            ps.setDouble(5, marks);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Student Added Successfully!");

            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}