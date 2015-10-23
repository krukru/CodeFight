package kru.codefight.controller;

import sun.plugin.dom.exception.InvalidStateException;

import kru.codefight.FightOutcome;
import kru.codefight.events.FightListener;
import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.thread.FighterThread;

public class FightController implements FightListener {

  private Fighter redFighter;
  private Fighter blueFighter;

  private FighterThread redFighterThread;
  private FighterThread blueFighterThread;

  private FightResolver fightResolver = new FightResolver();

  public FightOutcome resolveFight(Fighter redFighter, Fighter blueFighter) {

    this.redFighter = redFighter;
    this.blueFighter = blueFighter;

    redFighter.subscribeToAttackHappened(this);
    blueFighter.subscribeToAttackHappened(this);

    this.redFighterThread = new FighterThread(redFighter, blueFighter);
    this.blueFighterThread = new FighterThread(blueFighter, redFighter);

    redFighterThread.start();
    blueFighterThread.start();

    try {
      redFighterThread.join();
      blueFighterThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return fightResolver.determineOutcome(redFighter, blueFighter);
  }

  @Override
  public void attackHappened(Fighter attacker, AbstractAttack attack) {
    FighterThread attackerThread = getFighterThread(attacker);
    FighterThread defenderThread = getOpponentThread(attacker);
    fightResolver.resolveAttack(attackerThread, defenderThread, attack);
    System.out.println("Red hp:" + redFighter.getHitPoints());
    System.out.println("Blue hp:" + blueFighter.getHitPoints());
    if (defenderThread.getFighter().isKnockedOut()) {
      endFight();
    }
  }

  private FighterThread getFighterThread(Fighter attacker) {
    if (attacker == redFighter) {
      return redFighterThread;
    } else if (attacker == blueFighter) {
      return blueFighterThread;
    } else {
      throw new InvalidStateException("Some funny stuff right here...");
    }
  }

  private FighterThread getOpponentThread(Fighter attacker) {
    if (attacker == redFighter) {
      return blueFighterThread;
    } else if (attacker == blueFighter) {
      return redFighterThread;
    } else {
      throw new InvalidStateException("Some funny stuff right here...");
    }
  }

  private void endFight() {
    redFighter.stopFighting();
    blueFighter.stopFighting();
  }

}
