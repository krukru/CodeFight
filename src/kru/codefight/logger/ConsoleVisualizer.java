package kru.codefight.logger;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public class ConsoleVisualizer extends AbstractFightVisualizer {


  @Override
  public void attackStarted(Fighter attacker, AbstractAttack attack) {
    System.out.println(String.format("%1$s fighter attacked with a %2$s (%3$s)",
        attacker.getColor(), attack.getClass().getSimpleName(), attacker.getStance()));
    System.out.println(String.format("%1$s fighter remaining HP: %2$s",
        attacker.getColor(), attacker.getHitPoints()));
  }

  @Override
  public void attackLanded(Fighter attacker, AbstractAttack attack) {

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

  }
}
