#include <set>
#include <algorithm>
#include "Potion.h"
#include "CharacterClass.h"

enum Item { WIND_PIECE, EARTH_PIECE, FIRE_PIECE, WATER_PIECE };

class Character{
private:
	std::set<Item> items;
	int remainingHP;
	CharacterClass myClass;

public:
	Character(CharacterClass charClass = MAGE);

	int getRemainingHP();

	bool isAbleToAdvance(void);
	bool isDead();

	void addItem(Item item);
	void sustainDamage(Attack* att);
	void drinkPotion(Potion pot);

	std::set<Item> getItems();
};