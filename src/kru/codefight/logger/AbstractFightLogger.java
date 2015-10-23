package kru.codefight.logger;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public abstract class AbstractFightLogger {
  public abstract void attack(Fighter attacker, Fighter defender, AbstractAttack attack, int damage);

  public abstract void stanceChange(Fighter initiator, Stance newStance);

  public abstract void attackInterrupted(Fighter attacker, AbstractAttack attack);

  public abstract void recoverStamina(Fighter attacker, int recoverAmount);
}
