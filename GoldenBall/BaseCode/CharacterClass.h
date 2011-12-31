#include "ArmorClass.h"

class CharacterClass{
private:
	ArmorClass armorClass;
	int maximumHP;

public:
	CharacterClass(ArmorClass armor = NO_ARMOR, int maxHP = 100);
	int getMaximumHP();
	int mitigateDamage(Attack attack);
};

const int MAGE_HP = 100;
const int ROGUE_HP = 150;

const CharacterClass MAGE(NO_ARMOR, MAGE_HP);
const CharacterClass ROGUE(LOW_ARMOR, ROGUE_HP);