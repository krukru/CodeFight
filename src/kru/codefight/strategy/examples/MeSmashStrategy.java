package kru.codefight.strategy.examples;

import kru.codefight.fighter.attacks.Attacks;
import kru.codefight.strategy.AbstractFighterStrategy;

public class MeSmashStrategy extends AbstractFighterStrategy {
  @Override
  public void act() {
    Do().attack(Attacks.Haymaker);
    Do().recoverStaminaUpTo(90);
  }
}
