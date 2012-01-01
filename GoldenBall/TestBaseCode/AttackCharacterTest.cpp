#include "../BaseCode/Character.h"
#include "gtest/gtest.h"

void attackAndCheckHP(Character* person, int expectedRemainingHealth){
	Attack attack;
	person->sustainDamage(&attack);
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

TEST(AttackCharacterTest, WarriorsTakeEvenMoreReducedDamageAndHaveHighHP){
	Character person(WARRIOR);
	EXPECT_EQ(WARRIOR_HP, person.getRemainingHP());
	attackAndCheckHP(&person, WARRIOR_HP - 30);
}

TEST(AttackCharacterTest, WarriorsAndRoguesTakeFullDamageFromMagicSpells){
	Character warrior(WARRIOR);
	Character rogue(ROGUE);

	MagicAttack spell;

	warrior.sustainDamage(&spell);
	rogue.sustainDamage(&spell);

	EXPECT_EQ(WARRIOR_HP - 50, warrior.getRemainingHP());
	EXPECT_EQ(ROGUE_HP - 50, rogue.getRemainingHP());
}