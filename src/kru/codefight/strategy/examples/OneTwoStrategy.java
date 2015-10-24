package kru.codefight.strategy.examples;

import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.Attacks;
import kru.codefight.strategy.AbstractFighterStrategy;

public class OneTwoStrategy extends AbstractFighterStrategy {
  @Override
  public void act() {
    Do().changeStance(Stance.BLOCKING);
    Do().attack(Attacks.Jab);
    Do().attack(Attacks.Jab);
    Do().recoverStaminaUpTo(80);
  }
}
