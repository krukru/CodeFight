package kru.codefight.fighter;

import kru.codefight.core.FighterApi;
import kru.codefight.events.FightListener;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.logger.Logger;
import kru.codefight.strategy.AbstractFighterStrategy;
import kru.codefight.strategy.examples.NumnutsStrategy;

public class Fighter {
  private static final int MAX_HIT_POINTS = 100;
  private static final int MAX_STAMINA = 100;

  private AbstractFighterStrategy strategy;

  private int stamina;
  private Stance stance;
  private Fighter opponent;
  private FighterApi api;

  private FighterColor fighterColor;

  private volatile long stunDuration; //in milis //possibly only volatile
  private volatile boolean fightActive;
  private volatile int hitPoints;
  private volatile boolean isAttacking = false;

  private FightListener listener;

  public FighterApi Api() {
    return api;
  }

  public boolean isAttacking() {
    return isAttacking;
  }

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

  public FighterColor getColor() {
    return fighterColor;
  }

  public int getHitPoints() {
    return hitPoints;
  }

  public Stance getStance() {
    return stance;
  }

  public void setStance(Stance stance) {
    this.stance = stance;
    Logger.instance().stanceChange(this, stance);
  }

  public double getAttackIntensityFactor() {
    return Math.max(0.1, (double)stamina / MAX_STAMINA); //@TODO: neki eksponencijalni pad možda?
  }

  public void recoverStamina() {
    this.stance = Stance.NORMAL;
    this.stamina = Math.min(MAX_STAMINA, stamina + 25);
    Logger.instance().recoverStamina(this, 25);
  }

  public Fighter getOpponent() {
    return opponent;
  }

  public boolean isKnockedOut() {
    return hitPoints <= 0;
  }

  public int getStamina() {
    return stamina;
  }

  public Fighter(AbstractFighterStrategy strategy, FighterColor fighterColor) {
    this.api = new FighterApi(this);
    initFighterStats(MAX_HIT_POINTS, MAX_STAMINA);
    if (strategy == null) {
      this.strategy = new NumnutsStrategy();
    } else {
      this.strategy = strategy;
    }
    this.strategy.setFighter(this);
    this.fighterColor = fighterColor;
  }

  public void attack(AbstractAttack attack) {
    this.isAttacking = true;
    if (listener == null) {
      throw new NullPointerException("Attack happened, but no listener was set!");
    }
    int castTime = attack.getCastTimeInMs();
    try {
      Thread.sleep(castTime);
      listener.attackHappened(this, attack);
    } catch (InterruptedException e) {
      Logger.instance().attackInterrupted(this, attack);
    } finally {
      this.stamina = Math.max(0, stamina - attack.getStaminaCost());
      this.stance = Stance.NORMAL;
      this.isAttacking = false;
    }
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
