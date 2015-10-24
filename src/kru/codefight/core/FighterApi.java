package kru.codefight.core;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.FighterStatus;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.logger.AbstractFightVisualizer;
import kru.codefight.logger.Visualizer;
import kru.codefight.logger.VisualizerFactory;

public class FighterApi {

  private Fighter fighter;

  public FighterApi(Fighter fighter) {
    this.fighter = fighter;
  }

  public void attack(AbstractAttack attack) {
    resolveAccumulatedStun();
    fighter.attack(attack);
  }

  public void changeStance(Stance stance) {
    resolveAccumulatedStun();
    if (fighter.getStance() != stance) {
      delay(500); //stance change time, vidi kako ovo pametnije rješiti
      fighter.setStance(stance);
    }
  }
  public void recoverStamina() {
    fighter.recoverStamina();
    delay(500);
    fighter.decreaseStunDuration(500);
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
