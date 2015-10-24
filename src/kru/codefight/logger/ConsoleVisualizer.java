package kru.codefight.logger;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public class ConsoleVisualizer extends AbstractFightVisualizer {


  @Override
  public void attackStarted(Fighter attacker, AbstractAttack attack) {
    System.out.println(String.format("%1$s fighter started casting a %2$s.",
        attacker.getColor(), attack.getClass().getSimpleName()));
  }

  @Override
  public void attackLanded(Fighter attacker, Fighter defender, AbstractAttack attack) {
    System.out.println(String.format("%1$s fighter landed a %2$s with %3$s% strength. Opponent " +
            "had stance %4$s.",
        attacker.getColor(), attack.getClass().getSimpleName(),
        attacker.getAttackIntensityFactor(), defender.getStamina()));
    System.out.println(String.format("%1$s fighter remaining HP: %2$s.",
        defender.getColor(), defender.getHitPoints()));
  }

  @Override
  public void attackInterrupted(Fighter attacker, AbstractAttack attack) {
    System.out.println(String.format("%1$s fighter was interrupted while casting %2$s",
        attacker.getColor(), attack.getClass().getSimpleName()));
  }

  @Override
  public void stanceChanged(Fighter fighter, Stance newStance) {
    System.out.println(String.format("%1$s fighter changed stance to %2$s",
        fighter.getColor(), newStance));
  }

  @Override
  public void staminaRecovered(Fighter fighter, int oldStamina) {
    System.out.println(String.format("%1$s fighter recovered stamina: %2$s -> %3$s",
        fighter.getColor(), oldStamina, fighter.getStamina()));
  }
}
