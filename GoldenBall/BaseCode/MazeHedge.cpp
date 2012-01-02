#include "MazeHedge.h"

MazeHedge::MazeHedge(int width, Point first, Point second){
	hedgeWidth = width;
	firstPoint = first;
	secondPoint = second;
}

bool MazeHedge::isVerticalLine(){
	return firstPoint.getX() == secondPoint.getX();
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
		return MazeHedge (0, 
			getWestHorizontalPoint(&firstPoint),
			getEastHorizontalPoint(&firstPoint));
	}
	return MazeHedge(0,
		getNorthVerticalPoint(&firstPoint),
		getNorthVerticalPoint(&secondPoint));
}

MazeHedge MazeHedge::getSouthBoundary(){
	if (isVerticalLine()){
		return MazeHedge (0,
			getWestHorizontalPoint(&secondPoint),
			getEastHorizontalPoint(&secondPoint));
	}
	return MazeHedge (0, 
		getSouthVerticalPoint(&firstPoint),
		getSouthVerticalPoint(&secondPoint));
}

MazeHedge MazeHedge::getWestBoundary(){
	if (isVerticalLine()){
		return MazeHedge (0, 
			getWestHorizontalPoint(&firstPoint),
			getWestHorizontalPoint(&secondPoint));
	}
	return MazeHedge (0, 
		getNorthVerticalPoint(&firstPoint),
		getSouthVerticalPoint(&firstPoint));
}

MazeHedge MazeHedge::getEastBoundary(){
	if (isVerticalLine()){
		return MazeHedge (0, 
			getEastHorizontalPoint(&firstPoint),
			getEastHorizontalPoint(&secondPoint));
	}
	return MazeHedge (0, 
		getNorthVerticalPoint(&secondPoint),
		getSouthVerticalPoint(&secondPoint));
}

Point* MazeHedge::getFirstPoint(){
	return &firstPoint;
}

Point* MazeHedge::getSecondPoint(){
	return &secondPoint;
}

bool MazeHedge::contains(Point p){
	return getWestBoundary().getFirstPoint()->getX() <= p.getX() 
		&& getEastBoundary().getFirstPoint()->getX() >= p.getX()
		&& getNorthBoundary().getFirstPoint()->getY() <= p.getY()
		&& getSouthBoundary().getFirstPoint()->getY() >= p.getY();
}