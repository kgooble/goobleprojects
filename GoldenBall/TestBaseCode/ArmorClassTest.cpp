#include "../BaseCode/ArmorClass.h"
#include "gtest/gtest.h"

TEST(ArmorClassTest, NoArmorMitigatesNoDamage){
	ArmorClass noArmor = NO_ARMOR;
	Attack attack;
    EXPECT_EQ(attack.getDamage(), noArmor.getMitigatedDamage(&attack));
}

TEST(ArmorClassTest, LowArmorMitigatesSomeDamage){
	ArmorClass lowArmor = LOW_ARMOR;
	Attack attack;
	EXPECT_EQ(40, lowArmor.getMitigatedDamage(&attack));
}

TEST(ArmorClassTest, HighArmorMitigatesSignificantDamage){
	ArmorClass lowArmor = HIGH_ARMOR;
	Attack attack;
	EXPECT_EQ(30, lowArmor.getMitigatedDamage(&attack));
}
