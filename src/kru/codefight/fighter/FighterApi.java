package kru.codefight.fighter;

import kru.codefight.fighter.attacks.AbstractAttack;

public class FighterApi {

  private Fighter fighter;

  public FighterApi(Fighter fighter) {
    this.fighter = fighter;
  }

  public void attack(AbstractAttack attack) {
    fighter.attack(attack);
  }

  public void changeStance(Stance stance) {
    fighter.setStance(stance);
  }

  public boolean tryScanOpponent(FighterStatus opponentStatus) {
    if (fighter.canSeeOpponent()) {
      opponentStatus.setFighter(fighter.getOpponent());
      return true;
    } else {
      return false;
    }
  }
}
