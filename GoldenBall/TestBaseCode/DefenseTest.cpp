#include "../BaseCode/Defense.h"
#include "gtest/gtest.h"

TEST(DefenseTest, NoArmorMitigatesNoDamage){
	Defense noArmor = NO_RESISTANCE;
	Attack attack;
    EXPECT_EQ(attack.getDamage(), noArmor.getMitigatedDamage(&attack));
}

TEST(DefenseTest, LowArmorMitigatesSomeDamage){
	Defense lowArmor = LOW_RESISTANCE;
	Attack attack;
	EXPECT_EQ(40, lowArmor.getMitigatedDamage(&attack));
}

TEST(DefenseTest, HighArmorMitigatesSignificantDamage){
	Defense highArmor = HIGH_RESISTANCE;
	Attack attack;
	EXPECT_EQ(30, highArmor.getMitigatedDamage(&attack));
}
