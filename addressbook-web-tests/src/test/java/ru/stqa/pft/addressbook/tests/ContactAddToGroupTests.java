package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alesia on 21.05.17.
 */
public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().
              withFirstname("Olga02").withLastname("Test").withAddress("test"), true);
    }
    app.goTo().homePage();
  }


  @Test
  public void testContactAddToGroup() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData contactToAdd = before.iterator().next();
    ContactData contact = new ContactData().withId(contactToAdd.getId())
            .withFirstname(contactToAdd.getFirstname()).withLastname(contactToAdd.getLastname())
            .withHomephone(contactToAdd.getHomephone()).withMobilephone(contactToAdd.getMobilephone())
            .withWorkphone(contactToAdd.getWorkphone()).withEmai1(contactToAdd.getEmail()).withEmail2(contactToAdd.getEmail2())
            .withEmail3(contactToAdd.getEmail3()).withAddress(contactToAdd.getAddress())
            .inGroup(groups.iterator().next());

    app.contact().addToGroup(contact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(contactToAdd).withAdded(contact.withGroups(groups))));
  }

}
