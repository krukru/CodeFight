package kru.codefight.controller;

import kru.codefight.FightOutcome;
import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.thread.FighterThread;

public class FightResolver {

  private static final int MINIMAL_STUN_DURATION = 100;
  private static final int DAMAGE_MULTIPLIER = 2;
  private static final int STUN_MULTIPLIER = 2;

  public void resolveAttack(FighterThread attackerThread, FighterThread defenderThread,
                            AbstractAttack attack) {
    Fighter attacker = attackerThread.getFighter();
    Fighter defender = defenderThread.getFighter();

    int damage = getDamage(attacker, defender, attack);
    if (damage > 0) {
      defender.takeDamage(damage);
    }

    int stunDuration = getStunDuration(attacker, defender, attack);
    if (stunDuration > 0) {
      //@TODO: refactor
      defender.addStunDuration(stunDuration);
      if (defender.isAttacking()) {
        defenderThread.interrupt();
      }
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
