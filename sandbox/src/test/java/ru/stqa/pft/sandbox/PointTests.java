package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Alesia on 13.04.17.
 */
public class PointTests {

  @Test
  public void testPoint() {
    Point p1 = new Point(15, 5);
    Point p2 = new Point(15, 10);
    Assert.assertEquals(p1.distance(p2), 5.0);
  }

  @Test
  public void testPoint1() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testPoint2() {
    Point p1 = new Point(-10, -10);
    Point p2 = new Point(8, 1);
    Assert.assertEquals(p1.distance(p2), 21.095023109728988);
  }

}
