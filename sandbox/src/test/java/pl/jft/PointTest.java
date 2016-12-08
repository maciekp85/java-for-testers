package pl.jft;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by nishi on 2016-12-08.
 */
public class PointTest {

  @Test
  public void testCheckIfPointsExist() {
    Point pointA = new Point(2,5);
    Point pointB = new Point(5, 9);
    Assert.assertNotNull(pointA);
    Assert.assertNotNull(pointB);
  }

  @Test
  public void testCheckIfDistanceBetweenPointsIsCorrect() {
    Point pointA = new Point(2, 5);
    Point pointB = new Point(5,9);

    Assert.assertEquals(pointA.distance(pointB), 5.0);
  }

  @Test
  public void testCheckIfDistanceBetweenPointsIsNotCorrect() {
    Point pointA = new Point(2, 5);
    Point pointB = new Point(5, 9);

    Assert.assertNotEquals(pointA.distance(pointB), 5);
  }

  @Test
  public void testCheckThatNoDistanceBetweenPoints() {
    Point pointA = new Point(3,3);
    Point pointB = new Point(3,3);

    Assert.assertEquals(pointA.distance(pointB), 0.0);
  }

}
