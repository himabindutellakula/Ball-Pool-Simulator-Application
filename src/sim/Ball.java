package sim;

/**
 * Abstract base class for implementing a Ball in a pool simulation.
 * This class contains method definitions and attributes common to all Ball implementations.
 */

abstract public class Ball {

  protected double ballPositionX;
  protected double ballPositionY;
  protected double ballSpeed;
  protected double ballVelocityX;
  protected double ballVelocityY;
  protected final double ballRadius;
  protected String status;
  protected double dx;
  protected double dy;

  /**
   * Constructs a Ball in terms of its position x & y and radius.
   *
   * @param x      the x co-ordinate of the position where ball is present
   * @param y      the y co-ordinate of the position where ball is present
   * @param radius the radius of the ball
   */
  protected Ball(double x, double y, double radius) {
    this.ballPositionX = x;
    this.ballPositionY = y;
    this.ballRadius = radius;
    this.ballSpeed = 0;
    this.ballVelocityX = 0;
    this.ballVelocityY = 0;
    this.status = "Ball not set up";
    this.dx = 0;
    this.dy = 0;
  }

  /**
   * gets the X-coordinate of the ball's position.
   *
   * @return the X-coordinate of the ball's position
   */
  protected double getBallPositionX() {
    return this.ballPositionX;
  }

  /**
   * gets the Y-coordinate of the ball's position.
   *
   * @return the Y-coordinate of the ball's position
   */
  protected double getBallPositionY() {
    return this.ballPositionY;
  }

  /**
   * gets the radius of the ball.
   *
   * @return the radius of the ball
   */
  protected double getBallRadius() {
    return this.ballRadius;
  }

  /**
   * gets the X-component of the ball's velocity.
   *
   * @return the X-component of the ball's velocity
   */
  protected double getBallVelocityX() {
    return this.ballVelocityX;
  }

  /**
   * gets the Y-component of the ball's velocity.
   *
   * @return the Y-component of the ball's velocity
   */
  protected double getBallVelocityY() {
    return this.ballVelocityY;
  }


  /**
   * gets the status message for the ball.
   *
   * @return the status message for the ball
   */
  protected String getStatus() {
    return "Status: " + this.status;
  }

  /**
   * To advance the ball position by one single step based on velocity and the physics model.
   *
   * @param tableWidth represents the width of the used for pool table
   * @param tableHeight represents the height of the table used for pool table
   */
  protected abstract void advance(double tableWidth, double tableHeight);

}
