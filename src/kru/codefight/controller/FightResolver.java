package kru.codefight.controller;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.attacks.AbstractAttack;

public class FightResolver {

  private static final int MINIMAL_STUN_DURATION = 100;
  private static final int DAMAGE_MULTIPLIER = 2;
  private static final int STUN_MULTIPLIER = 2;

  public void resolveAttack(Fighter attacker, Fighter defender, AbstractAttack attack) {
    int damage = getDamage(attacker, defender, attack);
    int stunDuration = getStunDuration(attacker, defender, attack);
    defender.takeDamage(damage);
    if (stunDuration > 0) {
      defender.stunned(stunDuration);
    }
  }

  private int getDamage(Fighter attacker, Fighter defender, AbstractAttack attack) {
    int result;
    switch (defender.getStance()) {
      case NORMAL:
        result = attack.getFullDamage();
        if (defender.isAttacking()) {
          result *= DAMAGE_MULTIPLIER;
        }
        break;
      case BLOCKING:
        result = attack.getBlockedDamage();
        break;
      case DODGING:
        result = 0;
        break;
      default:
        throw new IllegalStateException();
    }
    return result;
  }

  private int getStunDuration(Fighter attacker, Fighter defender, AbstractAttack attack) {
    int result;
    switch (defender.getStance()) {
      case NORMAL:
        result = attack.getStunDurationInMs();
        if (defender.isAttacking()) {
          result = Math.max(STUN_MULTIPLIER * result, MINIMAL_STUN_DURATION);
        }
        break;
      case BLOCKING:
        result = attack.getStunDurationInMs() / 10;
        break;
      case DODGING:
        result = 0;
        break;
      default:
        throw new IllegalStateException();
    }
    return result;
  }
}
