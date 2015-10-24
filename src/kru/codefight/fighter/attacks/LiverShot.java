package kru.codefight.fighter.attacks;

public class LiverShot extends AbstractAttack {
  @Override
  public int getFullDamage() {
    return 15;
  }

  @Override
  public int getBlockedDamage() {
    return 10;
  }

  @Override
  public int getStaminaCost() {
    return 30;
  }

  @Override
  public int getCastTimeInMs() {
    return 700;
  }

  @Override
  public int getStunDurationInMs() {
    return 0;
  }

  @Override
  public boolean isDodgeable() {
    return false;
  }
}
