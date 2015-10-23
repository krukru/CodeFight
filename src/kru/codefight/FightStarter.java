package kru.codefight;

import kru.codefight.controller.FightController;
import kru.codefight.fighter.Fighter;
import kru.codefight.strategy.AbstractFighterStrategy;
import kru.codefight.strategy.NewbieStrategy;
import kru.codefight.strategy.NumnutsStrategy;

public class FightStarter {

  private static final AbstractFighterStrategy redStrategy = new NewbieStrategy();
  private static final AbstractFighterStrategy blueStrategy = new NumnutsStrategy();

  public static void main(String[] args) {
    Fighter redFighter = new Fighter(redStrategy);
    Fighter blueFighter = new Fighter(blueStrategy);

    FightController fightClub = new FightController();
    System.out.println("Fight started!");
    fightClub.startFight(redFighter, blueFighter);
    System.out.println("Fight ended!");
  }
}
