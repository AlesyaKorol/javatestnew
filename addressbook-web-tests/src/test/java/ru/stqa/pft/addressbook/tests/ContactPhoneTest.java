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
 * Created by Alesya on 05/05/2017.
 */
public class ContactPhoneTest extends TestBase {

  @BeforeMethod()
  public void ensurePreconditions() {
    app.goTo().homePage();
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().
              withFirstname("Olga02").withLastname("Test").withHomephone("111").withMobilephone("222").
              withWorkphone("333").withEmai1("anna@gmail.com").inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactPhone() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoEditForm = app.contact().infoContactEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergedPhones(contactInfoEditForm)));
  }

  private String mergedPhones(ContactData contact) {
    return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
