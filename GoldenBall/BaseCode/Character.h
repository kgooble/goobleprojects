#include <set>
#include <algorithm>
#include "Attack.h"

enum Item { WIND_PIECE, EARTH_PIECE, FIRE_PIECE, WATER_PIECE };
class Potion{
public:
	int getHealAmount(){
		return 25;
	}
};
class Character{
private:
	std::set<Item> items;
	std::set<Item>::iterator iter;
	int maxHP;
	int remainingHP;

public:
	Character();

	int getRemainingHP();

	bool isAbleToAdvance(void);
	bool isDead();

	void addItem(Item item);
	void sustainDamage(Attack att);
	void drinkPotion(Potion pot);

	std::set<Item> getItems();
};