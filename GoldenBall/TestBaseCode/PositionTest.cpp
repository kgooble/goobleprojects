#include "gtest/gtest.h"
#include "../BaseCode/Position.h"

TEST(PositionTest, IfTwoPositionsCompletelyApartThenTheyDoNotOverlap){
	Position posn1(Point(0, 0), 50, 50);
	Position posn2(Point(51, 51), 10, 10);

	EXPECT_FALSE(posn1.overlaps(posn2));
	EXPECT_FALSE(posn2.overlaps(posn1));
}

TEST(PositionTest, IfOnePositionCompletelyContainedInOtherThenTheyOverlap){
	Position container(Point(0, 0), 50, 50);
	Position contained(Point(25, 25), 10, 10);

	EXPECT_TRUE(container.overlaps(contained));
	EXPECT_TRUE(contained.overlaps(container));
}

TEST(PositionTest, IfTwoPositionsOverLapLeftSideAndRightSideThenTheyOverlap){
	Position left(Point(0, 0), 50, 50);
	Position right(Point(40, 10), 20, 30);

	EXPECT_TRUE(left.overlaps(right));
	EXPECT_TRUE(right.overlaps(left));
}

TEST(PositionTest, IfTwoPositionsOverLapBottomAndTopThenTheyOverlap){
	Position top(Point(0, 0), 50, 50);
	Position bottom(Point(0, 40), 50, 30);

	EXPECT_TRUE(top.overlaps(bottom));
	EXPECT_TRUE(bottom.overlaps(top));
}

TEST(PositionTest, IfTwoPositionsOverlapTopRightAndBottomLeft){
	Position topright = Position(Point(100, 20), 50, 50);
	Position bottomleft = Position(Point(75, 50), 50, 50);
	EXPECT_TRUE(topright.overlaps(bottomleft));
	EXPECT_TRUE(bottomleft.overlaps(topright));
}