package kru.codefight.visualization;

import kru.codefight.fighter.Fighter;
import kru.codefight.fighter.Stance;
import kru.codefight.fighter.attacks.AbstractAttack;

public abstract class AbstractFighterVisualizer {
  abstract public void attack(Fighter attacker, Fighter defender, AbstractAttack attack);
  abstract public void stanceChange(Fighter initiator, Stance newStance);
}
