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

Point MazeHedge::getNorthEastHorizontalPoint(){
	return Point(firstPoint.getX() + (int) (hedgeWidth / 2), firstPoint.getY());
}

Point MazeHedge::getSouthEastHorizontalPoint(){
	return Point(firstPoint.getX() + (int) (hedgeWidth / 2), secondPoint.getY());
}

MazeHedge MazeHedge::getNorthBoundary(){
	if (isVerticalLine()){
		return MazeHedge (0, getWestHorizontalPoint(&firstPoint),
			getNorthEastHorizontalPoint());
	}
	return MazeHedge(0,
		Point(firstPoint.getX(), firstPoint.getY() - (int) (hedgeWidth / 2)),
		Point(secondPoint.getX(), firstPoint.getY() - (int) (hedgeWidth / 2)));
}

MazeHedge MazeHedge::getSouthBoundary(){
	if (isVerticalLine()){
		return MazeHedge (0, getWestHorizontalPoint(&secondPoint),
			getSouthEastHorizontalPoint());
	}
	return MazeHedge (0, 
		Point(firstPoint.getX(), firstPoint.getY() + (int) (hedgeWidth / 2)),
		Point(secondPoint.getX(), firstPoint.getY() + (int) (hedgeWidth / 2)));
}

MazeHedge MazeHedge::getWestBoundary(){
	if (isVerticalLine()){
		return MazeHedge (0, getWestHorizontalPoint(&firstPoint),
			getWestHorizontalPoint(&secondPoint));
	}
	return MazeHedge (0, 
		Point(firstPoint.getX(), firstPoint.getY() - (int) (hedgeWidth / 2)),
		Point(firstPoint.getX(), firstPoint.getY() + (int) (hedgeWidth / 2)));
}

MazeHedge MazeHedge::getEastBoundary(){
	if (isVerticalLine()){
		return MazeHedge (0, 
			getNorthEastHorizontalPoint(),
			getSouthEastHorizontalPoint());
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
