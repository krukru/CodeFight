package kru.codefight.fighter;

import kru.codefight.fighter.attacks.AbstractAttack;

public class FighterApi {

  private Fighter fighter;

  public FighterApi(Fighter fighter) {
    this.fighter = fighter;
  }

  public void attack(AbstractAttack attack) {
    resolveStun();
    fighter.attack(attack);
  }

  public void changeStance(Stance stance) {
    resolveStun();
    fighter.setStance(stance);
  }

  public boolean tryScanOpponent(FighterStatus opponentStatus) {
    resolveStun();
    if (fighter.canSeeOpponent()) {
      opponentStatus.setFighter(fighter.getOpponent());
      return true;
    } else {
      return false;
    }
  }

  private void resolveStun() {
    if (fighter.getStunDuration() > 0) {
      try {
        Thread.sleep(fighter.getStunDuration());
      } catch (InterruptedException e) {
        //someone woke up the thread! @TODO possible bug!
      } finally {
        fighter.setStunDuration(0);
      }
    }
  }
}