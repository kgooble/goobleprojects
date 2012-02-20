#include "gtest/gtest.h"
#include "../BaseCode/ValidMove.h"
#include <list>
#include <iostream>

TEST(ValidMoveRightTest, ValidMoveReturnsSameMoveIfItIsValid){
	std::list<Position> allPosns;
	allPosns.push_back(Position(Vector2(0, 0), 50, 50));

	ValidMove validity(Position(Vector2(0, 0), 500, 500), allPosns);
	Vector2 speed(25, 0);
	Position nextMove(Vector2(25, 0), 50, 50);
	Position validMove = validity.getValidMove(Position(Vector2(0, 0), 50, 50), speed);
	
	EXPECT_EQ(validMove, nextMove); 
}

TEST(ValidMoveRightTest, ValidMoveReturnsClosestPositionToEdgeIfMoveNotValid){
	std::list<Position> allPosns;
	allPosns.push_back(Position(Vector2(0, 0), 50, 50));
	allPosns.push_back(Position(Vector2(75, 0), 50, 50));
	ValidMove validity(Position(Vector2(0, 0), 500, 500), allPosns);
	Position expectedNextMove(Vector2(24, 0), 50, 50);
	Vector2 speed(40, 0);
	Position validMove = validity.getValidMove(Position(Vector2(0, 0), 50, 50), speed);
	EXPECT_EQ(validMove, expectedNextMove)<< "Got: " << validMove.getX() << " " << validMove.getY();
}

TEST(ValidMoveRightTest, SomethingInWayPreventsGreatJump){
	std::list<Position> allPosns;
	allPosns.push_back(Position(Vector2(0, 0), 50, 50));
	allPosns.push_back(Position(Vector2(75, 0), 50, 50));
	ValidMove validity(Position(Vector2(0, 0), 500, 500), allPosns);
	Position expectedNextMove(Vector2(24, 0), 50, 50);
	Vector2 speed(126, 0);
	Position validMove = validity.getValidMove(Position(Vector2(0, 0), 50, 50), speed);
	EXPECT_EQ(validMove, expectedNextMove) << "Got: " << validMove.getX() << " " << validMove.getY();
}

TEST(ValidMoveLeftTest, ValidMoveReturnsClosestPositionToEdgeIfMoveNotValid){
	std::list<Position> allPosns;
	allPosns.push_back(Position(Vector2(150, 0), 50, 50));
	allPosns.push_back(Position(Vector2(75, 0), 50, 50));
	
	ValidMove validity(Position(Vector2(0, 0), 500, 500), allPosns);

	Position expectedNextMove(Vector2(126, 0), 50, 50);
	Vector2 speed(-50, 0);
	Position validMove = validity.getValidMove(Position(Vector2(150, 0), 50, 50), speed);
	EXPECT_EQ(validMove, expectedNextMove)<< "Got: " << validMove.getX() << " " << validMove.getY();
}

TEST(ValidMoveLeftTest, IfThinPieceCenterCloserThanThickPieceCenterThickPieceStillConsideredClosest){
	std::list<Position> allPosns;
	allPosns.push_back(Position(Vector2(150, 20), 50, 50));
	allPosns.push_back(Position(Vector2(75, 0), 10, 50));
	allPosns.push_back(Position(Vector2(75, 50), 50, 50));
	ValidMove validity(Position(Vector2(0, 0), 500, 500), allPosns);

	Position expectedNextMove(Vector2(126, 20), 50, 50);
	Vector2 speed(-150, 0);
	Position validMove = validity.getValidMove(Position(Vector2(150, 20), 50, 50), speed);
	EXPECT_EQ(validMove, expectedNextMove)<< "Got: " << validMove.getX() << " " << validMove.getY();
}

TEST(ValidMoveUpTest, StandardMoveUpWorks){
	std::list<Position> allPosns;
	Position myPosn(Vector2(30, 30), 10, 10);
	allPosns.push_back(myPosn);
	allPosns.push_back(Position(Vector2(20, 10), 20, 10));
	ValidMove validity(Position(Vector2(0, 0), 500, 500), allPosns);

	Position expectedNextMove(Vector2(30, 21), 10, 10);
	Vector2 speed(0, -50);
	Position validMove = validity.getValidMove(myPosn, speed);
	EXPECT_EQ(validMove, expectedNextMove)<< "Got: " << validMove.getX() << " " << validMove.getY();
}

TEST(ValidMoveDownTest, StandardMoveDownWorks){
	std::list<Position> allPosns;
	Position myPosn = Position(Vector2(20, 10), 20, 10);
	allPosns.push_back(Position(Vector2(30, 30), 10, 10));
	allPosns.push_back(myPosn);
	ValidMove validity(Position(Vector2(0, 0), 500, 500), allPosns);

	Position expectedNextMove(Vector2(20, 19), 20, 10);
	Vector2 speed(0, 50);
	Position validMove = validity.getValidMove(myPosn, speed);
	EXPECT_EQ(validMove, expectedNextMove)<< "Got: " << validMove.getX() << " " << validMove.getY();
}