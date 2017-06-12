package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alesia on 15.04.17.
 */
public class GroupHelper extends HelperBase {


  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModificatio() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModificatio();
    groupCache = null;
    returToGroupPage();
  }

  public void delete(int index) {
    selectGroup(index);
    deleteSelectedGroups();
    returToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCache = null;
    returToGroupPage();
  }

  public void deleteFromGroup(GroupData group) {
    selectGroupInTopList(group);
    selectContact();
    deleteFromSelectedGroup();
  }

  private void selectContact() {
    wd.findElement(By.name("selected[]")).click();
  }

  private void selectGroupInTopList(GroupData groupData) {
    Select dropdown = new Select(wd.findElement(By.name("group")));
    dropdown.selectByVisibleText(groupData.getName());
  }

  private void deleteFromSelectedGroup() {
    click(By.name("remove"));
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String groupname = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(groupname));
    }
    return groups;
  }

  private Groups groupCache = null;

  public Groups all() {
    if (groupCache != null){
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String groupname = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(groupname));
    }
    return new Groups(groupCache);
  }

//  public void removeTopGroupSelection() {
//
//    Select dropdown = new Select(wd.findElement(By.name("group")));
//    dropdown.selectByVisibleText("[all]");
//
//
//  }
}
