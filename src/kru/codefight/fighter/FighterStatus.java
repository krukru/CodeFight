package kru.codefight.fighter;

import kru.codefight.fighter.attacks.AbstractAttack;

public class FighterStatus {

  private int hitPoints;
  private boolean isAttacking;
  private AbstractAttack incomingAttack;
  private Stance stance;

  public void loadStatus(Fighter fighter) {
    this.stance = fighter.getStance(); //this possibly is not a deep copy!
    this.hitPoints = fighter.getHitPoints();
    this.isAttacking = fighter.isAttacking();
    this.incomingAttack = fighter.getCastingAttack();
  }

  public Stance getStance() {
    return stance;
  }

  public int getHitPoints() {
    return hitPoints;
  }

  public boolean isAttacking() {
    return isAttacking;
  }

  public FighterStatus() {

  }

  public FighterStatus(Fighter fighter) {
    loadStatus(fighter);
  }
}
