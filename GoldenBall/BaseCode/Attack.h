#include <string>

class Attack{
private:
	int damage;

public:
	Attack();
	int getDamage();
	virtual std::basic_string<char> getType();
};

class MagicAttack: public Attack{
public:
	std::basic_string<char> getType();
};