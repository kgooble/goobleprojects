#include "CollidingPositions.h"

CollidingPositions::CollidingPositions(Position oldP, Position newP){
	oldPosn = oldP;
	newPosn = newP;
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
		if (   0 >= unitspeed.getX() * (oldPosn.getCenterX() - iter->getCenterX()) 
			&& 0 >= unitspeed.getX() * (iter->getCenterX() - newPosn.getCenterX())) {
			Position test(Vector2(iter->getX(), iter->getY()), oldPosn.getWidth(), oldPosn.getHeight());
			if (test.overlaps(*iter)){
				colliders.push_back(*iter);
			}

		}
	}
	return colliders;
}