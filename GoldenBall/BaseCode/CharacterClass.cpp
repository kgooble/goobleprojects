#include "CharacterClass.h"

CharacterClass::CharacterClass(ArmorClass armor, MagicResistance resistance, int maxHP){
	armorClass = armor;
	magicResistance = resistance;
	maximumHP = maxHP;
}

int CharacterClass::getMaximumHP(){
	return maximumHP;
}

int CharacterClass::mitigateDamage(Attack* attack){
	if (attack->isMagicAttack()){
		return magicResistance.getMitigatedDamage(attack);
	}
	return armorClass.getMitigatedDamage(attack);
}