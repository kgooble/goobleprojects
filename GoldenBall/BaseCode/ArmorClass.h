#include "Attack.h"

class ArmorClass{
private:
	double defenseRating;

public:
	ArmorClass();
	ArmorClass(double defense);
	int getMitigatedDamage(Attack attack);
};

const ArmorClass NO_ARMOR(0.0);
const ArmorClass LOW_ARMOR(0.2);
const ArmorClass HIGH_ARMOR(0.4);