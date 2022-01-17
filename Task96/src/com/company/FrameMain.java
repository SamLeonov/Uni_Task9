package com.company;

import com.company.util.ArrayUtils;
import com.company.util.JTableUtils;
import com.company.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class FrameMain extends JFrame {
    private JButton convert;
    private JPanel panelMain;
    private JTable tableInput;
    private JTable tableInput2;
    private JScrollPane JScrollPaneInput;
    private JScrollPane JScrollPaneInput2;
    private JTable tableOutput;
    private JScrollPane JScrollPaneOutput;
    private JButton saveToButtonIn;
    private JButton saveToButtonOut;
    private JButton openFileButton;
    private JButton openFileButton2;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;


    public FrameMain() {
        this.setTitle("FriendlyConverter v1.0");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 40, true, true, false, true);
        JTableUtils.initJTableForArray(tableOutput, 40, true, true, false, true);
        JTableUtils.initJTableForArray(tableInput2, 40, true, true, false, true);
        //tableOutput.setEnabled(false);
        tableInput.setRowHeight(25);
        tableInput2.setRowHeight(25);
        tableOutput.setRowHeight(25);
        JScrollPaneInput.setPreferredSize(new Dimension(-1, 90));
        JScrollPaneInput2.setPreferredSize(new Dimension(-1, 90));
        JScrollPaneOutput.setPreferredSize(new Dimension(-1, 90));

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");


        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    List<Integer> l1 = ArrayUtils.toIntList(JTableUtils.readIntArrayFromJTable(tableInput));
                    List<Integer> l2 = ArrayUtils.toIntList(JTableUtils.readIntArrayFromJTable(tableInput2));
                    int[] arr = ArrayUtils.toIntArray(Task9.createNewList(l1, l2));
                    JTableUtils.writeArrayToJTable(tableOutput, arr);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });


        saveToButtonIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = JTableUtils.readIntMatrixFromJTable(tableInput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        saveToButtonOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = JTableUtils.readIntMatrixFromJTable(tableOutput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        openFileButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr2 = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput2, arr2);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
