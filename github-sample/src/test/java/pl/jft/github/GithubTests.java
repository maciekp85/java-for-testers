package pl.jft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by nishi on 2017-02-05.
 */
public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub( "6c2efe5095dae11bea7d6b413a0492f65c16a706" );
    RepoCommits commits = github.repos().get( new Coordinates.Simple( "maciekp85", "java-for-testers")).commits();
    for (RepoCommit commit: commits.iterate( new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
