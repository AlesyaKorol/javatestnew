package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alesia on 05.05.17.
 */
public class ContactAddressTest extends TestBase {
  @BeforeMethod()
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().
              withFirstname("Olga02").withLastname("Test").withAddress("test").inGroup(groups.iterator().next()), true);
    }
  }


  @Test
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoContactEditForm(contact);
    System.out.println(contact.getAddress());
    System.out.println(contactInfoEditForm.getAddress());

    assertThat(contact.getAddress(), equalTo(contactInfoEditForm.getAddress()));


  }
}
