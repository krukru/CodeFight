package kru.codefight.strategy;

import kru.codefight.fighter.attacks.Attacks;

public class NewbieStrategy extends AbstractFighterStrategy {
  @Override
  public void act() {
    Do().attack(Attacks.Jab);
  }
}
