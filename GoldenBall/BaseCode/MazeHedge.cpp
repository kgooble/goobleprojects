#include "MazeHedge.h"

MazeHedge::MazeHedge(Point first, Point second, int width){
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

Point MazeHedge::getWestHorizontalPoint(Point* vertPoint){
	return Point(vertPoint->getX() - (int) (hedgeWidth / 2), vertPoint->getY());
}

Point MazeHedge::getEastHorizontalPoint(Point* vertPoint){
	return Point(vertPoint->getX() + (int) (hedgeWidth / 2), vertPoint->getY());
}

Point MazeHedge::getNorthVerticalPoint(Point *horizPoint){
	return Point(horizPoint->getX(), horizPoint->getY() - (int) (hedgeWidth / 2));
}

Point MazeHedge::getSouthVerticalPoint(Point *horizPoint){
	return Point(horizPoint->getX(), horizPoint->getY() + (int) (hedgeWidth / 2));
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

Point* MazeHedge::getFirstPoint(){
	return &firstPoint;
}

Point* MazeHedge::getSecondPoint(){
	return &secondPoint;
}

bool MazeHedge::contains(Point* p){
	return getWestBoundary().getFirstPoint()->getX() <= p->getX() 
		&& getEastBoundary().getFirstPoint()->getX() >= p->getX()
		&& getNorthBoundary().getFirstPoint()->getY() <= p->getY()
		&& getSouthBoundary().getFirstPoint()->getY() >= p->getY();
}