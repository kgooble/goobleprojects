#ifndef MOVEMENT_COLLIDINGPOSITIONS_H
#define MOVEMENT_COLLIDINGPOSITIONS_H
#include "Position.h"
#include <list>

class CollidingPositions{
private:
	Position oldPosn;
	Position newPosn;
public:
	CollidingPositions(Position oldPosn, Position newPosn);
	std::list<Position> findAllInDir(std::list<Position> all, Vector2 unitspeed);
};

#endif