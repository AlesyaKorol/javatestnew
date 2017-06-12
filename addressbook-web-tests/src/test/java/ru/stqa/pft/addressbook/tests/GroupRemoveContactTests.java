package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alesia on 23.05.17.
 */
public class GroupRemoveContactTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("GROUP1"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testGroupRemoveContact() {
    Groups before = app.db().groups();
    GroupData groupInTopList = before.iterator().next();
    GroupData group = new GroupData().withId(groupInTopList.getId())
            .withName(groupInTopList.getName()).withHeader(groupInTopList.getHeader()).
                    withFooter(groupInTopList.getFooter());

    if (groupInTopList.getContacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().
              withFirstname("Kate").withLastname("Smirnova").withAddress("Minsk")
              .inGroup(group), true);
    }
    app.group().deleteFromGroup(group);
    Groups after = app.db().groups();

    assertThat(after, equalTo(before.without(groupInTopList).withAdded(group)));

  }
}


