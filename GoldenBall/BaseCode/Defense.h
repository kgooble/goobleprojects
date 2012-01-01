#include "Attack.h"

class Defense{
private:
	double defenseRating;

public:
	Defense(double defense = 0.0);
	int getMitigatedDamage(Attack* attack);
};

const Defense NO_RESISTANCE(0.0);
const Defense LOW_RESISTANCE(0.2);
const Defense HIGH_RESISTANCE(0.4);