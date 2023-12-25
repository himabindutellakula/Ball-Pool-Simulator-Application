package sim;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

/**
 * A graphical viewer for simulating and visualizing the movement of balls in a pool.
 */
public class BallView extends JFrame {

  private List<Integer> ballX;
  private List<Integer> ballY;
  private List<Integer> ballRadius;
  private int tableWidth;
  private int tableHeight;
  private int step;

  /**
   * Constructs a BallView instance.
   *
   * @param simulator The PoolSimulator object containing the simulation data.
   */
  public BallView(PoolSimulator simulator) {
    super("Ball Viewer");
    extractBallPositions(simulator);
    step = 1;

    // Set the preferred size of the panel based on the table dimensions.
    this.add(new JPanel() {
      @Override
      public Dimension getPreferredSize() {
        return new Dimension((int) (1.5 * tableWidth), (int) (1.5 * tableHeight));
      }

      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int offset = 20;
        Graphics2D g2d = (Graphics2D) g;
        int height = this.getPreferredSize().height;
        g2d.setColor(Color.BLACK);
        g2d.drawRect(offset, offset, tableWidth, tableHeight);

        // Draw the different ball positions.
        for (int i = step - 1; i <= step; i += 1) {
          g2d.drawOval(offset + (int) ballX.get(i).doubleValue()
                  - (int) ballRadius.get(i).doubleValue(),
              offset + tableHeight - 1 - ((int) ballY.get(i).doubleValue()
                  + (int) ballRadius.get(i).doubleValue()),
              2 * (int) ballRadius.get(i).doubleValue(),
              2 * (int) ballRadius.get(i).doubleValue());
        }

        // Draw lines between the ball positions.
        for (int i = step - 1; i < step; i += 1) {
          g2d.drawLine(offset + (int) ballX.get(i).doubleValue(), offset
                  + tableHeight - 1 - (int) ballY.get(i).doubleValue(),
              offset + (int) ballX.get(i + 1).doubleValue(), offset
                  + tableHeight - 1 - (int) ballY.get(i + 1).doubleValue());
        }
      }
    });

    this.setFocusable(true);
    this.requestFocus();
    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          step -= 1;
          if (step < 1) {
            step = 1;
          }
          repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          step += 1;
          if (step >= ballX.size()) {
            step = ballX.size() - 1;
          }
          repaint();
        }
      }
    });

    pack();
    setVisible(true);
  }

  /**
   * The entry point for the BallView application.
   *
   * @param args The command-line arguments.
   */
  public static void main(String[] args) {
    PoolSimulator simulator = new SimplePoolSimulator(400, 400, "friction");
    simulator.start(100, 100, 20, 60, 1.1, -2);
    JFrame view = new BallView(simulator);
  }

  /**
   * Extracts ball positions during the simulation and stores them in lists.
   *
   * @param simulator The PoolSimulator object containing the simulation data.
   */
  private void extractBallPositions(PoolSimulator simulator) {
    ballX = new ArrayList<Integer>();
    ballY = new ArrayList<Integer>();
    ballRadius = new ArrayList<Integer>();

    tableWidth = simulator.getTableWidth();
    tableHeight = simulator.getTableHeight();

    while (!simulator.getStatus().equals("Status: Ball is stationary")) {
      ballX.add((int) simulator.getBallPositionX());
      ballY.add((int) simulator.getBallPositionY());
      ballRadius.add((int) simulator.getBallRadius());
      System.out.println(simulator.getStatus());
      simulator.advance();
    }

    // Store the final resting place of the ball.
    ballX.add((int) simulator.getBallPositionX());
    ballY.add((int) simulator.getBallPositionY());
    ballRadius.add((int) simulator.getBallRadius());
  }
}
