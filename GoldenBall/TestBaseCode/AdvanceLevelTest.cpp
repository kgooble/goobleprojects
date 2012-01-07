#include "../BaseCode/Character.h"
#include "gtest/gtest.h"

TEST(AdvanceLevelTest, CannotAdvanceWhenNoItemsObtained){
	Character person;
    EXPECT_FALSE(person.isAbleToAdvance());
}

TEST(AdvanceLevelTest, CannotAdvanceWhenOnlyOneItemObtained){
	Character person;
	person.addItem(WIND_PIECE);
	EXPECT_FALSE(person.isAbleToAdvance());
}

TEST(AdvanceLevelTest, CanAdvanceWhenAllItemsObtained){
	Character person;
	person.addItem(WIND_PIECE);
	person.addItem(FIRE_PIECE);
	person.addItem(WATER_PIECE);
	person.addItem(EARTH_PIECE);
	EXPECT_TRUE(person.isAbleToAdvance());
}

TEST(AdvanceLevelTest, CannotHaveTwoOfSameItem){
	Character person;
	person.addItem(WIND_PIECE);
	person.addItem(WIND_PIECE);
	EXPECT_EQ(1, person.getItems().size());
}
