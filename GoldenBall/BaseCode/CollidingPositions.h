#ifndef MOVEMENT_COLLIDINGPOSITIONS_H
#define MOVEMENT_COLLIDINGPOSITIONS_H
#include "Position.h"
#include <list>

class CollidingPositions{
private:
	Position oldPosn;
	Position newPosn;
	bool isInBetweenAlongXAxis(Vector2 unitspeed, Position currPosn);
	bool isInBetweenAlongYAxis(Vector2 unitspeed, Position currPosn);
	bool wouldCollideAtThatPosition(Position currPosn);
	bool isInBetweenOldAndNewPosns(Vector2 unitspeed, Position currPosn);
public:
	CollidingPositions(Position oldPosn, Position newPosn);
	std::list<Position> findAllInDir(std::list<Position> all, Vector2 unitspeed);
};

#endif