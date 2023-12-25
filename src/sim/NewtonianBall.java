package sim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * NewtonianBall represents the ball which simulates on the concepts of newtonian physics.
 */
public class NewtonianBall extends Ball {

  private final double newtonianFrictionCoefficient = 0.1;
  private final double g = 9.81;
  private static final double EPSILON = 1e-10;

  /**
   * Constructs a NewtonianBall in terms of its position x & y, radius, speed and directions.
   *
   * @param x      the x co-ordinate of the position where ball is present
   * @param y      the y co-ordinate of the position where ball is present
   * @param radius the radius of the ball
   * @param speed  the speed of the ball on the table
   * @param dx     the horizontal direction of the speed of the ball
   * @param dy     the vertical direction of the speed of the ball
   */
  public NewtonianBall(double x, double y, double radius, double speed, double dx, double dy) {
    super(x, y, radius);
    double magnitude = Math.sqrt(dx * dx + dy * dy);
    dx = dx / magnitude;
    dy = dy / magnitude;
    this.dx = dx;
    this.dy = dy;
    this.ballSpeed = speed;
    this.ballVelocityX = ballSpeed * dx;
    this.ballVelocityY = ballSpeed * dy;
    this.status = "Simulation started";
  }

  @Override
  protected void advance(double tableWidth, double tableHeight) {
    if (status.equals("Ball is stationary") || status.equals("Ball not set up")) {
      return;
    }
    double minTime = calculateNextHitTime(tableWidth, tableHeight);

    double xDisplacement =
        ballVelocityX * minTime - ((g * newtonianFrictionCoefficient * dx) / 2) * minTime * minTime;
    double yDisplacement =
        ballVelocityY * minTime - ((g * newtonianFrictionCoefficient * dy) / 2) * minTime * minTime;

    ballPositionX += xDisplacement;
    ballPositionY += yDisplacement;

    ballSpeed = ballSpeed - (g * newtonianFrictionCoefficient * minTime);

    if (status.contains("right") || status.contains("left")) {
      dx *= -1;
    } else if (status.contains("top") || status.contains("bottom")) {
      dy *= -1;
    }

    if (ballSpeed <= EPSILON) {
      ballVelocityX = 0;
      ballVelocityY = 0;
      status = "Ball is stationary";
    } else {
      ballVelocityX = ballSpeed * dx;
      ballVelocityY = ballSpeed * dy;
    }
  }

  /**
   * To calculate the times to hit the four edges of table and to stop & choose the minimum time.
   *
   * @param tableWidth  represents the width of the table in which ball simulates
   * @param tableHeight represents the height of the table in which ball simulates
   * @return the minimum time taken for the next collision or to stop
   */
  private double calculateNextHitTime(double tableWidth, double tableHeight) {
    double timeToHitRightEdge = Double.MAX_VALUE;
    double timeToHitLeftEdge = Double.MAX_VALUE;
    double timeToHitBottomEdge = Double.MAX_VALUE;
    double timeToHitTopEdge = Double.MAX_VALUE;

    if (ballVelocityX != 0) {
      if (ballVelocityX > 0) {
        timeToHitRightEdge = timeToHitEdgeWithFriction(
            tableWidth - ballPositionX - ballRadius, dx, ballVelocityX);
      } else if (ballVelocityX < 0) {
        timeToHitLeftEdge = timeToHitEdgeWithFriction(
            ballRadius - ballPositionX, dx, ballVelocityX);
      }
    }

    if (ballVelocityY != 0) {
      if (ballVelocityY < 0) {
        timeToHitBottomEdge = timeToHitEdgeWithFriction(
            ballRadius - ballPositionY, dy, ballVelocityY);
      } else if (ballVelocityY > 0) {
        timeToHitTopEdge = timeToHitEdgeWithFriction(
            tableHeight - ballPositionY - ballRadius, dy, ballVelocityY);
      }
    }

    double timeToStop = ballSpeed / (g * newtonianFrictionCoefficient);

    List<Double> timesList = new ArrayList<>();
    timesList.add(timeToHitRightEdge);
    timesList.add(timeToHitLeftEdge);
    timesList.add(timeToHitBottomEdge);
    timesList.add(timeToHitTopEdge);
    timesList.add(timeToStop);

    return getMinValueAndSetStatus(timesList);
  }

  /**
   * To get the roots of a quadratic equation m = ut + 1/2 at*t, which is the time.
   *
   * @param displacementM displacement travelled by ball in different directions
   * @param d             the direction of the speed of the ball
   * @param velocity      the velocity of the ball
   * @return the least positive time obtained as a root of quadratic equation
   */
  private double timeToHitEdgeWithFriction(double displacementM, double d, double velocity) {
    double a = (g / 2) * newtonianFrictionCoefficient * d;
    double b = -1 * velocity;
    double c = displacementM;
    double discriminant = b * b - 4 * a * c;

    if (discriminant < 0) {
      return Double.POSITIVE_INFINITY;
    } else {
      double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
      double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);
      if (t1 > 0 && t2 > 0) {
        return Math.min(t1, t2);
      } else if (t1 > 0) {
        return t1;
      } else if (t2 > 0) {
        return t2;
      } else {
        return Double.POSITIVE_INFINITY;
      }
    }
  }

  /**
   * To get the minimum time for next collision and set the status for the ball based on collision.
   *
   * @param timesList list of times that hit top, bottom, right and left edges
   * @return minimum time taken for next collision
   */
  private double getMinValueAndSetStatus(List<Double> timesList) {
    List<Double> timesListPositive = new ArrayList<>();
    for (Double value : timesList) {
      if (value > 0) {
        timesListPositive.add(value);
      }
    }
    int minValueIndex = timesList.indexOf(Collections.min(timesListPositive));
    switch (minValueIndex) {
      case 0:
        status = "Ball hit right edge";
        break;
      case 1:
        status = "Ball hit left edge";
        break;
      case 2:
        status = "Ball hit bottom edge";
        break;
      case 3:
        status = "Ball hit top edge";
        break;
      default:
        status = "Ball is stationary";
        break;
    }
    return Collections.min(timesListPositive);
  }

}
