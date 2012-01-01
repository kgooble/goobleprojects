#include "Defense.h"

Defense::Defense(double defense){
	defenseRating = defense;
}

int Defense::getMitigatedDamage(Attack* attack){
	return attack->getDamage() - (int) (attack->getDamage() * defenseRating);
}
