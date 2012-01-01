#include "ArmorClass.h"

class CharacterClass{
private:
	ArmorClass armorClass;
	MagicResistance magicResistance;
	int maximumHP;

public:
	CharacterClass(ArmorClass armor = NO_ARMOR, MagicResistance resistance = NO_RESISTANCE, int maxHP = 100);
	int getMaximumHP();
	int mitigateDamage(Attack* attack);
};

const int MAGE_HP = 100;
const int ROGUE_HP = 150;
const int WARRIOR_HP = 200;

const CharacterClass MAGE(NO_ARMOR, HIGH_RESISTANCE, MAGE_HP);
const CharacterClass ROGUE(LOW_ARMOR, NO_RESISTANCE, ROGUE_HP);
const CharacterClass WARRIOR(HIGH_ARMOR, NO_RESISTANCE, WARRIOR_HP);