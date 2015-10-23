package kru.codefight.controller;

import sun.plugin.dom.exception.InvalidStateException;

import kru.codefight.events.FightListener;
import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.thread.FightRunner;

public class FightController implements FightListener {

  private Fighter redFighter;
  private Fighter blueFighter;

  private FightResolver fightResolver = new FightResolver();

  public void startFight(Fighter redFighter, Fighter blueFighter) {

    this.redFighter = redFighter;
    this.blueFighter = blueFighter;

    redFighter.subscribeToAttackHappened(this);
    blueFighter.subscribeToAttackHappened(this);

    Thread redFighterThread = new Thread(new FightRunner(redFighter, blueFighter));
    Thread blueFighterThread = new Thread(new FightRunner(blueFighter, redFighter));

    redFighterThread.start();
    blueFighterThread.start();

    try {
      redFighterThread.join();
      blueFighterThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void attackHappened(Fighter attacker, AbstractAttack attack) {
    Fighter defender = getVictim(attacker);
    fightResolver.resolveAttack(attacker, defender, attack);
    System.out.println("Red hp:" + redFighter.getHitPoints());
    System.out.println("Blue hp:" + blueFighter.getHitPoints());
    if (defender.getHitPoints() <= 0) {
      endFight();
    }
  }

  private void endFight() {
    redFighter.stopFighting();
    blueFighter.stopFighting();
    redFighter.unsubscribeFromAttackHappened();
    blueFighter.unsubscribeFromAttackHappened();
  }

  private Fighter getVictim(Fighter attacker) {
    if (attacker == redFighter) {
      return blueFighter;
    } else if (attacker == blueFighter) {
      return redFighter;
    } else {
      throw new InvalidStateException("Some funny stuff right here...");
    }
  }

}
