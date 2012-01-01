#include "Attack.h"

class ArmorClass{
private:
	double defenseRating;

public:
	ArmorClass(double defense = 0.0);
	int getMitigatedDamage(Attack* attack);
};

class MagicResistance{
private:
	double resistanceRating;

public:
	MagicResistance(double resist = 0.0);
	int getMitigatedDamage(Attack* attack);
};

const ArmorClass NO_ARMOR(0.0);
const ArmorClass LOW_ARMOR(0.2);
const ArmorClass HIGH_ARMOR(0.4);

const MagicResistance NO_RESISTANCE(0.0);
const MagicResistance HIGH_RESISTANCE(0.4);