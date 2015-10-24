package kru.codefight.logger;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.FighterColor;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public class ConsoleVisualizer extends AbstractFightVisualizer {

  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_BLACK = "\u001B[30m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_YELLOW = "\u001B[33m";
  private static final String ANSI_BLUE = "\u001B[34m";
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_CYAN = "\u001B[36m";
  private static final String ANSI_WHITE = "\u001B[37m";

  @Override
  public void attackStarted(Fighter attacker, AbstractAttack attack) {
    printlnInColor(attacker.getColor(),String.format("%1$s fighter started casting a %2$s.",
        attacker.getColor(), attack.getClass().getSimpleName()) );
  }

  @Override
  public void attackLanded(Fighter attacker, Fighter defender, AbstractAttack attack) {
    printlnInColor(attacker.getColor(),
        String.format("%1$s fighter landed a %2$s with %3$s%% strength. Opponent had stance %4$s.",
        attacker.getColor(), attack.getClass().getSimpleName(),
        100 * attacker.getAttackIntensityFactor(), defender.getStance()));
    printlnInColor(defender.getColor(), String.format("%1$s fighter remaining HP: %2$s.",
        defender.getColor(), defender.getHitPoints()));
  }

  @Override
  public void attackInterrupted(Fighter attacker, AbstractAttack attack) {
    printlnInColor(attacker.getColor(), String.format("%1$s fighter was interrupted while casting %2$s",
        attacker.getColor(), attack.getClass().getSimpleName()));
  }

  @Override
  public void stanceChanged(Fighter fighter, Stance newStance) {
    printlnInColor(fighter.getColor(), String.format("%1$s fighter changed stance to %2$s",
        fighter.getColor(), newStance));
  }

  @Override
  public void staminaRecovered(Fighter fighter, int oldStamina) {
    printlnInColor(fighter.getColor(), String.format("%1$s fighter recovered stamina: %2$s -> %3$s",
        fighter.getColor(), oldStamina, fighter.getStamina()));
  }

  private void printlnInColor(FighterColor color, String output) {
    String consoleColor;
    if (color == FighterColor.RED) {
      consoleColor = ANSI_RED;
    } else {
      consoleColor = ANSI_BLUE;
    }
    System.out.println(consoleColor + output + ANSI_RESET);
  }
}
