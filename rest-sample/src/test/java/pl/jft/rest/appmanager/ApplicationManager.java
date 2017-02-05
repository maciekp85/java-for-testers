package pl.jft.rest.appmanager;

import pl.jft.rest.model.IssueHelper;

/**
 * Created by nishi on 2016-12-10.
 */
public class ApplicationManager {

  private IssueHelper issueHelper;

  public ApplicationManager() {
  }

  public IssueHelper issue() {
    if (issueHelper == null) {
      issueHelper = new IssueHelper(this);
    }
    return issueHelper;
  }
}
