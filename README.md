# CodeFight

[![Build Status](https://travis-ci.org/krukru/CodeFight.svg?branch=master)](https://travis-ci
.org/krukru/CodeFight)

CodeFight is a game between two AI fighters. The players program their strategies and they are compared in a deadly fight to decide the winner!

To get starting, all you need to do is extend the kru.codefight.strategy.AbstractFighterStrategy class and implement the act() method.
The act method is the main strategy for your fighter. The method will get executed in a loop, as
long as the battle is running. You can also customize multiple strategies based on what your
opponent is doing, see Conditional Strategies

All the actions you can perform are encapsulared in the kru.codefight.core.FighterApi, which you access via the protected method Do().

# FighterAPI Actions

In general, there are 4 actions you can perform. You can 
* Attack
* Change stance 
* Recover stamina 
* Scan the opponent.

# Stances

There are 4 stances you can choose from

* Normal - The basic stance
* Blocking - Incoming attacks hit with reduced damage. While behind a block you cannot scan your opponent. Attacking while in block will first switch you to normal stance
* Dodging - Dodges every attack that has the isDodgeable property set to false (most body punches are not dodgeable). Every dodge costs 10 stamina. Dropping below 10 stamina while in dodging stance will automatically switch you to the open stance.
* Open - Your guard is lowered and damage received is double the normal amount. Casting an attack automatically puts you in open stance (after the attack finishes you revert to your old stance)

# Attacks

Every attack extends the kru.codefight.fighter.attacks.AbstractAttack class and has several properties.

* Full damage - The damage when the opponent is in normal stance
* Blocked damage - The damage when the opponent blocked the attack
* Stamina cost - The amount of stamina lost after the attack is cast. The stamina cost is applied after the attack has successfully landed or if the attack was interrupted.
* Cast Time - The time require for the punch to land. Stronger attacks take a bit longer to cast.
* Stun Duration - Some attacks can leave the opponent staggering. While stunned, the opponent cannot reach in any way and his stance is considered to be Open
* Is Dodgeable - Indicates if a attack can be dodged while in Dodging stance. In general, only body punches cannot be dodged.

# Stamina

Every fighter has a maximum od 100 stamina. Each attack costs some amount of stamina. The damage you do will be multiplied by the percent of your stamina. So a fighter with 100 stamina will do 100% of the attack damage, while a fighter with 50 stamina will do only 50% of the attacks damage. It is a good idea to keep your stamina high.

Recovering stamina lasts 1 second and recovers 25 stamina.

# Conditional Strategies

The AbstractFighterStrategy has a virtual method registerConditionalStrategies(). Here you can queue up conditional strategies that will trigger only if the condition is met. For example, you can have a strategy when your opponent is in a blocking stance

# Examples

You can find some examples in the kru.codefight.strategy.examples package. Good luck!
