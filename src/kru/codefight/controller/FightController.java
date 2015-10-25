package kru.codefight.controller;

import kru.codefight.core.FightOutcome;
import kru.codefight.events.FightListener;
import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.thread.FighterThread;
import kru.codefight.visualizer.AbstractFightVisualizer;
import kru.codefight.visualizer.Visualizer;

public class FightController implements FightListener {

  private Fighter redFighter;
  private Fighter blueFighter;

  private FighterThread redFighterThread;
  private FighterThread blueFighterThread;

  private FightResolver fightResolver = new FightResolver();

  private AbstractFightVisualizer visualizer = Visualizer.instance();

  public FightOutcome resolveFight(Fighter redFighter, Fighter blueFighter) {

    this.redFighter = redFighter;
    this.blueFighter = blueFighter;

    redFighter.subscribeToAttackHappened(this);
    blueFighter.subscribeToAttackHappened(this);

    this.redFighterThread = new FighterThread(redFighter, blueFighter);
    this.blueFighterThread = new FighterThread(blueFighter, redFighter);

    redFighterThread.start();
    blueFighterThread.start();

    try {
      redFighterThread.join();
      blueFighterThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return fightResolver.determineOutcome(redFighter, blueFighter);
  }

  @Override
  public void attackStarted(Fighter attacker, AbstractAttack attack) {
    visualizer.attackStarted(attacker, attack);
  }

  @Override
  public void attackLanded(Fighter attacker, Fighter defender, AbstractAttack attack) {
    FighterThread attackerThread = getFighterThread(attacker);
    FighterThread defenderThread = getFighterThread(defender);
    fightResolver.resolveAttack(attackerThread, defenderThread, attack);
    visualizer.attackLanded(attacker, defender, attack);
    if (defenderThread.getFighter().isKnockedOut()) {
      endFight();
    }
  }

  @Override
  public void attackInterrupted(Fighter attacker, AbstractAttack attack) {
    visualizer.attackInterrupted(attacker, attack);
  }

  @Override
  public void stanceChanged(Fighter fighter, Stance newStance) {
    visualizer.stanceChanged(fighter, newStance);
  }

  @Override
  public void staminaRecovered(Fighter fighter, int oldStamina) {
    visualizer.staminaRecovered(fighter, oldStamina);
  }

  private FighterThread getFighterThread(Fighter attacker) {
    if (attacker == redFighter) {
      return redFighterThread;
    } else if (attacker == blueFighter) {
      return blueFighterThread;
    } else {
      throw new IllegalStateException("Some funny stuff right there...");
    }
  }

  private void endFight() {
    redFighter.stopFighting();
    blueFighter.stopFighting();
  }

}
