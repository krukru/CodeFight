package kru.codefight.strategy;

import kru.codefight.fighter.FighterStatus;

import java.util.function.Predicate;

public class ConditionalStrategy implements Comparable<ConditionalStrategy> {

  private Predicate<FighterStatus> predicate;
  private Integer priority;
  private Runnable action;

  public ConditionalStrategy(Predicate<FighterStatus> predicate, Integer priority, Runnable action) {
    this.predicate = predicate;
    this.priority = priority;
    this.action = action;
  }

  public void act() {
    action.run();
  }

  public boolean predicateSatisfied(FighterStatus status) {
    return predicate.test(status);
  }

  @Override
  public int compareTo(ConditionalStrategy secondCondition) {
    return priority.compareTo(secondCondition.priority);
  }
}
