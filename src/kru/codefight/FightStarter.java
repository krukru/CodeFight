package kru.codefight;

import kru.codefight.fighter.Fighter;
import kru.codefight.strategy.AbstractFighterStrategy;
import kru.codefight.strategy.NewbieStrategy;
import kru.codefight.strategy.NumnutsStrategy;

public class FightStarter {
  public static void main(String[] args) {
    AbstractFighterStrategy redStrategy = new NewbieStrategy();
    AbstractFighterStrategy blueStrategy = new NumnutsStrategy();

    Fighter redFighter = new Fighter(redStrategy);
    Fighter blueFighter = new Fighter(blueStrategy);

    FightController fightClub = new FightController();
    fightClub.startFight(redFighter, blueFighter);
  }
}
