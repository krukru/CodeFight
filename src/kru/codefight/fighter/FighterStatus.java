package kru.codefight.fighter;

public class FighterStatus {
  private Fighter fighter;

  public Stance getStance() {
    return fighter.getStance();
  }

  public int getHitPoints() {
    return fighter.getHitPoints();
  }

  public void setFighter(Fighter fighter) {
    this.fighter = fighter;
  }

  public boolean isAttacking() {
    return fighter.isAttacking();
  }

  public FighterStatus() {

  }

  public FighterStatus(Fighter fighter) {
    this.fighter = fighter;
  }
}
