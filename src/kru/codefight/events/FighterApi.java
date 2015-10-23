package kru.codefight.events;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.attacks.AbstractAttack;

public class FighterApi {

  private Fighter fighter;

  public FighterApi(Fighter fighter) {
    this.fighter = fighter;
  }

  private FightListener listener;

  public void subscribeToAttackHappened(FightListener listener) {
    if (listener == null) {
      throw new NullPointerException("If you want to unsubscribe, there's a method for that.");
    }
    this.listener = listener;
  }

  public void unsubscribeFromAttackHappened() {
    this.listener = null;
  }

  public void attack(AbstractAttack attack) {
    if (listener == null) {
      throw new NullPointerException("Attack happened, but no listener was set!");
    }
    int castTime = attack.getCastTimeInMs();
    try {
      Thread.sleep(castTime);
    } catch (InterruptedException e) {
      //Attack was interrupted
    }
    listener.attackHappened(fighter, attack);
  }
}
