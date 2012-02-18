#include "ValidMove.h"
#include <math.h>

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

int distance(Position one, Position two){
	int xDist = one.getCenterX() - two.getCenterX();
	int yDist = one.getCenterY() - two.getCenterY();
	return (int) sqrt((double)(xDist*xDist + yDist*yDist));
}

bool closerThanClosestGuy(Position closestGuy, Position newGuy, Position myGuy){
	return distance(closestGuy, myGuy) > distance(newGuy, myGuy);
}

Position ValidMove::getValidMove(Position oldPosn, Point speed){
	Point unitspeed = getUnitSpeedVector(speed);
	Position furthestPossible = addSpeedTo(oldPosn, speed);

	std::list<Position> colliders;
	for (std::list<Position>::iterator iter=allOccupiedPositions.begin(); iter!=allOccupiedPositions.end(); iter++){
		if ((*iter) == oldPosn)
			continue;
		if (iter->overlaps(furthestPossible)){
			colliders.push_back(*iter);
			continue;
		}
		if (   0 >= unitspeed.getX() * (oldPosn.getCenterX() - iter->getCenterX()) 
			&& 0 >= unitspeed.getX() * (iter->getCenterX() - furthestPossible.getCenterX())) {
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
		if (closerThanClosestGuy(closestGuy, *iter, oldPosn))
			closestGuy = *iter;
	}

	int newX = closestGuy.getCenterX() - (int) (unitspeed.getX() * (closestGuy.getWidth() + oldPosn.getWidth())/ 2) - (unitspeed.getX() * 1);
	newX = newX - (int) (oldPosn.getWidth() / 2);
	return Position(Point(newX, oldPosn.getY()), oldPosn.getWidth(), oldPosn.getHeight());

}