package kru.codefight.visualizer.swing;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;
import kru.codefight.visualizer.AbstractFightVisualizer;

public class SpriteVisualizer extends AbstractFightVisualizer {



  public SpriteVisualizer() {
    Arena arena = new Arena();
    arena.pack();
    arena.setVisible(true);
  }

  @Override
  public void attackStarted(Fighter attacker, AbstractAttack attack) {

  }

  @Override
  public void attackLanded(Fighter attacker, Fighter defender, AbstractAttack attack) {

  }

  @Override
  public void attackInterrupted(Fighter attacker, AbstractAttack attack) {

  }

  @Override
  public void stanceChanged(Fighter fighter, Stance newStance) {

  }

  @Override
  public void staminaRecovered(Fighter fighter, int oldStamina) {

  }
}
