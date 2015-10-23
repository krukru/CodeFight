package kru.codefight.strategy;

import sun.plugin.dom.exception.InvalidStateException;

import kru.codefight.events.FighterApi;
import kru.codefight.fighter.Fighter;

public abstract class AbstractFighterStrategy {

  private Fighter fighter = null;

  public final void setFighter(Fighter fighter) {
    if (fighter == null) {
      throw new NullPointerException("Fighter is null");
    }
    if (this.fighter != null) {
      throw new InvalidStateException("Fighter is already set");
    }
    this.fighter = fighter;
  }

  public FighterApi Do() {
    return fighter.Api();
  }

  /**
   * This gets executed in a loop
   */
  public abstract void act();
}
