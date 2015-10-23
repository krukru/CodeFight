package kru.codefight.visualization;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public class ConsoleVisualizer extends AbstractFightVisualizer {

  @Override
  public void attack(Fighter attacker, Fighter defender, AbstractAttack attack) {
    System.out.println(String.format("%1$s fighter attacked with a %2$s",
        attacker.getColor(), attack.getClass().getSimpleName()));
  }

  @Override
  public void stanceChange(Fighter initiator, Stance newStance) {
    System.out.println(String.format("%1$s fighter changed stance to %2$s",
        initiator.getColor(), newStance));
  }
}
