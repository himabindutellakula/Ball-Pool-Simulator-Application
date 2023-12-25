package sim;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * An abstract class for testing Balls that extend the Ball class.
 */
public abstract class BallTest {

  private Ball testBall;

  public abstract Ball createBall(double x, double y, double radius, double speed, double dx,
      double dy);

  @Before
  public void setUp() {
    testBall = createBall(294, 218, 15, 100, -0.420000, -0.340000);
  }

  @Test
  public void testGetBallPositionX() {
    assertEquals(294, testBall.getBallPositionX(), 0.01);
  }

  @Test
  public void testGetBallPositionY() {
    assertEquals(218, testBall.getBallPositionY(), 0.01);
  }

  @Test
  public void testGetBallRadius() {
    assertEquals(15, testBall.getBallRadius(), 0.01);
  }

  @Test
  public void testGetBallVelocityX() {
    assertEquals(-77.7244870, testBall.getBallVelocityX(), 0.0001);
  }

  @Test
  public void testGetBallVelocityY() {
    assertEquals(-62.9198228, testBall.getBallVelocityY(), 0.01);
  }

  @Test
  public void testGetStatus() {
    assertEquals("Status: Simulation started", testBall.getStatus());
  }

}