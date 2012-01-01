#include "ArmorClass.h"

ArmorClass::ArmorClass(double defense){
	defenseRating = defense;
}

int ArmorClass::getMitigatedDamage(Attack* attack){
	return attack->getDamage() - (int) (attack->getDamage() * defenseRating);
}

MagicResistance::MagicResistance(double resist){
	resistanceRating = resist;
}

int MagicResistance::getMitigatedDamage(Attack* attack){
	return attack->getDamage() - (int) (attack->getDamage() * resistanceRating);
}