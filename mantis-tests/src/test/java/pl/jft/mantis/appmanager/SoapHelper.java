package pl.jft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import pl.jft.mantis.model.Issue;
import pl.jft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by nishi on 2017-02-04.
 */
public class SoapHelper {

  private final ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible( "administrator", "root" );
    return Arrays.asList( projects ).stream()
            .map( (p) -> new Project().withId( p.getId().intValue() ).withName( p.getName() ) )
            .collect( Collectors.toSet() );
  }

  public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
            .getMantisConnectPort(new URL("http://localhost/mantisbt-2.0.0/api/soap/mantisconnect.php"));
  }

  public Set<Issue> getIssuesReportedBy(String username, String password) throws RemoteException, ServiceException, MalformedURLException {
    MantisConnectPortType mc = getMantisConnect();
    Set<Project> projects = getProjects();
    int accessLevel = 25;
    if (username.equals("administrator")) {
      accessLevel = 90;
    }
    AccountData[] users = mc.mc_project_get_users( username, password, BigInteger.valueOf(projects.iterator().next().getId()), BigInteger.valueOf(accessLevel));
    int j = 0;
    AccountData user = users[j];
    while (!user.getName().equals( username)) {
      user = users[++j];
    }
    AccountData finalUser = user;
    IssueData[] issueDatas = mc.mc_project_get_issues_for_user( username, password, BigInteger.valueOf(projects.iterator().next().getId()), "reported", finalUser, BigInteger.valueOf( 1 ), BigInteger.valueOf( -1 ) );
    return Arrays.asList( issueDatas ).stream()
            .map( (i) -> new Issue().withId( i.getId().intValue()).withSummary( i.getSummary() )
                    .withDescription(i.getDescription()).withProject( new Project().withId( finalUser.getId().intValue()).withName( finalUser.getName())))
            .collect( Collectors.toSet());
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories( "administrator", "root", BigInteger.valueOf( issue.getProject().getId() ) );
    IssueData issueData = new IssueData(  );
    issueData.setSummary( issue.getSummary() );
    issueData.setDescription( issue.getDescription() );
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add( "administrator", "root", issueData );
    IssueData createdIssueData = mc.mc_issue_get( "administrator", "root", issueId );
    return new Issue().withId( createdIssueData.getId().intValue() )
            .withSummary( createdIssueData.getSummary() ).withDescription( createdIssueData.getDescription() )
            .withProject( new Project().withId( createdIssueData.getProject().getId().intValue())
                                        .withName( createdIssueData.getProject().getName() ));
  }

}
