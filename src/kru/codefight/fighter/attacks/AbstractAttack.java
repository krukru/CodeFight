package kru.codefight.fighter.attacks;

public abstract class AbstractAttack {

  AbstractAttack() { }

  public abstract int getFullDamage();
  public abstract int getBlockedDamage();
  public abstract int getStaminaCost();
  public abstract int getCastTimeInMs();
  public abstract int getStunDurationInMs();

  //@TODO isDodgeable (body punches shouldn't be dodgeable)
  //and dodge should work with a % depending on your endurance
}
