package kru.codefight.strategy;

import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.Attacks;

public class OneTwoStrategy extends AbstractFighterStrategy {
  @Override
  public void act() {
    Do().changeStance(Stance.BLOCKING);
    Do().attack(Attacks.Jab);
    Do().attack(Attacks.Jab);
    Do().recoverStaminaUpTo(80);
  }
}
