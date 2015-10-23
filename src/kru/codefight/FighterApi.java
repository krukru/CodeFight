package kru.codefight;

import kru.codefight.events.FightListener;
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
      throw new NullPointerException("Nice job numnuts. Use unsubscribe insted.");
    }
    this.listener = listener;
  }

  public void unsubscribeFromAttackHappened() {
    this.listener = null;
  }

  public void Attack(AbstractAttack attack) {
    if (listener != null) {
      listener.AttackHappened(fighter, attack);
    }
  }
}
