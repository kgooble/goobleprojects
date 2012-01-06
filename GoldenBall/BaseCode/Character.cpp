#include "Character.h"

Character::Character(CharacterClass charClass){
	myClass = charClass;
	remainingHP = charClass.getMaximumHP();
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
	items.insert(items.begin(), item);
}

void Character::sustainDamage(Attack* attack){
	int damageDone = myClass.mitigateDamage(attack);
	remainingHP -= damageDone;
	if (remainingHP < 0){
		remainingHP = 0;
	}
}

void Character::drinkPotion(Potion pot){
	remainingHP += pot.getHealAmount();
	if (remainingHP > myClass.getMaximumHP()){
		remainingHP = myClass.getMaximumHP();
	}
}

std::set<Item> Character::getItems(){
	return items;
}

