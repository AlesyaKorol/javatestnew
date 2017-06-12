package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.Users;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;


/**
 * Created by Alesya on 05/19/2017.
 */
public class DbHelper {
  private final SessionFactory sessionFactory;
  private final ApplicationManager app;


//  public DbHelper() {
//    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//            .configure() // configures settings from hibernate.cfg.xml
//            .build();
//    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//  }

  public DbHelper(ApplicationManager app) {
    this.app = app;
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData").list();
    for (UserData user : result) {
      System.out.println("Users " + user);
    }
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

}

