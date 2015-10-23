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

  public FighterStatus() {

  }

  public FighterStatus(Fighter fighter) {
    this.fighter = fighter;
  }
}
