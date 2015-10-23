package kru.codefight.thread;

import kru.codefight.fighter.Fighter;

public class FightRunner implements Runnable {

  private Fighter fighter;
  private Fighter opponent;

  public FightRunner(Fighter fighter, Fighter opponent) {
    this.fighter = fighter;
    this.opponent = opponent;
  }

  @Override
  public void run() {
    fighter.startFighting(opponent);
  }
}
