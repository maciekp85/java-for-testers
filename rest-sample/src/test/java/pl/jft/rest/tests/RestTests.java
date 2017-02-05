package pl.jft.rest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * Created by nishi on 2017-02-05.
 */
public class RestTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() throws IOException {
    Set<Issue> issues = app.issue().all();
    for (Issue issue: issues) {
      skipIfNotFixed(issue.getId());
    }
  }

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = app.issue().all();
    Issue newIssue = new Issue().withSubject( "Test issue" ).withDescription( "New test issue" );
    int issueId = app.issue().create( newIssue );
    Set<Issue> newIssues = app.issue().all();
    oldIssues.add(newIssue.withId(issueId));
    Assert.assertEquals(newIssues, oldIssues);
  }

}