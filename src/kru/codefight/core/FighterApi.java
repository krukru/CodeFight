package kru.codefight.core;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.FighterStatus;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public class FighterApi {

  private Fighter fighter;

  public FighterApi(Fighter fighter) {
    this.fighter = fighter;
  }

  public void attack(AbstractAttack attack) {
    if (!resolveAccumulatedStun()) {
      fighter.attack(attack);
    }
  }

  public void changeStance(Stance stance) {
    if (!resolveAccumulatedStun()) {
      fighter.setStance(stance);
    }
  }

  public void recoverStamina() {
    fighter.recoverStamina();
  }

  public void recoverStaminaUpTo(int targetStamina) {
    if (targetStamina < 0 || targetStamina > 100) {
      throw new IllegalArgumentException();
    }
    while (fighter.getStamina() < targetStamina) {
      recoverStamina();
    }
  }

  public boolean tryScanOpponent(FighterStatus opponentStatus) {
    if (!resolveAccumulatedStun()) {
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

  private boolean resolveAccumulatedStun() {
    if (fighter.getStunDuration() > 0) {
      try {
        Thread.sleep(fighter.getStunDuration());
      } catch (InterruptedException e) {
        //someone woke up the thread! @TODO possible bug!
        System.out.println("This should never happen!" + fighter.getColor().toString());
      } finally {
        fighter.resetStun();
      }
      return true;
    } else {
      return false;
    }
  }
}
