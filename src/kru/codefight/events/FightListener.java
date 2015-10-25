package kru.codefight.events;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public interface FightListener {
  public abstract void attackStarted(Fighter attacker, AbstractAttack attack);

  public abstract void attackLanded(Fighter attacker, Fighter defender, AbstractAttack attack);

  public abstract void attackInterrupted(Fighter attacker, AbstractAttack attack);

  public abstract void stanceChanged(Fighter fighter, Stance newStance);

  public abstract void staminaRecovered(Fighter fighter, int oldStamina);

  //stamina changed event umjesto stamina recovered
}
