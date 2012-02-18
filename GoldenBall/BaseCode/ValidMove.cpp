#include "ValidMove.h"
#include "CollidingPositions.h"
#include <math.h>

ValidMove::ValidMove(Position cont, std::list<Position> allPosns){
	container = cont;
	allOccupiedPositions = allPosns;
}

Vector2 getUnitSpeedVector(Vector2 speed){
	int x = speed.getX() > 0? 1 : -1;
	int y = speed.getY() > 0? 1 : -1;

	if (speed.getX() == 0)
		x = 0;
	if (speed.getY() == 0)
		y = 0;
	return Vector2(x, y);
}

Position addSpeedTo(Position oldPosn, Vector2 speed){
	return Position(Vector2(oldPosn.getX() + speed.getX(), oldPosn.getY() + speed.getY()), oldPosn.getWidth(), oldPosn.getHeight());
}

int distance(Position one, Position two){
	int xDist = one.getCenterX() - two.getCenterX();
	int yDist = one.getCenterY() - two.getCenterY();
	return (int) sqrt((double)(xDist*xDist + yDist*yDist));
}

bool closerThanClosestGuy(Position closestGuy, Position newGuy, Position myGuy){
	return distance(closestGuy, myGuy) > distance(newGuy, myGuy);
}

Position ValidMove::getValidMove(Position oldPosn, Vector2 speed){
	Vector2 unitspeed = getUnitSpeedVector(speed);
	Position furthestPossible = addSpeedTo(oldPosn, speed);

	std::list<Position> colliders = CollidingPositions(oldPosn, furthestPossible).findAllInDir(allOccupiedPositions, unitspeed);

	if (colliders.empty())
		return furthestPossible;

	Position closestGuy = colliders.front();
	for (std::list<Position>::iterator iter=colliders.begin(); iter!=colliders.end(); iter++){
		if (closerThanClosestGuy(closestGuy, *iter, oldPosn))
			closestGuy = *iter;
	}

	int newX = closestGuy.getCenterX() - (int) (unitspeed.getX() * (closestGuy.getWidth() + oldPosn.getWidth())/ 2) - (unitspeed.getX() * 1);
	newX = newX - (int) (oldPosn.getWidth() / 2);
	return Position(Vector2(newX, oldPosn.getY()), oldPosn.getWidth(), oldPosn.getHeight());

}