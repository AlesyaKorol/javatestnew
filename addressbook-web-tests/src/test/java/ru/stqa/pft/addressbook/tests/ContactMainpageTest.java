package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alesia on 06.05.17.
 */
public class ContactMainpageTest extends TestBase {

  @BeforeMethod()
  public void ensurePreconditions() {
    app.goTo().homePage();
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Kate").withLastname("Test").withAddress("Tests").withHomephone("111")
              .withMobilephone("222").withWorkphone("333")
              .withEmai1("anna@gmail.com").withEmail2("email@mail.com").withEmail3("hello@gmail.test")
              .inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactMainpage() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoContactEditForm(contact);
    System.out.println(contact.getAddress());
    System.out.println(contactInfoEditForm.getAddress());
    System.out.println(contact.getAllPhones());
    System.out.println(mergedPhones(contactInfoEditForm));
    System.out.println(contact.getAllEmails());
    System.out.println(mergedEmails(contactInfoEditForm));

    assertThat(contact.getAddress(), equalTo(contactInfoEditForm.getAddress()));
    assertThat(contact.getAllPhones(), equalTo(mergedPhones(contactInfoEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergedEmails(contactInfoEditForm)));
  }


  private String mergedPhones(ContactData contact) {
    return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactMainpageTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  private String mergedEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }


}


