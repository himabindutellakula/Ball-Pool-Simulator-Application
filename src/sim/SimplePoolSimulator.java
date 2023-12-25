package sim;

/**
 * A SimplePoolSimulator class that implements the PoolSimulator interface that handles mechanics of
 * a single ball moving within a pool table also trace the path followed by a ball on a simple
 * rectangular pool table.
 */
public class SimplePoolSimulator implements PoolSimulator {

  private final int tableWidth;
  private final int tableHeight;
  private Ball ball;
  private final String simulatorType;

  /**
   * Constructs a SimplePoolSimulator with width & height of the pool table and type of physics.
   *
   * @param width  represents the width of the used for pool table
   * @param height represents the height of the table used for pool table
   * @param type   represents the type of physics on which the ball simulates
   * @throws IllegalArgumentException for non-positive width/height & non-valid physics type
   */
  public SimplePoolSimulator(int width, int height, String type) {
    if (width <= 0 || height <= 0 || (!type.equals("simple") && !type.equals("friction"))) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    this.tableWidth = width;
    this.tableHeight = height;
    this.simulatorType = type;
  }

  @Override
  public void start(int x, int y, int radius, int speed, double dx, double dy)
      throws IllegalArgumentException {
    if (x - radius < 0 || y - radius < 0 || x + radius > tableWidth || y + radius > tableHeight
        || radius <= 0 || speed <= 0) {
      throw new IllegalArgumentException("Invalid ball parameters");
    }

    this.ball = (simulatorType.equals("simple")) ? new SimpleBall(x, y, radius, speed, dx, dy)
        : new NewtonianBall(x, y, radius, speed, dx, dy);
  }

  @Override
  public void advance() {
    if (ball != null) {
      ball.advance(tableWidth, tableHeight);
    }
  }

  @Override
  public int getTableWidth() {
    return this.tableWidth;
  }

  @Override
  public int getTableHeight() {
    return this.tableHeight;
  }

  @Override
  public double getBallPositionX() {
    return (ball != null) ? ball.getBallPositionX() : Double.NEGATIVE_INFINITY;
  }

  @Override
  public double getBallPositionY() {
    return (ball != null) ? ball.getBallPositionY() : Double.NEGATIVE_INFINITY;
  }

  @Override
  public double getBallRadius() {
    return (ball != null) ? ball.getBallRadius() : Double.NEGATIVE_INFINITY;
  }

  @Override
  public double getBallVelocityX() {
    return (ball != null) ? ball.getBallVelocityX() : 0;
  }

  @Override
  public double getBallVelocityY() {
    return (ball != null) ? ball.getBallVelocityY() : 0;
  }

  @Override
  public String getStatus() {
    return (ball != null) ? ball.getStatus() : "Status: Ball not set up";
  }
}