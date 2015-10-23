package kru.codefight.fighter;

import kru.codefight.core.FighterApi;
import kru.codefight.events.FightListener;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.strategy.AbstractFighterStrategy;
import kru.codefight.strategy.NumnutsStrategy;

public class Fighter {

  private static final int STARTING_HIT_POINTS = 100;
  private static final int STARTING_STAMINA = 100;

  private AbstractFighterStrategy strategy;

  private int stamina;
  private Stance stance;
  private Fighter opponent;
  private FighterApi api;

  private volatile long stunDuration; //in milis
  private volatile boolean fightActive;
  private volatile int hitPoints;
  private volatile boolean isAttacking = false;

  private FightListener listener;

  public FighterApi Api() {
    return api;
  }

  public boolean isAttacking() {return isAttacking;}

  public long getStunDuration() {
    return stunDuration;
  }

  public void resetStun() {
    this.stunDuration = 0;
  }

  public void addStunDuration(long stunDuration) {
    if (stunDuration < 0) {
      throw new IllegalArgumentException();
    }
    this.stunDuration += stunDuration;
  }

  public int getHitPoints() {
    return hitPoints;
  }

  public Stance getStance() {
    return stance;
  }

  public void setStance(Stance stance) {
    this.stance = stance;
  }

  public Fighter getOpponent() {
    return opponent;
  }

  public boolean isKnockedOut() {
    return hitPoints <= 0;
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

  public void attack(AbstractAttack attack) {
    this.isAttacking = true;
    if (listener == null) {
      throw new NullPointerException("Attack happened, but no listener was set!");
    }
    int castTime = attack.getCastTimeInMs();
    try {
      Thread.sleep(castTime);
    } catch (InterruptedException e) {
      //Attack was interrupted
      this.isAttacking = false;
      return;
    }
    listener.attackHappened(this, attack);
    this.isAttacking = false;
  }

  private void initFighterStats(int startingHitPoints, int startingStamina) {
    this.hitPoints = startingHitPoints;
    this.stamina = startingStamina;
    this.stance = Stance.NORMAL;
  }

  public void startFighting(Fighter opponent) {
    this.opponent = opponent;
    this.fightActive = true;
    while (fightActive) {
      strategy.act();
    }
  }

  public void stopFighting() {
    this.fightActive = false;
  }

  public void takeDamage(int damage) {
    if (damage < 0) {
      throw new IllegalArgumentException("Negative damage");
    }
    this.hitPoints -= damage;
  }

  public boolean canSeeOpponent() {
    return stance != Stance.BLOCKING;
  }

  public void subscribeToAttackHappened(FightListener listener) {
    if (listener == null) {
      throw new NullPointerException("If you want to unsubscribe, there's a method for that.");
    }
    this.listener = listener;
  }

  public void unsubscribeFromAttackHappened() {
    this.listener = null;
  }
}
