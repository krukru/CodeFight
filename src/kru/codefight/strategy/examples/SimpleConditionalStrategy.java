package kru.codefight.strategy.examples;

import kru.codefight.fighter.FighterStatus;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.Attacks;
import kru.codefight.strategy.AbstractFighterStrategy;
import kru.codefight.strategy.ConditionalStrategy;

import java.util.ArrayList;

public class SimpleConditionalStrategy extends AbstractFighterStrategy {

  @Override
  protected ArrayList<ConditionalStrategy> registerConditionalStrategies() {
    ArrayList<ConditionalStrategy> result = new ArrayList<>();

    ConditionalStrategy blockAttack = new ConditionalStrategy(FighterStatus::isAttacking, 1,
        this::actBlockAttack);

    result.add(blockAttack);
    return result;
  }

  @Override
  public void act() {
    Do().attack(Attacks.Jab);
    Do().attack(Attacks.Jab);
    Do().attack(Attacks.Cross);
    Do().recoverStaminaUpTo(80);
  }

  public void actBlockAttack() {
    Do().changeStance(Stance.BLOCKING);
    Do().recoverStamina();
  }
}
