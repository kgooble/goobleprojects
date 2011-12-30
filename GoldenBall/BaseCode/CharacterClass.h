#include "ArmorClass.h"

class CharacterClass{
private:
	ArmorClass armorClass;

public:
	CharacterClass(ArmorClass armor = NO_ARMOR);
};

const int MAGE_HP = 100;
const CharacterClass MAGE(NO_ARMOR);