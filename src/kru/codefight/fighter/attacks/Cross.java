package kru.codefight.fighter.attacks;

public class Cross extends AbstractAttack {
  @Override
  public int getFullDamage() {
    return 20;
  }

  @Override
  public int getBlockedDamage() {
    return 5;
  }

  @Override
  public int getStaminaCost() {
    return 0;
  }

  @Override
  public int getCastTimeInMs() {
    return 1000;
  }
}
