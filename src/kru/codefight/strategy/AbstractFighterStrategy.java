package kru.codefight.strategy;

import sun.plugin.dom.exception.InvalidStateException;

import kru.codefight.fighter.Fighter;
import kru.codefight.core.FighterApi;

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

  protected final FighterApi Do() {
    return fighter.Api();
  }

  /**
   * This gets executed in a loop
   */
  public abstract void act();

  public List<ConditionalStrategy> registerConditionalStrategies() {
    //here you can give a set of conditions that will trigger
    //this way, your act() is just the default act()
    //based on what condition triggers, that act it trigger
  }
}
