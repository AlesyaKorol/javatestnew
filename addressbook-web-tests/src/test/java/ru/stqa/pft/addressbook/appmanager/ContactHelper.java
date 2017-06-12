package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alesia on 15.04.17.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    if (contactData.getFirstname() == null) {
      contactData.withFirstname(wd.findElement(By.name("firstname")).getAttribute("value"));
    } else {
      type(By.name("firstname"), contactData.getFirstname());
    }

    if (contactData.getLastname() == null) {
      contactData.withLastname(wd.findElement(By.name("lastname")).getAttribute("value"));
    } else {
      type(By.name("lastname"), contactData.getLastname());
    }

    attach(By.name("photo"), contactData.getPhoto());

    if (contactData.getAddress() == null) {
      contactData.withAddress(wd.findElement(By.name("address")).getText());
    } else {
      type(By.name("address"), contactData.getAddress());
    }

    if (contactData.getHomephone() == null) {
      contactData.withHomephone(wd.findElement(By.name("home")).getAttribute("value"));
    } else {
      type(By.name("home"), contactData.getHomephone());
    }

    if (contactData.getMobilephone() == null) {
      contactData.withMobilephone(wd.findElement(By.name("mobile")).getAttribute("value"));
    } else {
      type(By.name("mobile"), contactData.getMobilephone());
    }

    if (contactData.getWorkphone() == null) {
      contactData.withWorkphone(wd.findElement(By.name("work")).getAttribute("value"));
    } else {
      type(By.name("work"), contactData.getWorkphone());
    }

    if (contactData.getEmail() == null) {
      contactData.withEmai1(wd.findElement(By.name("email")).getAttribute("value"));
    } else {
      type(By.name("email"), contactData.getEmail());
    }

    if (contactData.getEmail2() == null) {
      contactData.withEmail2(wd.findElement(By.name("email2")).getAttribute("value"));
    } else {
      type(By.name("email2"), contactData.getEmail2());
    }

    if (contactData.getEmail3() == null) {
      contactData.withEmail3(wd.findElement(By.name("email3")).getAttribute("value"));
    } else {
      type(By.name("email3"), contactData.getEmail3());
    }

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups()
                .iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  private void selectGroupInList(ContactData contactData) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(contactData.getGroups()
            .iterator().next().getName());

  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("img[alt='Edit']")).get(index).click();
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.xpath("//a[contains(@href,'edit.php?id=" + id + "')]")).click();

  }

  public void initContactView() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
  }

  private void initViewContactDetailsById(int id) {
    wd.findElement(By.xpath("//a[contains(@href,'view.php?id=" + id + "')]")).click();
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  private void addToSelectedGroup() {
    click(By.name("add"));
  }

  public void closeAlertWindow() {
    wd.switchTo().alert().accept();

  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(int index, ContactData contact) {
    initContactModification(index);
    fillContactForm((contact), false);
    submitContactModification();
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm((contact), false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void view(ContactData contact) {
    initViewContactDetailsById(contact.getId());
  }


  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    closeAlertWindow();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    closeAlertWindow();
    contactCache = null;
  }

  public void addToGroup(ContactData contact) {
    selectContactById(contact.getId());
    selectGroupInList(contact);
    addToSelectedGroup();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.xpath("td[3]")).getText();
      String lastname = element.findElement(By.xpath("td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      System.out.println("id " + id + " fn: " + firstname + " ln: " + lastname + " qty " + elements.size());
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }

  private Contacts contactCache = null;

  public ContactData infoContactEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String homephome = wd.findElement(By.name("home")).getAttribute("value");
    String mobilephome = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphome = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address)
            .withHomephone(homephome)
            .withMobilephone(mobilephome).withWorkphone(workphome)
            .withEmai1(email).withEmail2(email2).withEmail3(email3);
  }

  public ContactData infoContactDetailsSplit(ContactData contact) {
    initViewContactDetailsById(contact.getId());
    String[] details = wd.findElement(By.xpath("//div[@id='content']")).getText().split("\n");
    String[] names = details[0].split("\\s");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(names[0]).withLastname(names[1])
            .withAddress(details[1])
            .withHomephone(details[3]).withMobilephone(details[4]).withWorkphone(details[5])
            .withEmai1(details[7]).withEmail2(details[8]).withEmail3(details[9]);
  }

  public ContactData infoContactDetailsMerge(ContactData contact) {
    initViewContactDetailsById(contact.getId());
    String details = wd.findElement(By.xpath("//div[@id='content']")).getText();
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(details);
  }

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.xpath("td[3]")).getText();
      String lastname = element.findElement(By.xpath("td[2]")).getText();
      String allEmails = element.findElement(By.xpath("td[5]")).getText();
      String allPhones = element.findElement(By.xpath("td[6]")).getText();
      String address = element.findElement(By.xpath("td[4]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address)
              .withAllPhones(allPhones).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

}
