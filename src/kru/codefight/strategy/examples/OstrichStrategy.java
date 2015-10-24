package kru.codefight.strategy.examples;

import kru.codefight.fighter.Stance;
import kru.codefight.strategy.AbstractFighterStrategy;

public class OstrichStrategy extends AbstractFighterStrategy {
  @Override
  public void act() {
    Do().changeStance(Stance.BLOCKING);
  }
}
