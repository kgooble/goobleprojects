#include "gtest/gtest.h"
#include "../BaseCode/ValidMove.h"
#include <list>
#include <iostream>

TEST(ValidMoveTest, ValidMoveReturnsSameMoveIfItIsValid){
	std::list<Position> allPosns;
	allPosns.push_back(Position(Point(0, 0), 50, 50));

	ValidMove validity(Position(Point(0, 0), 500, 500), allPosns);
	Point speed(25, 0);
	Position nextMove(Point(25, 0), 50, 50);
	Position validMove = validity.getValidMove(Position(Point(0, 0), 50, 50), speed);
	
	EXPECT_EQ(validMove, nextMove); 
}

TEST(ValidMoveTest, ValidMoveReturnsClosestPositionToEdgeIfMoveNotValid){
	std::list<Position> allPosns;
	allPosns.push_back(Position(Point(0, 0), 50, 50));
	allPosns.push_back(Position(Point(75, 0), 50, 50));
	ValidMove validity(Position(Point(0, 0), 500, 500), allPosns);
	Position expectedNextMove(Point(24, 0), 50, 50);
	Point speed(40, 0);
	Position validMove = validity.getValidMove(Position(Point(0, 0), 50, 50), speed);
	EXPECT_EQ(validMove, expectedNextMove)<< "Got: " << validMove.getX() << " " << validMove.getY();
}

TEST(ValidMoveTest, SomethingInWayPreventsGreatJump){
	std::list<Position> allPosns;
	allPosns.push_back(Position(Point(0, 0), 50, 50));
	allPosns.push_back(Position(Point(75, 0), 50, 50));
	ValidMove validity(Position(Point(0, 0), 500, 500), allPosns);
	Position expectedNextMove(Point(24, 0), 50, 50);
	Point speed(126, 0);
	Position validMove = validity.getValidMove(Position(Point(0, 0), 50, 50), speed);
	EXPECT_EQ(validMove, expectedNextMove) << "Got: " << validMove.getX() << " " << validMove.getY();
}