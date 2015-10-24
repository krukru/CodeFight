package kru.codefight.visualizer;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public abstract class AbstractFightVisualizer {
  public abstract void attackStarted(Fighter attacker, AbstractAttack attack);

  public abstract void attackLanded(Fighter attacker, Fighter defender, AbstractAttack attack);

  public abstract void attackInterrupted(Fighter attacker, AbstractAttack attack);

  public abstract void stanceChanged(Fighter fighter, Stance newStance);

  public abstract void staminaRecovered(Fighter fighter, int oldStamina);
}
