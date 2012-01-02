#include "gtest/gtest.h"
#include "../BaseCode/MazeHedge.h"
#include <iostream>

TEST(MazeHedgeTest, BorderCalculationsAreAccurateWithVerticalLine){
	MazeHedge line(50, Point(50, 50), Point(50, 100));
	
	EXPECT_EQ(Point(25, 50), *line.getNorthBoundary().getFirstPoint());
	EXPECT_EQ(Point(75, 50), *line.getNorthBoundary().getSecondPoint());

	EXPECT_EQ(Point(25, 50), *line.getWestBoundary().getFirstPoint());
	EXPECT_EQ(Point(25, 100), *line.getWestBoundary().getSecondPoint());

	EXPECT_EQ(Point(25, 100), *line.getSouthBoundary().getFirstPoint());
	EXPECT_EQ(Point(75, 100), *line.getSouthBoundary().getSecondPoint());

	EXPECT_EQ(Point(75, 50), *line.getEastBoundary().getFirstPoint());
	EXPECT_EQ(Point(75, 100), *line.getEastBoundary().getSecondPoint());
}

TEST(MazeHedgeTest, BorderCalculationsAreAccurateWithHorizontalLine){
	MazeHedge line(50, Point(50, 50), Point(100, 50));
	
	EXPECT_EQ(Point(50, 25), *line.getNorthBoundary().getFirstPoint());
	EXPECT_EQ(Point(100, 25), *line.getNorthBoundary().getSecondPoint());

	EXPECT_EQ(Point(50, 25), *line.getWestBoundary().getFirstPoint());
	EXPECT_EQ(Point(50, 75), *line.getWestBoundary().getSecondPoint());

	EXPECT_EQ(Point(50, 75), *line.getSouthBoundary().getFirstPoint());
	EXPECT_EQ(Point(100, 75), *line.getSouthBoundary().getSecondPoint());

	EXPECT_EQ(Point(100, 25), *line.getEastBoundary().getFirstPoint());
	EXPECT_EQ(Point(100, 75), *line.getEastBoundary().getSecondPoint());
}

TEST(MazeHedgeTest, MazeHedgeContainsPointsWithinItsBoundaries){
	MazeHedge line(50, Point(50, 50), Point(100, 50));

	EXPECT_TRUE(line.contains(Point(75, 50)));
	EXPECT_FALSE(line.contains(Point(0, 0)));
}