package agh.edu.hermes.generators;

public final class IdGenerator {

  private static long ruleID = 0;

  public static long getRuleId() {
    ruleID += 1;
    return ruleID;
  }
}
