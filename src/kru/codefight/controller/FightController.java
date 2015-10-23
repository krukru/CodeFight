package kru.codefight.controller;

import sun.plugin.dom.exception.InvalidStateException;

import kru.codefight.events.FightListener;
import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.thread.FightRunner;

public class FightController implements FightListener {

  private Fighter redFighter;
  private Fighter blueFighter;

  private Thread redFighterThread;
  private Thread blueFighterThread;

  private FightResolver fightResolver = new FightResolver();

  public void startFight(Fighter redFighter, Fighter blueFighter) {

    this.redFighter = redFighter;
    this.blueFighter = blueFighter;

    redFighter.subscribeToAttackHappened(this);
    blueFighter.subscribeToAttackHappened(this);

    this.redFighterThread = new Thread(new FightRunner(redFighter, blueFighter));
    this.blueFighterThread = new Thread(new FightRunner(blueFighter, redFighter));

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
  public void attackHappened(Fighter initiator, AbstractAttack attack) {
    Fighter victim = getVictim(initiator);
    fightResolver.resolveAttack(initiator, victim, attack);
    System.out.println("Red hp:" + redFighter.getHitPoints());
    System.out.println("Blue hp:" + blueFighter.getHitPoints());
    if (victim.getHitPoints() <= 0) {
      endFight();
    }
  }

  private void endFight() {
    redFighter.stopFighting();
    blueFighter.stopFighting();
    redFighter.unsubscribeFromAttackHappened();
    blueFighter.unsubscribeFromAttackHappened();
  }

  private Fighter getVictim(Fighter initiator) {
    if (initiator == redFighter) {
      return blueFighter;
    } else if (initiator == blueFighter) {
      return redFighter;
    } else {
      throw new InvalidStateException("What da. Who's been calling my listener?");
    }
  }
}
