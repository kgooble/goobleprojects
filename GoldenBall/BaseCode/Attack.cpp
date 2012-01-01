#include "Attack.h"

Attack::Attack(){
	damage = 50;
}

int Attack::getDamage(){
	return damage;
}

bool Attack::isMagicAttack(){
	return false;
}

bool MagicAttack::isMagicAttack(){
	return true;
}