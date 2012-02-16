#include <list>
#include "Position.h"

class ValidMove{
private:
	Position container;
	std::list<Position> allOccupiedPositions;

public:
	ValidMove(Position cont, std::list<Position> allPositions);
	Position getValidMove(Position oldPosition, Point speed);

};