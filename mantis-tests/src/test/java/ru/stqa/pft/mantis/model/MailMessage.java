package ru.stqa.pft.mantis.model;

/**
 * Created by Alesia on 28.05.17.
 */
public class MailMessage {
  public String to;
  public String text;

  public MailMessage (String to, String text){
    this.to = to;
    this.text = text;
  }

}
