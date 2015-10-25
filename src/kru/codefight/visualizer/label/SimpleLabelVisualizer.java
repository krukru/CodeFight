package kru.codefight.visualizer.label;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.FighterColor;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.visualizer.AbstractFightVisualizer;

public class SimpleLabelVisualizer extends AbstractFightVisualizer {

  private Arena arena;

  public SimpleLabelVisualizer() {
    this.arena = new Arena();
    arena.pack();
    arena.setLocationRelativeTo(null);
    arena.setVisible(true);
  }

  @Override
  public void attackStarted(Fighter attacker, AbstractAttack attack) {
    String text1 = String.format("Casting %1$s", attack.getClass().getSimpleName());
    String text2 = String.format("Stamina: %1$s", attacker.getStamina());
    if (attacker.getColor() == FighterColor.RED) {
      arena.getRedCasting().setText(text1);
      arena.getRedStamina().setText(text2);
    } else {
      arena.getBlueCasting().setText(text1);
      arena.getBlueStamina().setText(text2);
    }
  }

  @Override
  public void attackLanded(Fighter attacker, Fighter defender, AbstractAttack attack) {
    String text = String.format("HP: %1$s", defender.getHitPoints());
    if (defender.getColor() == FighterColor.RED) {
      arena.getRedHp().setText(text);
    } else {
      arena.getBlueHp().setText(text);
    }
  }

  @Override
  public void attackInterrupted(Fighter attacker, AbstractAttack attack) {
    String text = "Attack interrupted!";
    if (attacker.getColor() == FighterColor.RED) {
      arena.getRedCasting().setText(text);
    } else {
      arena.getBlueCasting().setText(text);
    }
  }

  @Override
  public void stanceChanged(Fighter fighter, Stance newStance) {
    String text = String.format("%1$s", fighter.getStance());
    if (fighter.getColor() == FighterColor.RED) {
      arena.getRedStance().setText(text);
    } else {
      arena.getBlueStance().setText(text);
    }
  }

  @Override
  public void staminaRecovered(Fighter fighter, int oldStamina) {
    String text1 = "Recovering stamina";
    String text2 = String.format("Stamina: %1$s", fighter.getStamina());
    if (fighter.getColor() == FighterColor.RED) {
      arena.getRedCasting().setText(text1);
      arena.getRedStamina().setText(text2);
    } else {
      arena.getBlueCasting().setText(text1);
      arena.getBlueStamina().setText(text2);
    }
  }
}
