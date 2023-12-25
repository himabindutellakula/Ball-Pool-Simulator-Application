package sim;

/**
 * An interface representing a pool simulation.
 * This interface defines methods for starting and advancing the simulation,
 * retrieving information about the table and ball, and getting the simulation status.
 */
public interface PoolSimulator {

  /**
   * To start the simulation with a ball at the given position, radius, and velocity.
   *
   * @param x      the x-coordinate of the initial ball position
   * @param y      the y-coordinate of the initial ball position
   * @param radius the radius of the ball
   * @param speed  the initial speed of the ball
   * @param dx     the initial horizontal direction of the ball's speed
   * @param dy     the initial vertical direction of the ball's speed
   * @throws IllegalArgumentException if the simulation of ball did not start within the table
   */
  void start(int x, int y, int radius, int speed, double dx, double dy)
      throws IllegalArgumentException;

  /**
   * Advances the simulation of the ball by one single step, involves a bouncing or stopping.
   */
  void advance();

  /**
   * gets the width of the table for this simulation.
   *
   * @return the width of the table
   */
  int getTableWidth();

  /**
   * gets the height of the table for this simulation.
   *
   * @return the height of the table
   */
  int getTableHeight();

  /**
   * gets the x-coordinate of the current position of the ball.
   *
   * @return the x-coordinate of the ball's position
   */
  double getBallPositionX();

  /**
   * gets the y-coordinate of the current position of the ball.
   *
   * @return the y-coordinate of the ball's position
   */
  double getBallPositionY();

  /**
   * gets the radius of the ball.
   *
   * @return the radius of the ball
   */
  double getBallRadius();

  /**
   * gets the x-component of the current velocity of the ball.
   *
   * @return the x-component of the ball's velocity
   */
  double getBallVelocityX();

  /**
   * gets the y-component of the current velocity of the ball.
   *
   * @return the y-component of the ball's velocity
   */
  double getBallVelocityY();

  /**
   * gets the status of the simulation at the current step.
   *
   * @return the status of the simulation
   */
  String getStatus();

}
