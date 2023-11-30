package First;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Planet extends JPanel implements ActionListener {
    private int planetRadius = 50;
    private int satelliteRadius = 10;
    private int planetCenterX = 200;
    private int planetCenterY = 200;
    private int semiMajorAxis = 100; // Semi-major axis of the ellipse
    private int semiMinorAxis = 30;  // Semi-minor axis of the ellipse
    private double angle = 0;
    private Timer timer;

    public Planet() {
        timer = new Timer(10, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int satelliteX = (int) (planetCenterX + semiMajorAxis * Math.cos(angle));
        int satelliteY = (int) (planetCenterY + semiMinorAxis * Math.sin(angle));

        g2d.setColor(Color.RED); // Satellite color
        g2d.fillOval(satelliteX - satelliteRadius, satelliteY - satelliteRadius, 2 * satelliteRadius, 2 * satelliteRadius);


        g2d.setColor(Color.BLUE); // Planet color
        g2d.fillOval(planetCenterX - planetRadius, planetCenterY - planetRadius, 2 * planetRadius, 2 * planetRadius);


        angle += 0.02; // Speed of satellite's orbit
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Satellite Orbit");
        Planet simulation = new Planet();
        frame.add(simulation);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
