package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {

    JLabel titleLabel;

    JButton addButton;
    JButton viewButton;

    public Dashboard() {

        setTitle("Student Management System");

        setSize(700, 500);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Student Management System");

        titleLabel.setBounds(190, 30, 500, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        add(titleLabel);

        addButton = new JButton("Add Student");

        addButton.setBounds(250, 120, 180, 40);

        add(addButton);

        addButton.addActionListener(this);

        viewButton = new JButton("View Students");

        viewButton.setBounds(250, 200, 180, 40);

        add(viewButton);
        viewButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton) {
            new AddStudentForm();
        }
        if (e.getSource()==viewButton) {
            new ViewStudents();
        }
    }
}