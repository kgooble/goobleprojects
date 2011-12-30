#include <set>
#include <algorithm>
#include "Potion.h"
#include "CharacterClass.h"

enum Item { WIND_PIECE, EARTH_PIECE, FIRE_PIECE, WATER_PIECE };

class Character{
private:
	std::set<Item> items;
	std::set<Item>::iterator iter;
	int maxHP;
	int remainingHP;
	CharacterClass myClass;

public:
	Character();
	Character(CharacterClass charClass);

	int getRemainingHP();

	bool isAbleToAdvance(void);
	bool isDead();

	void addItem(Item item);
	void sustainDamage(Attack att);
	void drinkPotion(Potion pot);

	std::set<Item> getItems();
};

const int MAGE_HP = 100;
const CharacterClass MAGE(NO_ARMOR);