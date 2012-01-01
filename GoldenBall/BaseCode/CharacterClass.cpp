#include "CharacterClass.h"

CharacterClass::CharacterClass(Defense armor, Defense resistance, int maxHP){
	maximumHP = maxHP;
	defenses["physical"] = armor;
	defenses["magic"] = resistance;
}

int CharacterClass::getMaximumHP(){
	return maximumHP;
}

int CharacterClass::mitigateDamage(Attack* attack){
	return defenses[attack->getType()].getMitigatedDamage(attack);
}