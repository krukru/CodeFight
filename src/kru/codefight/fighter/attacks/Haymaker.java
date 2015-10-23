package kru.codefight.fighter.attacks;

public class Haymaker extends AbstractAttack {
  @Override
  public int getFullDamage() {
    return 50;
  }

  @Override
  public int getBlockedDamage() {
    return 10;
  }

  @Override
  public int getStaminaCost() {
    return 0;
  }

  @Override
  public double getCastTime() {
    return 2.5;
  }
}
