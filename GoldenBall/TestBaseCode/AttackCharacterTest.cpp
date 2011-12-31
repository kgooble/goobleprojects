#include "../BaseCode/Character.h"
#include "gtest/gtest.h"

void attackAndCheckHP(Character* person, int expectedRemainingHealth){
	Attack attack;
	person->sustainDamage(attack);
	EXPECT_EQ(expectedRemainingHealth, person->getRemainingHP());
}

TEST(AttackCharacterTest, MagesTakeMostPhysicalDamageAndHasLeastStartingHP){
	Character person(MAGE);
	EXPECT_EQ(MAGE_HP, person.getRemainingHP());
	attackAndCheckHP(&person, MAGE_HP - 50);
}

TEST(AttackCharacterTest, RoguesTakeReducedDamageAndHaveMediumHP){
	Character person(ROGUE);
	EXPECT_EQ(ROGUE_HP, person.getRemainingHP());
	attackAndCheckHP(&person, ROGUE_HP - 40);
}