#include "MazeHedge.h"

Point::Point(int X, int Y){
	x = X;
	y = Y;
}

int Point::getX() const{
	return x;
}

int Point::getY() const{
	return y;
}

bool operator ==(const Point& lhs, const Point& rhs){
	return lhs.getX() == rhs.getX();
}

MazeHedge::MazeHedge(int width, Point first, Point second){
	hedgeWidth = width;
	firstPoint = first;
	secondPoint = second;
}

MazeHedge MazeHedge::getNorthBoundary(){
	return MazeHedge (0, 
		Point(firstPoint.getX() - (int) (hedgeWidth / 2), firstPoint.getY()),
		Point(firstPoint.getX() + (int) (hedgeWidth / 2), firstPoint.getY()));
}

MazeHedge MazeHedge::getSouthBoundary(){
	return MazeHedge (0, 
		Point(firstPoint.getX() - (int) (hedgeWidth / 2), secondPoint.getY()),
		Point(firstPoint.getX() + (int) (hedgeWidth / 2), secondPoint.getY()));
}

MazeHedge MazeHedge::getWestBoundary(){
	return MazeHedge (0, 
		Point(firstPoint.getX() - (int) (hedgeWidth / 2), firstPoint.getY()),
		Point(firstPoint.getX() - (int) (hedgeWidth / 2), secondPoint.getY()));
}

MazeHedge MazeHedge::getEastBoundary(){
	return MazeHedge (0, 
		Point(firstPoint.getX() + (int) (hedgeWidth / 2), firstPoint.getY()),
		Point(firstPoint.getX() + (int) (hedgeWidth / 2), secondPoint.getY()));
}

Point* MazeHedge::getFirstPoint(){
	return &firstPoint;
}

Point* MazeHedge::getSecondPoint(){
	return &secondPoint;
}
