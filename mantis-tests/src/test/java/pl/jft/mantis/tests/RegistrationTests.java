package pl.jft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by nishi on 2017-01-29.
 */
public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    app.registration().start("user1", "user1t@localhost.localdomain");
  }
}
