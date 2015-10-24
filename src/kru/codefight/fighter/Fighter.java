package kru.codefight.fighter;

import kru.codefight.core.FighterApi;
import kru.codefight.events.FightListener;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.logger.AbstractFightVisualizer;
import kru.codefight.logger.Visualizer;
import kru.codefight.strategy.AbstractFighterStrategy;
import kru.codefight.strategy.ConditionalStrategy;
import kru.codefight.strategy.examples.NumnutsStrategy;

public class Fighter {
  private static final int MAX_HIT_POINTS = 100;
  private static final int MAX_STAMINA = 100;
  public static final int STAMINA_RECOVERY_AMOUNT = 25;
  public static final double MINIMAL_ATTACK_INTENSITY_FACTOR = 0.1;

  private AbstractFighterStrategy strategy;

  private AbstractAttack castingAttack;

  private int stamina;
  private Stance stance;
  private Fighter opponent;
  private FighterApi api;

  private FighterColor fighterColor;

  private volatile long stunDuration; //in milis
  private volatile int hitPoints;
  private boolean fightActive;

  private FightListener listener;
  private AbstractFightVisualizer visualizer = Visualizer.instance();

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

  public FighterApi Api() {
    return api;
  }

  public boolean isAttacking() {
    return castingAttack != null;
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

  public void decreaseStunDuration(long stunDuration) {
    if (stunDuration < 0) {
      throw new IllegalArgumentException();
    }
    this.stunDuration -= stunDuration;
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
    visualizer.stanceChanged(this, stance);
  }

  public double getAttackIntensityFactor() {
    return Math.max(MINIMAL_ATTACK_INTENSITY_FACTOR, (double) stamina / MAX_STAMINA);
  }

  public void recoverStamina() {
    int oldStamina = stamina;
    this.stamina = Math.min(MAX_STAMINA, stamina + STAMINA_RECOVERY_AMOUNT);
    visualizer.staminaRecovered(this, oldStamina);
  }

  public Fighter getOpponent() {
    return opponent;
  }

  public AbstractAttack getCastingAttack() {
    return castingAttack;
  }

  public boolean isKnockedOut() {
    return hitPoints <= 0;
  }

  public int getStamina() {
    return stamina;
  }

  public void attack(AbstractAttack attack) {
    if (listener == null) {
      throw new NullPointerException("Attack happened, but no listener was set!");
    }
    this.castingAttack = attack;
    Stance oldStance = stance;
    setStance(Stance.OPEN);
    visualizer.attackStarted(this, attack);
    int castTime = attack.getCastTimeInMs();
    try {
      Thread.sleep(castTime);
      listener.attackHappened(this, attack);
      visualizer.attackLanded(this, opponent, attack);
    } catch (InterruptedException e) {
      visualizer.attackInterrupted(this, attack);
    } finally {
      this.stamina = Math.max(0, stamina - attack.getStaminaCost());
      setStance(oldStance);
      this.castingAttack = null;
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
      if (canSeeOpponent()) {
        FighterStatus opponentStatus = new FighterStatus(opponent); //razmisli da fighter ima
        // metodu FighterStatus getStatus()
        boolean strategyFound = false;
        for (ConditionalStrategy cs : strategy.getStrategyList()) {
          if (cs.predicateSatisfied(opponentStatus)) {
            cs.act();
            strategyFound = true;
            break;
          }
        }
        if (strategyFound == false) {
          strategy.act(); //no conditions satisfied, do main act
        }
      } else {
        strategy.act();
      }
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
