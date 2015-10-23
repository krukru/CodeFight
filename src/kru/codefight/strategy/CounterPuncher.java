package kru.codefight.strategy;

import kru.codefight.fighter.FighterStatus;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.Attacks;

public class CounterPuncher extends AbstractFighterStrategy {

  private FighterStatus opponentStatus = new FighterStatus();
  @Override
  public void act() {
    Do().changeStance(Stance.DODGING);
    int counter = 0;
    Do().tryScanOpponent(opponentStatus);
    while (opponentStatus.isAttacking() == false && counter < 5) {
      Do().recoverStamina();
      counter++;
      Do().tryScanOpponent(opponentStatus);
    }
    Do().attack(Attacks.Jab);
  }
}
