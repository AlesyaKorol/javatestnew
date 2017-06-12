package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alesia on 15.04.17.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
      public void ensurePreconditions() {
      Groups groups = app.db().groups();
      app.goTo().homePage();
      if (app.db().contacts().size() == 0) {
        app.contact().create(new ContactData().
                withFirstname("Olga02").withLastname("Test").withAddress("test").inGroup(groups.iterator().next()), true);
      }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }


}

