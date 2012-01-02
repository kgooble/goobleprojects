#include "Attack.h"

class Defense{
private:
	double defenseRating;

public:
	Defense(double defense = 0.0);
	int getMitigatedDamage(Attack* attack);
};

const double NO_RESISTANCE(0.0);
const double LOW_RESISTANCE(0.2);
const double HIGH_RESISTANCE(0.4);