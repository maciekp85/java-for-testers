package pl.jft.rest.tests;

import org.testng.SkipException;
import pl.jft.rest.appmanager.ApplicationManager;
import pl.jft.rest.model.Issue;

import java.io.IOException;

/**
 * Created by nishi on 2016-12-10.
 */
public class TestBase {

  protected static final ApplicationManager app =
          new ApplicationManager();

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException( "ignored because of issue " + issueId );
    }
  }

  private boolean isIssueOpen(int issueId) throws IOException {
    Issue checkedIssue = app.issue().getIssueById(issueId).iterator().next();
    String status = checkedIssue.getState_name();
    if (status.equals( "resolved" ) || status.equals("closed")) {
      return false;
    } else {
      return true;
    }
  }
}
