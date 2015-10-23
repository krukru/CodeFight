package kru.codefight.logger;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public class SimpleLogger extends AbstractFightLogger {

  @Override
  public void attack(Fighter attacker, Fighter defender, AbstractAttack attack) {
    System.out.println(String.format("%1$s fighter remaining HP: %2$s",
        defender.getColor(), defender.getHitPoints()));
  }

  @Override
  public void stanceChange(Fighter initiator, Stance newStance) {

  }

  @Override
  public void attackInterrupted(Fighter attacker, AbstractAttack attack) {
    System.out.println(String.format("%1$s fighter was interrupted while casting %2$s",
        attacker.getColor(), attack.getClass().getSimpleName()));
  }
}
