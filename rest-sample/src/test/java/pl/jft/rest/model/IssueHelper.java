package pl.jft.rest.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import pl.jft.rest.appmanager.ApplicationManager;

import java.io.IOException;
import java.util.Set;

/**
 * Created by nishi on 2017-02-05.
 */
public class IssueHelper {

  private final ApplicationManager app;

  public IssueHelper(ApplicationManager app) {
    this.app = app;
  }

  public int create(Issue newIssue) throws IOException {
    String json = getExecutor().auth( "LSGjeU4yP1X493ud1hNniA==", "" ).execute( Request.Post( "http://demo.bugify.com/api/issues.json" )
            .bodyForm( new BasicNameValuePair( "subject", newIssue.getSubject() ),
                    new BasicNameValuePair( "description", newIssue.getDescription() )))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse( json );
    return parsed.getAsJsonObject().get( "issue_id" ).getAsInt();
  }

  public Set<Issue> all() throws IOException {
    String json = getExecutor().auth( "LSGjeU4yP1X493ud1hNniA==", "" ).execute( Request.Get( "http://demo.bugify.com/api/issues.json" ) ).returnContent().asString();
    JsonElement parsed = new JsonParser().parse( json );
    JsonElement issues = parsed.getAsJsonObject().get( "issues" );
    return new Gson().fromJson( issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  public Set<Issue> getIssueById(int issueId) throws IOException {
    String json = getExecutor().auth( "LSGjeU4yP1X493ud1hNniA==", "" ).execute( Request.Get( "http://demo.bugify.com/api/issues/" + issueId + ".json" ) ).returnContent().asString();
    JsonElement parsed = new JsonParser().parse( json );
    JsonElement issues = parsed.getAsJsonObject().get( "issues" );
    return new Gson().fromJson( issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  public Executor getExecutor() {
    return Executor.newInstance();
  }

}
