#include "MazeHedge.h"

MazeHedge::MazeHedge(int width, Point first, Point second){
	hedgeWidth = width;
	firstPoint = first;
	secondPoint = second;
}

bool MazeHedge::isHorizontalLine(){
	return firstPoint.getX() == secondPoint.getX();
}

Point MazeHedge::getNorthWestHorizontalPoint(){
	return Point(firstPoint.getX() - (int) (hedgeWidth / 2), firstPoint.getY());
}

Point MazeHedge::getNorthEastHorizontalPoint(){
	return Point(firstPoint.getX() + (int) (hedgeWidth / 2), firstPoint.getY());
}

MazeHedge MazeHedge::getNorthBoundary(){
	if (isHorizontalLine()){
		return MazeHedge (0, 
			getNorthWestHorizontalPoint(),
			getNorthEastHorizontalPoint());
	}
	return MazeHedge(0,
		Point(firstPoint.getX(), firstPoint.getY() - (int) (hedgeWidth / 2)),
		Point(secondPoint.getX(), firstPoint.getY() - (int) (hedgeWidth / 2)));
}

MazeHedge MazeHedge::getSouthBoundary(){
	if (isHorizontalLine()){
		return MazeHedge (0, 
			Point(firstPoint.getX() - (int) (hedgeWidth / 2), secondPoint.getY()),
			Point(firstPoint.getX() + (int) (hedgeWidth / 2), secondPoint.getY()));
	}
	return MazeHedge (0, 
		Point(firstPoint.getX(), firstPoint.getY() + (int) (hedgeWidth / 2)),
		Point(secondPoint.getX(), firstPoint.getY() + (int) (hedgeWidth / 2)));
}

MazeHedge MazeHedge::getWestBoundary(){
	if (isHorizontalLine()){
		return MazeHedge (0, 
			getNorthWestHorizontalPoint(),
			Point(firstPoint.getX() - (int) (hedgeWidth / 2), secondPoint.getY()));
	}
	return MazeHedge (0, 
		Point(firstPoint.getX(), firstPoint.getY() - (int) (hedgeWidth / 2)),
		Point(firstPoint.getX(), firstPoint.getY() + (int) (hedgeWidth / 2)));
}

MazeHedge MazeHedge::getEastBoundary(){
	if (isHorizontalLine()){
		return MazeHedge (0, 
			getNorthEastHorizontalPoint(),
			Point(firstPoint.getX() + (int) (hedgeWidth / 2), secondPoint.getY()));
	}
	return MazeHedge (0, 
		Point(secondPoint.getX(), secondPoint.getY() - (int) (hedgeWidth / 2)),
		Point(secondPoint.getX(), secondPoint.getY() + (int) (hedgeWidth / 2)));
}

Point* MazeHedge::getFirstPoint(){
	return &firstPoint;
}

Point* MazeHedge::getSecondPoint(){
	return &secondPoint;
}
