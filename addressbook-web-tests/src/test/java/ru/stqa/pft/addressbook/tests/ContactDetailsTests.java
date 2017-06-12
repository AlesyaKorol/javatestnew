package ru.stqa.pft.addressbook.tests;


import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alesya on 05/06/2017.
 */
//public class ContactDetailsTests extends TestBase {
//  @BeforeMethod()
//  public void ensurePreconditions() {
//    app.goTo().homePage();
//    Groups groups = app.db().groups();
//    if (app.db().contacts().size() == 0) {
//      app.contact().create(new ContactData().
//              withFirstname("Olga02").withLastname("Test").withHomephone("111").withMobilephone("222").withWorkphone("333")
//              .withEmai1("anna@gmail.com").withEmail2("test").withEmail3("test2")
//              .inGroup(groups.iterator().next()), true);
//    }
//  }
//
//  @Test
//  public void testContactDetailsMerge() {
//    ContactData contact = app.contact().all().iterator().next();
//    ContactData contactView = app.contact().infoContactDetailsMerge(contact);
//    ContactData contactInfoEditForm = app.contact().infoContactEditForm(contact);
//    assertThat(cleaned(contactView.getFirstname()), equalTo(mergedDetails(contactInfoEditForm)));
//  }
//
//  public static String cleaned(String phone) {
//    return phone.replaceAll("\\s", "");
//  }
//
//  private String mergedDetails(ContactData contact) {
//    String homephone = "";
//    String mobilephone = "";
//    String workphone = "";
//
//    if (!contact.getHomephone().equals("")) {
//      homephone = "H:" + contact.getHomephone();
//    }
//
//    if (!contact.getMobilephone().equals("")) {
//      mobilephone = "M:" + contact.getMobilephone();
//    }
//
//    if (!contact.getWorkphone().equals("")) {
//      workphone = "W:" + contact.getWorkphone();
//    }
//
//    return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getAddress(),
//            homephone, mobilephone, workphone,
//            contact.getEmail(), contact.getEmail2(), contact.getEmail3())
//            .stream().filter((s) -> !s.equals(""))
//            .map(ContactDetailsTests::cleaned)
//            .collect(Collectors.joining(""));
//  }
//
//}
