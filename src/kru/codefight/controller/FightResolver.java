package kru.codefight.controller;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public class FightResolver {
  public void resolveAttack(Fighter initiator, Fighter victim, AbstractAttack attack) {
    int damage;
    Stance victimStance = victim.getStance();
    switch (victimStance) {
      case NORMAL:
        damage = attack.getFullDamage();
        break;
      case BLOCKING:
        damage = attack.getBlockedDamage();
        break;
      case DODGING:
        damage = 0;
        break;
      default:
        throw new EnumConstantNotPresentException(victimStance.getClass(), victimStance.toString());
    }
    victim.takeDamage(damage);
  }
}
