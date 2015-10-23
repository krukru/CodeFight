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
    return 10;
  }

  @Override
  public int getCastTimeInMs() {
    return 500;
  }

  @Override
  public int getStunDurationInMs() {
    return 0;
  }
}
