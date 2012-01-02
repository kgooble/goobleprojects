#include "MazeHedge.h"

MazeHedge::MazeHedge(int width, Point first, Point second){
	hedgeWidth = width;
	firstPoint = first;
	secondPoint = second;
}

MazeHedge MazeHedge::getNorthBoundary(){
	if (firstPoint.getX() == secondPoint.getX()){
		return MazeHedge (0, 
			Point(firstPoint.getX() - (int) (hedgeWidth / 2), firstPoint.getY()),
			Point(firstPoint.getX() + (int) (hedgeWidth / 2), firstPoint.getY()));
	}
	return MazeHedge(0,
		Point(firstPoint.getX(), firstPoint.getY() - (int) (hedgeWidth / 2)),
		Point(secondPoint.getX(), firstPoint.getY() - (int) (hedgeWidth / 2)));
}

MazeHedge MazeHedge::getSouthBoundary(){
	if (firstPoint.getX() == secondPoint.getX()){
		return MazeHedge (0, 
			Point(firstPoint.getX() - (int) (hedgeWidth / 2), secondPoint.getY()),
			Point(firstPoint.getX() + (int) (hedgeWidth / 2), secondPoint.getY()));
	}
	return MazeHedge (0, 
		Point(firstPoint.getX(), firstPoint.getY() + (int) (hedgeWidth / 2)),
		Point(secondPoint.getX(), firstPoint.getY() + (int) (hedgeWidth / 2)));
}

MazeHedge MazeHedge::getWestBoundary(){
	if (firstPoint.getX() == secondPoint.getX()){
		return MazeHedge (0, 
			Point(firstPoint.getX() - (int) (hedgeWidth / 2), firstPoint.getY()),
			Point(firstPoint.getX() - (int) (hedgeWidth / 2), secondPoint.getY()));
	}
	return MazeHedge (0, 
		Point(firstPoint.getX(), firstPoint.getY() - (int) (hedgeWidth / 2)),
		Point(firstPoint.getX(), firstPoint.getY() + (int) (hedgeWidth / 2)));
}

MazeHedge MazeHedge::getEastBoundary(){
	if (firstPoint.getX() == secondPoint.getX()){
		return MazeHedge (0, 
			Point(firstPoint.getX() + (int) (hedgeWidth / 2), firstPoint.getY()),
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
