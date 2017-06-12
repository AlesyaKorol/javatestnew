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
 * Created by Alesia on 05.05.17.
 */
public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().
              withFirstname("Olga02").withLastname("Test")
              .withEmai1("anna@gmail.com").withEmail2("hello@test.ru")
              .withEmail3("test44@.com").inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactEmail() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoContactEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergedEmails(contactInfoEditForm)));
  }

  private String mergedEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
