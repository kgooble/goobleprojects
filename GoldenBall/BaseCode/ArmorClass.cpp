#include "ArmorClass.h"

ArmorClass::ArmorClass(){
	defenseRating = 0.0;
}
ArmorClass::ArmorClass(double defense=0.0){
	defenseRating = defense;
}

int ArmorClass::getMitigatedDamage(Attack attack){
	return attack.getDamage() - (int) (attack.getDamage() * defenseRating);
}