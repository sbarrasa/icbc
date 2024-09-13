package com.ebanking.utils.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringPatternConverter implements BidirectionalConverter<String, List<String>> {
  private final Pattern pattern;

  public StringPatternConverter(String regex){
    this.pattern = Pattern.compile(regex);
  }
  @Override
  public List<String> from(String input) throws Exception {
      Matcher matcher = pattern.matcher(input);
      var a = matcher.results();

      return matcher.results()
              .map(MatchResult::group)
              .toList();
  }

  @Override
  public String to(List<String> data) throws Exception {
    return String.format(pattern.pattern(), data.toArray());
  }
}
