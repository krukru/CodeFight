package kru.codefight.fighter.attacks;

public class Jab extends AbstractAttack {
  @Override
  public int getFullDamage() {
    return 5;
  }

  @Override
  public int getBlockedDamage() {
    return 0;
  }

  @Override
  public int getStaminaCost() {
    return 0;
  }

  @Override
  public double getCastTime() {
    return 0.5;
  }
}
