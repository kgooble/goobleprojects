#include "CollidingPositions.h"

CollidingPositions::CollidingPositions(Position oldP, Position newP){
	oldPosn = oldP;
	newPosn = newP;
}

bool CollidingPositions::wouldCollideAtThatPosition(Position iter){
	Position test(Vector2(iter.getX(), iter.getY()), oldPosn.getWidth(), oldPosn.getHeight());
	return test.overlaps(iter);
}

bool CollidingPositions::isInBetweenAlongYAxis(Vector2 unitspeed, Position currPosn){
	return 0 >= unitspeed.getY() * (oldPosn.getCenterY() - currPosn.getCenterY()) 
		&& 0 >= unitspeed.getY() * (currPosn.getCenterY() - newPosn.getCenterY());
}

bool CollidingPositions::isInBetweenAlongXAxis(Vector2 unitspeed, Position currPosn){
	return 0 >= unitspeed.getX() * (oldPosn.getCenterX() - currPosn.getCenterX()) 
	    && 0 >= unitspeed.getX() * (currPosn.getCenterX() - newPosn.getCenterX());
}

bool CollidingPositions::isInBetweenOldAndNewPosns(Vector2 unitspeed, Position currPosn){
	return isInBetweenAlongXAxis(unitspeed, currPosn) || isInBetweenAlongYAxis(unitspeed, currPosn);
}

std::list<Position> CollidingPositions::findAllInDir(std::list<Position> all, Vector2 unitspeed){
	std::list<Position> colliders;
	for (std::list<Position>::iterator iter=all.begin(); iter!=all.end(); iter++){
		if ((*iter) == oldPosn)
			continue;
		if (iter->overlaps(newPosn)){
			colliders.push_back(*iter);
			continue;
		}
		if (isInBetweenOldAndNewPosns(unitspeed, *iter) && wouldCollideAtThatPosition(*iter)) {
			colliders.push_back(*iter);
		}
	}
	return colliders;
}