#include "MazeHedge.h"

MazeHedge::MazeHedge(Vector2 first, Vector2 second, int width){
	hedgeWidth = width;
	firstPoint = first;
	secondPoint = second;
}

bool MazeHedge::isVerticalLine(){
	return firstPoint.getX() == secondPoint.getX();
}

int MazeHedge::getHedgeWidth(){
	return hedgeWidth;
}

Vector2 MazeHedge::getWestHorizontalPoint(Vector2* vertPoint){
	return Vector2(vertPoint->getX() - (int) (hedgeWidth / 2), vertPoint->getY());
}

Vector2 MazeHedge::getEastHorizontalPoint(Vector2* vertPoint){
	return Vector2(vertPoint->getX() + (int) (hedgeWidth / 2), vertPoint->getY());
}

Vector2 MazeHedge::getNorthVerticalPoint(Vector2 *horizPoint){
	return Vector2(horizPoint->getX(), horizPoint->getY() - (int) (hedgeWidth / 2));
}

Vector2 MazeHedge::getSouthVerticalPoint(Vector2 *horizPoint){
	return Vector2(horizPoint->getX(), horizPoint->getY() + (int) (hedgeWidth / 2));
}

MazeHedge MazeHedge::getNorthBoundary(){
	if (isVerticalLine()){
		return MazeHedge (
			getWestHorizontalPoint(&firstPoint),
			getEastHorizontalPoint(&firstPoint), 0);
	}
	return MazeHedge(
		getNorthVerticalPoint(&firstPoint),
		getNorthVerticalPoint(&secondPoint), 0);
}

MazeHedge MazeHedge::getSouthBoundary(){
	if (isVerticalLine()){
		return MazeHedge (
			getWestHorizontalPoint(&secondPoint),
			getEastHorizontalPoint(&secondPoint), 0);
	}
	return MazeHedge ( 
		getSouthVerticalPoint(&firstPoint),
		getSouthVerticalPoint(&secondPoint), 0);
}

MazeHedge MazeHedge::getWestBoundary(){
	if (isVerticalLine()){
		return MazeHedge (
			getWestHorizontalPoint(&firstPoint),
			getWestHorizontalPoint(&secondPoint), 0);
	}
	return MazeHedge (
		getNorthVerticalPoint(&firstPoint),
		getSouthVerticalPoint(&firstPoint), 0);
}

MazeHedge MazeHedge::getEastBoundary(){
	if (isVerticalLine()){
		return MazeHedge (
			getEastHorizontalPoint(&firstPoint),
			getEastHorizontalPoint(&secondPoint), 0);
	}
	return MazeHedge (
		getNorthVerticalPoint(&secondPoint),
		getSouthVerticalPoint(&secondPoint), 0);
}

Vector2* MazeHedge::getFirstPoint(){
	return &firstPoint;
}

Vector2* MazeHedge::getSecondPoint(){
	return &secondPoint;
}

bool MazeHedge::contains(Vector2* p){
	return getWestBoundary().getFirstPoint()->getX() <= p->getX() 
		&& getEastBoundary().getFirstPoint()->getX() >= p->getX()
		&& getNorthBoundary().getFirstPoint()->getY() <= p->getY()
		&& getSouthBoundary().getFirstPoint()->getY() >= p->getY();
}