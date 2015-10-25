package kru.codefight.strategy;

import kru.codefight.fighter.Fighter;
import kru.codefight.core.FighterApi;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFighterStrategy {

  private List<ConditionalStrategy> strategyList;
  private Fighter fighter = null;

  public final void setFighter(Fighter fighter) {
    if (fighter == null) {
      throw new NullPointerException("Fighter is null");
    }
    if (this.fighter != null) {
      throw new IllegalStateException("Fighter is already set");
    }
    this.fighter = fighter;
  }

  public final List<ConditionalStrategy> getStrategyList() {
    if (strategyList == null) {
      strategyList = registerConditionalStrategies();
    }
    return strategyList;
  }

  protected final FighterApi Do() {
    return fighter.Api();
  }

  public AbstractFighterStrategy() {
    this.strategyList = registerConditionalStrategies();
  }

  protected List<ConditionalStrategy> registerConditionalStrategies() {
    //here you can give a set of conditions that will trigger
    //this way, your act() is just the default act()
    //based on what condition triggers, that act it trigger
    return new ArrayList<>();
  }

  /**
   * This gets executed in a loop
   */
  public abstract void act();
}
