package sim;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A JUnit test class for the SimplePoolSimulator class.
 */
public class SimplePoolSimulatorTest {

  @Test
  public void testStartSimple() {
    PoolSimulator simulator5 = new SimplePoolSimulator(400, 400, "simple");
    simulator5.advance();
    simulator5.start(161, 63, 18, 113, -0.28000, -0.33000);
    simulator5.advance();
    assertEquals(122.818181, simulator5.getBallPositionX(), 0.0001);
    assertEquals(17.9999999, simulator5.getBallPositionY(), 0.0001);
    assertEquals("Status: Ball hit bottom edge", simulator5.getStatus());
    simulator5.advance();
    assertEquals("Status: Ball hit left edge", simulator5.getStatus());
  }

  @Test
  public void testStartSimple2() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "simple");
    simulator4.start(90, 175, 9, 188, 0.410000, 0.160000);
    assertEquals(175.136592463, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(68.3459873027, simulator4.getBallVelocityY(), 0.0001);
    simulator4.advance();
    assertEquals(391, simulator4.getBallPositionX(), 0.0001);
    assertEquals(292.46341463, simulator4.getBallPositionY(), 0.0001);
    assertEquals(-170.478704365, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(66.52827487444529, simulator4.getBallVelocityY(), 0.0001);
    assertEquals("Status: Ball hit right edge", simulator4.getStatus());

    simulator4.advance();
    assertEquals(138.5, simulator4.getBallPositionX(), 0.0001);
    assertEquals(391, simulator4.getBallPositionY(), 0.0001);
    assertEquals(-165.8208162, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(-64.710562, simulator4.getBallVelocityY(), 0.0001);
    assertEquals("Status: Ball hit top edge", simulator4.getStatus());
  }

  @Test
  public void testStartFriction() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "friction");
    simulator4.start(90, 175, 9, 188, 0.410000, 0.160000);
    assertEquals(175.136592463, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(68.3459873027, simulator4.getBallVelocityY(), 0.0001);
    simulator4.advance();
    assertEquals(391, simulator4.getBallPositionX(), 0.0001);
    assertEquals(292.46341463, simulator4.getBallPositionY(), 0.0001);
    assertEquals(-173.558842118, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(67.7302798511, simulator4.getBallVelocityY(), 0.0001);
    assertEquals("Status: Ball hit right edge", simulator4.getStatus());

    simulator4.advance();
    assertEquals(138.5, simulator4.getBallPositionX(), 0.0001);
    assertEquals(390.99999999999, simulator4.getBallPositionY(), 0.0001);
    assertEquals(-172.22416632, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(-67.2094307616, simulator4.getBallVelocityY(), 0.0001);
    assertEquals("Status: Ball hit top edge", simulator4.getStatus());

    simulator4.advance();
    assertEquals(8.999999, simulator4.getBallPositionX(), 0.0001);
    assertEquals(340.463414634, simulator4.getBallPositionY(), 0.0001);
    assertEquals(171.53562066, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(-66.940730015, simulator4.getBallVelocityY(), 0.0001);
    assertEquals("Status: Ball hit left edge", simulator4.getStatus());
  }

  @Test
  public void testStartFrictionVerticalBouncing() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "friction");
    simulator4.start(90, 175, 9, 188, 0, 0.160000);
    assertEquals(0, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(188, simulator4.getBallVelocityY(), 0.0001);

    simulator4.advance();
    assertEquals(90, simulator4.getBallPositionX(), 0.0001);
    assertEquals(390.99999, simulator4.getBallPositionY(), 0.0001);
    assertEquals(0, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(-186.86949456, simulator4.getBallVelocityY(), 0.0001);
    assertEquals("Status: Ball hit top edge", simulator4.getStatus());

  }

  @Test
  public void testStartFrictionHorizontalBouncing() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "friction");
    simulator4.start(90, 175, 9, 188, 0.410000, 0);
    assertEquals(188, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(0, simulator4.getBallVelocityY(), 0.0001);

    simulator4.advance();
    assertEquals(390.99999, simulator4.getBallPositionX(), 0.0001);
    assertEquals(175, simulator4.getBallPositionY(), 0.0001);
    assertEquals(-186.42274002, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(0, simulator4.getBallVelocityY(), 0.0001);
    assertEquals("Status: Ball hit right edge", simulator4.getStatus());
  }

  @Test
  public void testStartSimpleVerticalBouncing() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "simple");
    simulator4.start(90, 175, 9, 188, 0, -0.160000);
    assertEquals(0, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(-188, simulator4.getBallVelocityY(), 0.0001);

    simulator4.advance();
    assertEquals(90, simulator4.getBallPositionX(), 0.0001);
    assertEquals(9, simulator4.getBallPositionY(), 0.0001);
    assertEquals(0, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(183, simulator4.getBallVelocityY(), 0.0001);
    assertEquals("Status: Ball hit bottom edge", simulator4.getStatus());
  }

  @Test
  public void testStartSimpleHorizontalBouncing() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "simple");
    simulator4.start(90, 175, 9, 188, -0.410000, 0);
    assertEquals(-188, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(0, simulator4.getBallVelocityY(), 0.0001);

    simulator4.advance();
    assertEquals(9, simulator4.getBallPositionX(), 0.0001);
    assertEquals(175, simulator4.getBallPositionY(), 0.0001);
    assertEquals(183, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(0, simulator4.getBallVelocityY(), 0.0001);
    assertEquals("Status: Ball hit left edge", simulator4.getStatus());
  }


  @Test(timeout = 500)
  public void testStartSimpleAdvance20Times() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "simple");
    simulator4.start(294, 218, 15, 100, -0.420000, -0.340000);
    for (int i = 0; i < 20; i++) {
      simulator4.advance();
    }
    assertEquals("Status: Ball is stationary", simulator4.getStatus());
  }

  @Test(timeout = 500)
  public void testStartSimpleAdvance40Times() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "simple");
    simulator4.start(294, 218, 15, 200, -0.420000, -0.340000);
    assertEquals(-155.4489741, simulator4.getBallVelocityX(), 0.0001);
    assertEquals(-125.839645, simulator4.getBallVelocityY(), 0.0001);
    for (int i = 0; i < 40; i++) {
      simulator4.advance();
    }
    assertEquals("Status: Ball is stationary", simulator4.getStatus());
  }

  @Test(timeout = 500)
  public void testStartSimpleAdvance100Times() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "simple");
    simulator4.start(294, 218, 15, 500, -0.420000, -0.340000);
    for (int i = 0; i < 100; i++) {
      simulator4.advance();
    }
    assertEquals("Status: Ball is stationary", simulator4.getStatus());
  }

  @Test(timeout = 500)
  public void testStartFrictionManyAdvances() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "friction");
    simulator4.start(90, 175, 9, 50, 0.410000, 0.160000);

    int i = 0;
    while (!simulator4.getStatus().equals("Status: Ball is stationary")) {
      i = i + 1;
      System.out.println("i ->" + i);
      simulator4.advance();
    }
    assertEquals(5, i);
    assertEquals(268.97449, simulator4.getBallPositionX(), 0.0001);
    assertEquals(143.77053, simulator4.getBallPositionY(), 0.0001);
    assertEquals("Status: Ball is stationary", simulator4.getStatus());
  }

  @Test
  public void testStartSimpleManyAdvances() {
    PoolSimulator simulator4 = new SimplePoolSimulator(400, 400, "simple");
    simulator4.start(90, 175, 9, 18, 0.410000, 0.160000);
    int i = 0;
    while (!simulator4.getStatus().equals("Status: Ball is stationary")) {
      i = i + 1;
      simulator4.advance();
    }
    assertEquals(4, i);
    assertEquals(391, simulator4.getBallPositionX(), 0.0001);
    assertEquals(191.390243, simulator4.getBallPositionY(), 0.0001);
  }

  @Test
  public void testNoCollisionWithEdgesAndFriction() {
    SimplePoolSimulator simulator = new SimplePoolSimulator(100, 100, "friction");
    simulator.start(50, 50, 5, 10, 1, 1);

    simulator.advance();

    assertEquals(86.04, simulator.getBallPositionX(), 0.01);
    assertEquals(86.04, simulator.getBallPositionY(), 0.01);
    assertEquals(0.0, simulator.getBallVelocityX(), 0.01);
    assertEquals(0.0, simulator.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball is stationary", simulator.getStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructorBadHeight() {
    SimplePoolSimulator s = new SimplePoolSimulator(10, -100, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructorBadWidth() {
    SimplePoolSimulator s = new SimplePoolSimulator(-10, -100, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructorBadWidthAndHeight() {
    SimplePoolSimulator s = new SimplePoolSimulator(0, 0, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructorBadTypeSimple() {
    SimplePoolSimulator s = new SimplePoolSimulator(10, -100, "SIMPLE");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructorBadTypeFriction() {
    SimplePoolSimulator s = new SimplePoolSimulator(10, -100, "Friction");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructorBadType() {
    SimplePoolSimulator s = new SimplePoolSimulator(10, -100, "XYZ");
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidStartBadXY() {
    SimplePoolSimulator s = new SimplePoolSimulator(100, 100, "simple");
    s.start(150, 150, 5, 40, 9.9, 8.9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidStartBadXY2() {
    SimplePoolSimulator s = new SimplePoolSimulator(100, 100, "friction");
    s.start(96, 96, 5, 40, 9.9, 8.9);
  }

}