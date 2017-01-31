package pl.jft.mantis.model;

/**
 * Created by nishi on 2017-01-31.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }
}
