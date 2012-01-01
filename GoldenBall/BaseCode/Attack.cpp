#include "Attack.h"

Attack::Attack(){
	damage = 50;
}

int Attack::getDamage(){
	return damage;
}

std::basic_string<char> Attack::getType(){
	return "physical";
}

std::basic_string<char> MagicAttack::getType(){
	return "magic";
}