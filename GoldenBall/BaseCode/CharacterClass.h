#include <string>
#include <map>
#include "Defense.h"

class CharacterClass{
private:
	int maximumHP;
	std::map<std::basic_string<char>, Defense> defenses;

public:
	CharacterClass(Defense armor = NO_RESISTANCE, Defense resistance = NO_RESISTANCE, int maxHP = 100);
	int getMaximumHP();
	int mitigateDamage(Attack* attack);
};

const int MAGE_HP = 100;
const int ROGUE_HP = 150;
const int WARRIOR_HP = 200;

const CharacterClass MAGE(NO_RESISTANCE, HIGH_RESISTANCE, MAGE_HP);
const CharacterClass ROGUE(LOW_RESISTANCE, NO_RESISTANCE, ROGUE_HP);
const CharacterClass WARRIOR(HIGH_RESISTANCE, NO_RESISTANCE, WARRIOR_HP);