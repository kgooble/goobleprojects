class Attack{
private:
	int damage;

public:
	Attack();
	int getDamage();
	virtual bool isMagicAttack();
};

class MagicAttack: public Attack{
public:
	bool isMagicAttack();
};