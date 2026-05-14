package gui;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateStudentForm extends JFrame
        implements ActionListener {

    int studentId;

    JTextField nameField;
    JTextField rollField;
    JTextField deptField;
    JTextField attendanceField;
    JTextField marksField;

    JButton updateButton;

    public UpdateStudentForm(
            int id,
            String name,
            String roll,
            String dept,
            int attendance,
            double marks
    ) {

        studentId = id;

        setTitle("Update Student");

        setSize(500, 500);

        setLayout(null);

        getContentPane().setBackground(
                new Color(245, 247, 250)
        );

        JLabel heading =
                new JLabel("Update Student");

        heading.setFont(
                new Font("SansSerif", Font.BOLD, 24)
        );

        heading.setBounds(140, 20, 250, 30);

        add(heading);

        JLabel nameLabel = new JLabel("Name");

        nameLabel.setBounds(50, 80, 100, 30);

        add(nameLabel);

        nameField = new JTextField(name);

        nameField.setBounds(180, 80, 220, 35);

        add(nameField);

        JLabel rollLabel = new JLabel("Roll No");

        rollLabel.setBounds(50, 130, 100, 30);

        add(rollLabel);

        rollField = new JTextField(roll);

        rollField.setBounds(180, 130, 220, 35);

        add(rollField);

        JLabel deptLabel =
                new JLabel("Department");

        deptLabel.setBounds(50, 180, 100, 30);

        add(deptLabel);

        deptField = new JTextField(dept);

        deptField.setBounds(180, 180, 220, 35);

        add(deptField);

        JLabel attendanceLabel =
                new JLabel("Attendance");

        attendanceLabel.setBounds(50, 230, 100, 30);

        add(attendanceLabel);

        attendanceField =
                new JTextField(
                        String.valueOf(attendance)
                );

        attendanceField.setBounds(
                180,
                230,
                220,
                35
        );

        add(attendanceField);

        JLabel marksLabel =
                new JLabel("Marks");

        marksLabel.setBounds(50, 280, 100, 30);

        add(marksLabel);

        marksField =
                new JTextField(
                        String.valueOf(marks)
                );

        marksField.setBounds(
                180,
                280,
                220,
                35
        );

        add(marksField);

        updateButton =
                new JButton("Update");

        updateButton.setBounds(
                160,
                360,
                160,
                40
        );

        updateButton.addActionListener(this);

        add(updateButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            String name =
                    nameField.getText();

            String roll =
                    rollField.getText();

            String dept =
                    deptField.getText();

            int attendance =
                    Integer.parseInt(
                            attendanceField.getText()
                    );

            double marks =
                    Double.parseDouble(
                            marksField.getText()
                    );

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "UPDATE students " +
                            "SET name=?, roll_no=?, " +
                            "department=?, attendance=?, marks=? " +
                            "WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, name);

            ps.setString(2, roll);

            ps.setString(3, dept);

            ps.setInt(4, attendance);

            ps.setDouble(5, marks);

            ps.setInt(6, studentId);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Student Updated Successfully!"
            );

            con.close();

            dispose();

        } catch(Exception ex) {

            ex.printStackTrace();
        }
    }
}