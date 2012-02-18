#include "gtest/gtest.h"
#include "../BaseCode/Position.h"

TEST(PositionTest, IfTwoPositionsCompletelyApartThenTheyDoNotOverlap){
	Position posn1(Vector2(0, 0), 50, 50);
	Position posn2(Vector2(51, 51), 10, 10);

	EXPECT_FALSE(posn1.overlaps(posn2));
	EXPECT_FALSE(posn2.overlaps(posn1));
}

TEST(PositionTest, IfOnePositionCompletelyContainedInOtherThenTheyOverlap){
	Position container(Vector2(0, 0), 50, 50);
	Position contained(Vector2(25, 25), 10, 10);

	EXPECT_TRUE(container.overlaps(contained));
	EXPECT_TRUE(contained.overlaps(container));
}

TEST(PositionTest, IfTwoPositionsOverLapLeftSideAndRightSideThenTheyOverlap){
	Position left(Vector2(0, 0), 50, 50);
	Position right(Vector2(40, 10), 20, 30);

	EXPECT_TRUE(left.overlaps(right));
	EXPECT_TRUE(right.overlaps(left));
}

TEST(PositionTest, IfTwoPositionsOverLapBottomAndTopThenTheyOverlap){
	Position top(Vector2(0, 0), 50, 50);
	Position bottom(Vector2(0, 40), 50, 30);

	EXPECT_TRUE(top.overlaps(bottom));
	EXPECT_TRUE(bottom.overlaps(top));
}

TEST(PositionTest, IfTwoPositionsOverlapTopRightAndBottomLeft){
	Position topright = Position(Vector2(100, 20), 50, 50);
	Position bottomleft = Position(Vector2(75, 50), 50, 50);
	EXPECT_TRUE(topright.overlaps(bottomleft));
	EXPECT_TRUE(bottomleft.overlaps(topright));
}