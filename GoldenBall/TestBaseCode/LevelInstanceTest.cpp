#include "gtest/gtest.h"
/*#include "../BaseCode/GameArea.h"

TEST(GameAreaTest, CanAddCharactersAndItemsToGameArea){
	GameArea area;
	area.addCharacter(Character(LEFT_SIDE, MAGE));
	area.addCharacter(Character(RIGHT_SIDE, ROGUE));
	EXPECT_EQ(2, area.getObjects().size());

	area.addItem(Potion);
	EXPECT_EQ(3, area.getObjects().size());
}*/

/*
How to store character positions
1) store it within a character -- would also then need to store it within potions, items
   -- Positioned Superclass requird
2) store it within an "object wrapper" - something that contains either
   a character, potion, base, etc. - anything that can have a position
3) map from item to position. this could complicate things?

Do characters know their own position?
Pros:
1) characters know their own position, so it's stored there. can do a simple
character.getPosition.
2) what are the alternatives?

Cons:
1) ???


Ok, so characters, items, know their own position.
when you add to the game area, you add it with a position on the game area.
when a character moves, the game area does the calculations, and then changes 
the character's position.

GAME.

Create a Game Instance.
Start at Level 1. I.e new Level Instance created.
When a Level is passed, a new level instance takes its place - level 2.
Events take place on the screen, such as mouse movements and keyboard strokes.
The Game Instance translates events into actions that the level instance does.
(e.g. on click -> gameinstance.movechar, or something like that)
Game Instance responsible for:
---> when it's created, starts a thread that spawns characters every few seconds
---> handling movements. 

*/