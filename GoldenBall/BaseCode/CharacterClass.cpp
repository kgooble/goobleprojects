#include "CharacterClass.h"

CharacterClass::CharacterClass(double armor, double resistance, int maxHP){
	maximumHP = maxHP;
	defenses["physical"] = armor;
	defenses["magic"] = resistance;
}

int CharacterClass::getMaximumHP(){
	return maximumHP;
}

int CharacterClass::mitigateDamage(Attack* attack){
	Defense defense(defenses[attack->getType()]);
	return defense.getMitigatedDamage(attack);
}