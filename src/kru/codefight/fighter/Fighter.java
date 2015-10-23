package kru.codefight.fighter;

import kru.codefight.events.FighterApi;
import kru.codefight.strategy.AbstractFighterStrategy;
import kru.codefight.strategy.NumnutsStrategy;

public class Fighter {

  private static final int STARTING_HIT_POINTS = 100;
  private static final int STARTING_STAMINA = 100;

  private AbstractFighterStrategy strategy;
  private int hitPoints;
  private int stamina;
  private Stance stance;
  private FighterApi api;

  private volatile boolean fightActive;

  public FighterApi Api() {
    return api;
  }

  public int getHitPoints() {
    return hitPoints;
  }

  public Fighter(AbstractFighterStrategy strategy) {
    this.api = new FighterApi(this);
    initFighterStats(STARTING_HIT_POINTS, STARTING_STAMINA);
    if (strategy == null) {
      this.strategy = new NumnutsStrategy();
    } else {
      this.strategy = strategy;
    }
    this.strategy.setFighter(this);
  }

  private void initFighterStats(int startingHitPoints, int startingStamina) {
    this.hitPoints = startingHitPoints;
    this.stamina = startingStamina;
    this.stance = Stance.OPEN;
  }

  public void startFighting() {
    fightActive = true;
    while (fightActive) {
      strategy.act();
    }
  }

  public void stopFighting() {
    fightActive = false;
  }

  public void takeDamage(int damage) {
    this.hitPoints -= damage;
  }

}
