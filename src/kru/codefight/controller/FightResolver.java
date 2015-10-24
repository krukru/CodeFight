package kru.codefight.controller;

import kru.codefight.core.FightOutcome;
import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.thread.FighterThread;

public class FightResolver {

  private static final int MINIMAL_STUN_DURATION = 100;
  private static final int DAMAGE_MULTIPLIER = 2;
  private static final int STUN_OPEN_MODIFIER = 2;
  public static final double STUN_BLOCKING_MODIFIER = 0.2;

  public void resolveAttack(FighterThread attackerThread, FighterThread defenderThread,
                            AbstractAttack attack) {
    Fighter attacker = attackerThread.getFighter();
    Fighter defender = defenderThread.getFighter();

    int damage = getDamage(attacker, defender, attack);
    if (damage > 0) {
      defender.takeDamage(damage);
      int stunDuration = getStunDuration(attacker, defender, attack);
      if (stunDuration > 0) {
        defender.addStunDuration(stunDuration);
        if (defender.isAttacking() && defenderThread.getState() == Thread.State.TIMED_WAITING) {
          //if he's attacking, and still casting the attack
          defenderThread.interrupt();
        }
      }
    }
  }

  private int getDamage(Fighter attacker, Fighter defender, AbstractAttack attack) {
    int result;
    switch (defender.getStance()) {
      case NORMAL:
        result = attack.getFullDamage();
        break;
      case OPEN:
        result = DAMAGE_MULTIPLIER * attack.getFullDamage();
        break;
      case BLOCKING:
        result = attack.getBlockedDamage();
        break;
      case DODGING:
        result = (attack.isDodgeable()) ? attack.getFullDamage() : 0;
        break;
      default:
        throw new IllegalStateException();
    }
    result *= attacker.getAttackIntensityFactor();
    return result;
  }

  private int getStunDuration(Fighter attacker, Fighter defender, AbstractAttack attack) {
    int result;
    switch (defender.getStance()) {
      case NORMAL:
        //fallthrough
      case DODGING:
        result = attack.getStunDurationInMs();
        break;
      case OPEN:
        result = Math.max(STUN_OPEN_MODIFIER * attack.getStunDurationInMs(), MINIMAL_STUN_DURATION);
        break;
      case BLOCKING:
        result = (int)(STUN_BLOCKING_MODIFIER * attack.getStunDurationInMs());
        break;
      default:
        throw new IllegalStateException();
    }
    result *= attacker.getAttackIntensityFactor();
    return result;
  }

  public FightOutcome determineOutcome(Fighter redFighter, Fighter blueFighter) {
    if (redFighter.isKnockedOut() && blueFighter.isKnockedOut()) {
      return FightOutcome.MUTUAL_KO;
    } else if (redFighter.isKnockedOut() == false && blueFighter.isKnockedOut()) {
      return FightOutcome.RED_WON;
    } else if (redFighter.isKnockedOut() && blueFighter.isKnockedOut() == false) {
      return FightOutcome.BLUE_WON;
    } else {
      return FightOutcome.NO_WINNER;
    }
  }
}
