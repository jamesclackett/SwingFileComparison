package com.jimboidin;

// A Swing-based file comparison utility

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class FileCompare implements ActionListener {
    JTextField textField1, textField2; // contains the names of files to be compared
    JButton compareButton;
    JLabel label1, label2;
    JLabel labelResult; //displays the result of comparison

    FileCompare(){
        // Setup the JFrame container
        JFrame jFrame = new JFrame("Compare Files");
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(1000,100);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup the text fields
        textField1 = new JTextField(14);
        textField2 = new JTextField(14);

        textField1.setActionCommand("fileA");
        textField2.setActionCommand("fileB");

        // Setup the compare button
        compareButton = new JButton("Compare");
        compareButton.addActionListener(this);

        // Create labels
        label1 = new JLabel("First file: ");
        label2 = new JLabel("Second file");
        labelResult = new JLabel("");

        // Add components to the content pane
        jFrame.add(label1);
        jFrame.add(textField1);
        jFrame.add(label2);
        jFrame.add(textField2);
        jFrame.add(compareButton);
        jFrame.add(labelResult);

        jFrame.setVisible(true);
    }

    // When compare button is pressed, compare the files
    @Override
    public void actionPerformed(ActionEvent e) {
        int i = 0, j = 0;

        if (textField1.getText().equals("") || textField2.getText().equals("")){
            labelResult.setText("File name(s) missing");
            return;
        }

        // Compare the files
        try (FileInputStream f1 = new FileInputStream(textField1.getText());
            FileInputStream f2 = new FileInputStream(textField2.getText())){

            do {
                i = f1.read();
                j = f2.read();
                if (i != j) break;
            } while (i != -1 && j != -1);

            if (i != j)
                labelResult.setText("Files are not the same");
            else
                labelResult.setText("Files are equal");
        } catch (IOException exc){
            labelResult.setText("File Error");
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(FileCompare::new);
    }
}
