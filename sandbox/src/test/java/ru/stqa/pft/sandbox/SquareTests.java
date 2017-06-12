package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Alesia on 13.04.17.
 */
public class SquareTests {

  @Test
  public void testArea () {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);

      }

   @Test
  public void testArea1 () {
     Square s = new Square(10);
     Assert.assertEquals(s.area(), 100.0);
   }
}
