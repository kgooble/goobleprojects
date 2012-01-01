#include "../BaseCode/Character.h"
#include "gtest/gtest.h"

void attackPersonAndExpectDeadWithZeroHealth(Attack attack, Character person){
	person.sustainDamage(&attack);
	EXPECT_EQ(0, person.getRemainingHP());
	EXPECT_TRUE(person.isDead());
}

TEST(CharacterHealthTest, SustainDamageReducesHPRemainingInCharacter){
	Character person;
	Attack attack;
	EXPECT_EQ(100, person.getRemainingHP());

	person.sustainDamage(&attack);
	EXPECT_EQ(50, person.getRemainingHP());
	EXPECT_FALSE(person.isDead());
}

TEST(CharacterHealthTest, CannotGoBelowZeroHealth){
	Character person;
	Attack attack;
	EXPECT_EQ(100, person.getRemainingHP());

	person.sustainDamage(&attack);

	attackPersonAndExpectDeadWithZeroHealth(attack, person);
	attackPersonAndExpectDeadWithZeroHealth(attack, person);
}

TEST(CharacterHealthTest, CharacterGainsHealthFromPotion){
	Character person;
	Attack attack;
	Potion healthPotion;

	person.sustainDamage(&attack);
	person.drinkPotion(healthPotion);

	EXPECT_EQ(75, person.getRemainingHP());
}

TEST(CharacterHealthTest, CharacterCannotHaveMoreThanMaxHealth){
	Character person;
	Potion healthPotion;
	person.drinkPotion(healthPotion);
	EXPECT_EQ(100, person.getRemainingHP());
}