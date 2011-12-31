#include "CharacterClass.h"

CharacterClass::CharacterClass(ArmorClass armor, int maxHP){
	armorClass = armor;
	maximumHP = maxHP;
}

int CharacterClass::getMaximumHP(){
	return maximumHP;
}

int CharacterClass::mitigateDamage(Attack attack){
	return armorClass.getMitigatedDamage(attack);
}