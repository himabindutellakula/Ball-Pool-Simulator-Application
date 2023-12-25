package sim;

/**
 * A JUnit test class for the SimpleBall class.
 */
public class SimpleBallTest extends BallTest {

  /**
   * Constructs a SimpleBall in terms of its position x & y, radius, speed and directions.
   *
   * @param x      the x co-ordinate of the position where ball is present
   * @param y      the y co-ordinate of the position where ball is present
   * @param radius the radius of the ball
   * @param speed  the speed of the ball on the table
   * @param dx     the horizontal direction of the speed of the ball
   * @param dy     the vertical direction of the speed of the ball
   */
  public Ball createBall(double x, double y, double radius, double speed, double dx, double dy) {
    return new SimpleBall(x, y, radius, speed, dx, dy);
  }

}