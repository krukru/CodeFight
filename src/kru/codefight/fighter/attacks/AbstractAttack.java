package kru.codefight.fighter.attacks;

public abstract class AbstractAttack {

  AbstractAttack() { }

  public abstract int getFullDamage();
  public abstract int getBlockedDamage();
  public abstract int getStaminaCost();
  public abstract int getCastTimeInMs();
}
