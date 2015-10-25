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

  //TODO before making any call from the api, you must check if the fighter has been knocked out
  //because he won't stop fighting until one tick od act() finishes (could be a few more punches)

  public void attack(AbstractAttack attack) {
    resolveAccumulatedStun();
    if (fighter.getStance() == Stance.BLOCKING) {
      changeStance(Stance.NORMAL);
    }
    fighter.attack(attack);
  }

  public void changeStance(Stance stance) {
    resolveAccumulatedStun();
    if (fighter.getStance() != stance) {
      fighter.setStance(stance);
    }
  }
  public void recoverStamina() {
    fighter.recoverStamina();
    delay(1000);
    fighter.decreaseStunDuration(1000);
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
    resolveAccumulatedStun();
    //doesn't have a delay
    if (fighter.canSeeOpponent()) {
      opponentStatus.loadStatus(fighter.getOpponent());
      return true;
    } else {
      return false;
    }
  }

  public void waitFor(long milis) {
    if (milis <= 0) {
      throw new IllegalArgumentException();
    }
    delay(milis);
    fighter.decreaseStunDuration(milis);
  }


  private void delay(long milis) {
    try {
      Thread.sleep(milis);
    } catch (InterruptedException e) {
      System.out.println("This should never happen!" + fighter.getColor().toString());
    }
  }

  private void resolveAccumulatedStun() {
    if (fighter.getStunDuration() > 0) {
      try {
        Thread.sleep(fighter.getStunDuration());
      } catch (InterruptedException e) {
        System.out.println("This should never happen!" + fighter.getColor().toString());
      } finally {
        fighter.resetStun();
      }
    }
  }
}
