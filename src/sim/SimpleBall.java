package sim;

/**
 * SimpleBall represents the ball which simulates on the concepts of simple physics.
 */
public class SimpleBall extends Ball {

  private final double simpleSpeedDecrement = 5;
  private static final double EPSILON = 1e-10;

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
  public SimpleBall(double x, double y, double radius, double speed, double dx, double dy) {
    super(x, y, radius);
    this.ballSpeed = speed;
    double magnitude = Math.sqrt(dx * dx + dy * dy);
    dx = dx / magnitude;
    dy = dy / magnitude;
    this.dx = dx;
    this.dy = dy;
    this.ballVelocityX = ballSpeed * dx;
    this.ballVelocityY = ballSpeed * dy;
    this.status = "Simulation started";
  }

  @Override
  protected void advance(double tableWidth, double tableHeight) {
    if (status.equals("Ball is stationary") || status.equals("Ball not set up")) {
      return;
    }
    String nextStatus = "Ball is stationary";
    double minTime = Double.MAX_VALUE;

    if (ballVelocityX != 0) {
      minTime = (ballVelocityX > 0) ?
          (tableWidth - ballPositionX - ballRadius) / ballVelocityX :
          (ballRadius - ballPositionX) / ballVelocityX;
      nextStatus = (ballVelocityX > 0) ? "Ball hit right edge" : "Ball hit left edge";
    }

    if (ballVelocityY != 0) {
      double time = (ballVelocityY > 0) ?
          (tableHeight - ballPositionY - ballRadius) / ballVelocityY :
          (ballRadius - ballPositionY) / ballVelocityY;
      if (time < minTime) {
        minTime = time;
        nextStatus = (ballVelocityY > 0) ? "Ball hit top edge" : "Ball hit bottom edge";
      }
    }

    ballPositionX += ballVelocityX * minTime;
    ballPositionY += ballVelocityY * minTime;

    if (nextStatus.contains("right") || nextStatus.contains("left")) {
      dx *= -1;
    } else if (nextStatus.contains("top") || nextStatus.contains("bottom")) {
      dy *= -1;
    }

    ballSpeed = ballSpeed - simpleSpeedDecrement;
    if (ballSpeed <= EPSILON) {
      ballVelocityX = 0;
      ballVelocityY = 0;
      status = "Ball is stationary";
    } else {
      ballVelocityX = ballSpeed * dx;
      ballVelocityY = ballSpeed * dy;
      status = nextStatus;
    }
  }
}
