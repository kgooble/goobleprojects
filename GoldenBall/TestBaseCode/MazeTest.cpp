#include "../BaseCode/Maze.h"
#include "gtest/gtest.h"

TEST(MazeTest, ValidPositionInMazeIsOneWithoutHedgesInIt){
	std::vector<MazeHedge> hedges;
	hedges.push_back(MazeHedge(Vector2(125, 0), Vector2(125, 350)));
	hedges.push_back(MazeHedge(Vector2(375, 150), Vector2(375, 500)));
	Maze maze(hedges);
	
	EXPECT_FALSE(maze.isValidLocation(&Vector2(125, 80)));
	EXPECT_FALSE(maze.isValidLocation(&Vector2()));
	EXPECT_TRUE(maze.isValidLocation(&Vector2(75, 200)));
}

void checkPoints(MazeHedge* border, Vector2* firstPoint, Vector2* secondPoint){
	EXPECT_EQ(*firstPoint, *border->getFirstPoint());
	EXPECT_EQ(*secondPoint, *border->getSecondPoint());
}

TEST(BorderHedgeCreationTest, BorderHedgesAreProperlyPositioned){
	std::vector<MazeHedge> hedges;
	BorderHedges(600, 600, 10).addBorderHedgesTo(&hedges);
	
	EXPECT_EQ(4, hedges.size());

	MazeHedge north = hedges[0];
	EXPECT_EQ(10, north.getHedgeWidth());
	checkPoints(&north, &Vector2(0, 5), &Vector2(600, 5));

	MazeHedge south = hedges[1];
	checkPoints(&south, &Vector2(0, 600 - 5), &Vector2(600, 600 - 5));

	MazeHedge east = hedges[2];
	checkPoints(&east, &Vector2(600 - 5, 0), &Vector2(600 - 5, 600));

	MazeHedge west = hedges[3];
	checkPoints(&west, &Vector2(5, 0), &Vector2(5, 600));
}

