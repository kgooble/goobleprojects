#include "../BaseCode/Maze.h"
#include "gtest/gtest.h"

TEST(MazeTest, ValidPositionInMazeIsOneWithoutHedgesInIt){
	std::vector<MazeHedge> hedges;
	hedges.push_back(MazeHedge(Point(125, 0), Point(125, 350)));
	hedges.push_back(MazeHedge(Point(375, 150), Point(375, 500)));
	Maze maze(hedges);
	
	EXPECT_FALSE(maze.isValidLocation(&Point(125, 80)));
	EXPECT_FALSE(maze.isValidLocation(&Point()));
	EXPECT_TRUE(maze.isValidLocation(&Point(75, 200)));
}

TEST(BorderHedgeCreationTest, BorderHedgesAreProperlyPositioned){
	std::vector<MazeHedge> hedges;
	BorderHedges(600, 600, 10).addBorderHedgesTo(&hedges);
	
	EXPECT_EQ(4, hedges.size());

	MazeHedge north = hedges[0];
	EXPECT_EQ(Point(0, 5), *north.getFirstPoint());
	EXPECT_EQ(Point(600, 5), *north.getSecondPoint());
	EXPECT_EQ(10, north.getHedgeWidth());

	MazeHedge south = hedges[1];
	EXPECT_EQ(Point(0, 600 - 5), *south.getFirstPoint());
	EXPECT_EQ(Point(600, 600 - 5), *south.getSecondPoint());

	MazeHedge east = hedges[2];
	EXPECT_EQ(Point(600 - 5, 0), *east.getFirstPoint());
	EXPECT_EQ(Point(600 - 5, 600), *east.getSecondPoint());

	MazeHedge west = hedges[3];
	EXPECT_EQ(Point(5, 0), *west.getFirstPoint());
	EXPECT_EQ(Point(5, 600), *west.getSecondPoint());
}