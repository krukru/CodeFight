package kru.codefight.controller;

import sun.plugin.dom.exception.InvalidStateException;

import kru.codefight.events.FightListener;
import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.thread.FightRunner;

public class FightController implements FightListener {

  private Fighter redFighter;
  private Fighter blueFighter;

  private Thread redFighterThread;
  private Thread blueFighterThread;

  public void startFight(Fighter redFighter, Fighter blueFighter) {

    this.redFighter = redFighter;
    this.blueFighter = blueFighter;

    redFighter.Api().subscribeToAttackHappened(this);
    blueFighter.Api().subscribeToAttackHappened(this);

    this.redFighterThread = new Thread(new FightRunner(redFighter));
    this.blueFighterThread = new Thread(new FightRunner(blueFighter));

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
    int damage = attack.getFullDamage();
    victim.takeDamage(damage);
    System.out.println("Red hp:" + redFighter.getHitPoints());
    System.out.println("Blue hp:" + blueFighter.getHitPoints());
    if (victim.getHitPoints() <= 0) {
      redFighter.stopFighting();
      blueFighter.stopFighting();
      redFighter.Api().unsubscribeFromAttackHappened();
      blueFighter.Api().unsubscribeFromAttackHappened();
    }
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
