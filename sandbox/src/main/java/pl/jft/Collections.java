package pl.jft;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nishi on 2016-12-27.
 */
public class Collections {

  public static void main(String[] args) {
    String[] langs = new String[]{"Java", "C#", "Python", "PHP"};

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l: languages) {
      System.out.println("I want to learn " + l);
    }
  }
}
