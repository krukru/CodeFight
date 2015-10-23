package kru.codefight.thread;

import kru.codefight.fighter.Fighter;

public class FightRunner implements Runnable {

  private volatile boolean fightActive;
  private Fighter fighter;

  public FightRunner(Fighter fighter) {
    this.fighter = fighter;
  }

  @Override
  public void run() {
    fightActive = true;
    fighter.startFighting();
  }
}
