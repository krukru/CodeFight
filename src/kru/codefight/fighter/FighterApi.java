package kru.codefight.fighter;

import kru.codefight.fighter.attacks.AbstractAttack;

public class FighterApi {

  //@TODO: Think about moving the resolveStun to the Fighter class
  private Fighter fighter;

  public FighterApi(Fighter fighter) {
    this.fighter = fighter;
  }

  public void attack(AbstractAttack attack) {
    boolean interrupted = resolveStun();
    if (!interrupted) {
      fighter.attack(attack);
    }
  }

  public void changeStance(Stance stance) {
    boolean interrupted = resolveStun();
    if (!interrupted) {
      fighter.setStance(stance);
    }
  }

  public boolean tryScanOpponent(FighterStatus opponentStatus) {
    boolean interrupted = resolveStun();
    if (!interrupted) {
      if (fighter.canSeeOpponent()) {
        opponentStatus.setFighter(fighter.getOpponent());
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  private boolean resolveStun() {
    if (fighter.getStunDuration() > 0) {
      try {
        Thread.sleep(fighter.getStunDuration());
      } catch (InterruptedException e) {
        //someone woke up the thread! @TODO possible bug!
      } finally {
        fighter.resetStun();
      }
      return true;
    } else {
      return false;
    }
  }
}