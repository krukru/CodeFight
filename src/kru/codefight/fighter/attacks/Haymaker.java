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
    return 75;
  }

  @Override
  public int getCastTimeInMs() {
    return 2500;
  }

  @Override
  public int getStunDurationInMs() {
    return 2000;
  }

  @Override
  public boolean isDodgeable() {
    return true;
  }
}
