#ifndef MOVEMENT_VALIDMOVE_H
#define MOVEMENT_VALIDMOVE_H
#include <list>
#include "Position.h"

class ValidMove{
private:
	Position container;
	std::list<Position> allOccupiedPositions;

public:
	ValidMove(Position cont, std::list<Position> allPositions);
	Position getValidMove(Position oldPosition, Vector2 speed);

};
#endif