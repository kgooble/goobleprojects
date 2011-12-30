#include "ArmorClass.h"

ArmorClass::ArmorClass(double defense){
	defenseRating = defense;
}

int ArmorClass::getMitigatedDamage(Attack attack){
	return attack.getDamage() - (int) (attack.getDamage() * defenseRating);
}