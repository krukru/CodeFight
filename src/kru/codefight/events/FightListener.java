package kru.codefight.events;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.attacks.AbstractAttack;

public interface FightListener {
  void attackHappened(Fighter fighter, AbstractAttack attack);
}
