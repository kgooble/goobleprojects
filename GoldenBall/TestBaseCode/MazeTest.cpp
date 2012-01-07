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

void checkPoints(MazeHedge* border, Point* firstPoint, Point* secondPoint){
	EXPECT_EQ(*firstPoint, *border->getFirstPoint());
	EXPECT_EQ(*secondPoint, *border->getSecondPoint());
}

TEST(BorderHedgeCreationTest, BorderHedgesAreProperlyPositioned){
	std::vector<MazeHedge> hedges;
	BorderHedges(600, 600, 10).addBorderHedgesTo(&hedges);
	
	EXPECT_EQ(4, hedges.size());

	MazeHedge north = hedges[0];
	EXPECT_EQ(10, north.getHedgeWidth());
	checkPoints(&north, &Point(0, 5), &Point(600, 5));

	MazeHedge south = hedges[1];
	checkPoints(&south, &Point(0, 600 - 5), &Point(600, 600 - 5));

	MazeHedge east = hedges[2];
	checkPoints(&east, &Point(600 - 5, 0), &Point(600 - 5, 600));

	MazeHedge west = hedges[3];
	checkPoints(&west, &Point(5, 0), &Point(5, 600));
}

