#include "Character.h"

Character::Character(){
	maxHP = 100;
	remainingHP = maxHP;
}

int Character::getRemainingHP(){
	return remainingHP;
}

bool Character::isAbleToAdvance(void){
	return items.size() == 4;
}

bool Character::isDead(){
	return remainingHP == 0;
}

void Character::addItem(Item item){
	iter = items.begin();
	items.insert(iter, item);
}

void Character::sustainDamage(Attack attack){
	remainingHP -= attack.getDamage();
	if (remainingHP < 0){
		remainingHP = 0;
	}
}

void Character::drinkPotion(Potion pot){
	remainingHP += pot.getHealAmount();
	if (remainingHP > maxHP){
		remainingHP = maxHP;
	}
}

std::set<Item> Character::getItems(){
	return items;
}

