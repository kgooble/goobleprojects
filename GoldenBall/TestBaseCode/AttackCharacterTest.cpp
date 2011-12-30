#include "../BaseCode/Character.h"
#include "gtest/gtest.h"

TEST(AttackCharacterTest, MagesTakeMostPhysicalDamageAndHasLeastStartingHP)
{
	Character person(MAGE);
	EXPECT_EQ(MAGE_HP, person.getRemainingHP());
	Attack attack;
	person.sustainDamage(attack);
	EXPECT_EQ(MAGE_HP - 50, person.getRemainingHP());
}