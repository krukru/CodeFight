package kru.codefight.strategy;

import kru.codefight.fighter.Stance;

public class OstrichStrategy extends AbstractFighterStrategy {
  @Override
  public void act() {
    Do().changeStance(Stance.BLOCKING);
  }
}
