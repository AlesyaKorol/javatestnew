package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    yoda("Harry Potter");
    yoda("Yoda");

    Square s = new Square(9);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(5, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(5, 5);
    Point p2 = new Point(15, 10);

    System.out.println("Расстояние между точкой А с кординатами (" + p1.x
            + "," + p1.y + ") и точкой B с кординатами" + "(" + p2.x + ","
            + p2.y + ")" + " = " + p1.distance(p2));

    Circle c = new Circle(5);
    System.out.println("Площадь круга с радиусом " + c.r + " = " + c.area());

  }

  public static void yoda(String somebody) {
    System.out.println("Do or do not, there is no try, said " + somebody);
  }


}
