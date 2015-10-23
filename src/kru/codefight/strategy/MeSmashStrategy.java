package kru.codefight.strategy;

import kru.codefight.fighter.attacks.Attacks;

public class MeSmashStrategy extends AbstractFighterStrategy {
  @Override
  public void act() {
    Do().attack(Attacks.Haymaker);
    Do().recoverStaminaUpTo(90);
  }
}
