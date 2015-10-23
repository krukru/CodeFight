package kru.codefight.logger;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public class DetailedLogger extends AbstractFightLogger {

  @Override
  public void attack(Fighter attacker, Fighter defender, AbstractAttack attack, int damage) {
    System.out.println(String.format("%1$s fighter attacked with a %2$s (%3$s)",
        attacker.getColor(), attack.getClass().getSimpleName(), defender.getStance()));
    System.out.println(String.format("%1$s fighter remaining HP: %2$s",
        defender.getColor(), defender.getHitPoints()));
  }

  @Override
  public void stanceChange(Fighter initiator, Stance newStance) {
    System.out.println(String.format("%1$s fighter changed stance to %2$s",
        initiator.getColor(), newStance));
  }

  @Override
  public void attackInterrupted(Fighter attacker, AbstractAttack attack) {
    System.out.println(String.format("%1$s fighter was interrupted while casting %2$s",
        attacker.getColor(), attack.getClass().getSimpleName()));
  }

  @Override
  public void recoverStamina(Fighter initiator, int recoverAmount) {
    System.out.println(String.format("%1$s fighter is recovering stamina: %2$s -> %3$s",
        initiator.getColor(), initiator.getStamina() - recoverAmount, initiator.getStamina()));
  }
}
