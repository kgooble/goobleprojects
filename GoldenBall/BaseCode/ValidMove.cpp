#include "ValidMove.h"

ValidMove::ValidMove(Position cont, std::list<Position> allPosns){
	container = cont;
	allOccupiedPositions = allPosns;
}

Point getUnitSpeedVector(Point speed){
	int x = speed.getX() > 0? 1 : -1;
	int y = speed.getY() > 0? 1 : -1;

	if (speed.getX() == 0)
		x = 0;
	if (speed.getY() == 0)
		y = 0;
	return Point(x, y);
}

Position addSpeedTo(Position oldPosn, Point speed){
	return Position(Point(oldPosn.getX() + speed.getX(), oldPosn.getY() + speed.getY()), oldPosn.getWidth(), oldPosn.getHeight());
}

Position ValidMove::getValidMove(Position oldPosn, Point speed){
	Point unitspeed = getUnitSpeedVector(speed);
	Position furthestPossible = addSpeedTo(oldPosn, speed);

	std::list<Position> colliders;
	for (std::list<Position>::iterator iter=allOccupiedPositions.begin(); iter!=allOccupiedPositions.end(); iter++){
		if ((*iter) == oldPosn)
			continue;
		if (iter->overlaps(furthestPossible))
			colliders.push_back(*iter);
		if (iter->getX() > oldPosn.getEndX() && iter->getEndX() < furthestPossible.getX()){
			Position test(Point(iter->getX(), iter->getY()), oldPosn.getWidth(), oldPosn.getHeight());
			if (test.overlaps(*iter)){
				colliders.push_back(*iter);
			}

		}
	}

	if (colliders.empty())
		return furthestPossible;

	Position closestGuy = colliders.front();
	for (std::list<Position>::iterator iter=colliders.begin(); iter!=colliders.end(); iter++){
		if (iter->getX() < closestGuy.getX())
			closestGuy = *iter;
	}

	return Position(Point(closestGuy.getX() - oldPosn.getWidth() - 1, oldPosn.getY()), oldPosn.getWidth(), oldPosn.getHeight());

}