package ru.stqa.pft.sandbox;

/**
 * Created by Alesia on 08.04.17.
 */
public class Circle {

  double r;

  public Circle(double r) {
    this.r = r;
  }

  public double area() {
    return this.r * this.r * Math.PI;
  }

}
