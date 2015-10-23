package kru.codefight.thread;

import kru.codefight.fighter.Fighter;

public class FighterThread extends Thread {

  private Fighter fighter;
  private Fighter opponent;

  public FighterThread(Fighter fighter, Fighter opponent) {
    this.fighter = fighter;
    this.opponent = opponent;
  }

  public Fighter getFighter() {
    return fighter;
  }

  @Override
  public void run() {
    fighter.startFighting(opponent);
  }
}
