package Second;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main implements Runnable{
    public static void main(String[] args) {
        SwingUtilities.invokeLater (new Second.Main());
    }

    @Override
    public void run() {
        JFrame mainFrame = new JFrame();
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setPreferredSize(new Dimension(800, 500));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        JRadioButton dd = new JRadioButton("IN: Double, RES: double");
        JRadioButton id = new JRadioButton("IN: Int, RES: Int");
        JRadioButton di = new JRadioButton("IN: Double, RES: int");
        dd.setSelected(true);
        ButtonGroup btnGrp = new ButtonGroup();
        btnGrp.add(dd);
        btnGrp.add(id);
        btnGrp.add(di);
        JPanel radioButtons = new JPanel();
        radioButtons.setLayout(new BoxLayout(radioButtons, BoxLayout.PAGE_AXIS));
        radioButtons.add(dd);
        radioButtons.add(id);
        radioButtons.add(di);
        GridBagConstraints forRButtons = new GridBagConstraints();
        //forRButtons.anchor = GridBagConstraints.FIRST_LINE_START;
        forRButtons.gridx = 0;
        forRButtons.gridy = 0;
//        forRButtons.weighty = 0.1;
//        forRButtons.weightx = 0.1;
        mainFrame.add(radioButtons, forRButtons);
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        JButton calc = new JButton("Calculate");
        JButton impFile = new JButton("Import");
        buttons.add(calc);
        buttons.add(impFile);
        GridBagConstraints forButtons = new GridBagConstraints();
        forButtons.gridx = 1;
        forButtons.gridy = 1;
//        forCalc.weighty = 0.1;
//        forCalc.weightx = 0.1;
        //forCalc.anchor = GridBagConstraints.LAST_LINE_START;
        mainFrame.add(buttons, forButtons);
        JPanel forInput = new JPanel(new GridBagLayout());
        JPanel forResult = new JPanel(new GridBagLayout());
        JPanel forFile = new JPanel(new GridBagLayout());
        JLabel n1 = new JLabel("Number 1: ");
        JLabel n2 = new JLabel("Number 2: ");
        JLabel res = new JLabel("Result: ");
        JLabel filename = new JLabel("Filename: ");
        JTextField num1 = new JTextField();
        JTextField num2 = new JTextField();
        JTextField resField = new JTextField();
        JTextField fileField = new JTextField("in.txt");
        resField.setEnabled(false);
        num1.setPreferredSize(new Dimension(100, 20));
        num2.setPreferredSize(new Dimension(100, 20));
        resField.setPreferredSize(new Dimension(200, 20));
        fileField.setPreferredSize(new Dimension(100, 20));
        GridBagConstraints temp = new GridBagConstraints();
        //temp.anchor = GridBagConstraints.FIRST_LINE_START;
        temp.gridx = 0;
        temp.gridy = 0;
        forInput.add(n1, temp);
        //temp.anchor = GridBagConstraints.PAGE_START;
        temp.gridx = 1;
        temp.gridy = 0;
        forInput.add(num1, temp);
        //temp.anchor = GridBagConstraints.LINE_START;
        temp.gridx = 0;
        temp.gridy = 1;
        forInput.add(n2, temp);
        //temp.anchor = GridBagConstraints.CENTER;
        temp.gridx = 1;
        temp.gridy = 1;
        forInput.add(num2, temp);
        GridBagConstraints forInputC = new GridBagConstraints();
        forInputC.gridx = 2;
        forInputC.gridy = 0;
        mainFrame.add(forInput, forInputC);
        temp.gridx = 0;
        temp.gridy = 0;
        forResult.add(res, temp);
        forFile.add(filename, temp);
        temp.gridx = 1;
        temp.gridy = 0;
        forResult.add(resField, temp);
        forFile.add(fileField, temp);
        temp.gridx = 2;
        temp.gridy = 2;
        mainFrame.add(forResult, temp);
        temp.gridx = 0;
        temp.gridy = 2;
        mainFrame.add(forFile, temp);

        calc.addActionListener(actionEvent -> {
            int choose = 0;
            if(dd.isSelected()) {
                choose = 1;
            } else if(id.isSelected()) {
                choose = 2;
            } else if(di.isSelected()) {
                choose = 3;
            }
            switch (choose) {
                case 1 -> {
                    double x, y, result;
                    try {
                        x = Double.parseDouble(num1.getText());
                        y = Double.parseDouble(num2.getText());
                        result = 1/(x*y)+1/(x*x+y*y)*(x-y);
                        resField.setText(Double.toString(result));
                    } catch (Exception e) {
                        resField.setText("Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        int x, y;
                        double result;
                        x = Integer.parseInt(num1.getText());
                        y = Integer.parseInt(num2.getText());
                        result = 1/(x*y)+1/(x*x+y*y)*(x-y);
                        resField.setText(Double.toString(result));
                    } catch (Exception e) {
                        resField.setText("Error: " + e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        double x, y;
                        int result;
                        x = Double.parseDouble(num1.getText());
                        y = Double.parseDouble(num2.getText());
                        result = (int)  (1/(x*y)+1/(x*x+y*y)*(x-y));
                        resField.setText(Integer.toString(result));
                    } catch (Exception e) {
                        resField.setText("Error: " + e.getMessage());
                    }
                }
                default -> resField.setText("Unexcepted error.");
            }
        });

        impFile.addActionListener(actionEvent -> {
            String fileName = fileField.getText();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                num1.setText((reader.readLine()));
                num2.setText((reader.readLine()));
                reader.close();
            } catch (FileNotFoundException e) {
                resField.setText("Error. Can't open file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
