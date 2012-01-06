#include "Point.h"

class MazeHedge{
private:
	Point firstPoint;
	Point secondPoint;
	int hedgeWidth;
	bool isVerticalLine();
	Point MazeHedge::getWestHorizontalPoint(Point* vertPoint);
	Point MazeHedge::getEastHorizontalPoint(Point* vertPoint);
	Point MazeHedge::getNorthVerticalPoint(Point* horizPoint);
	Point MazeHedge::getSouthVerticalPoint(Point* horizPoint);

public:
	MazeHedge(Point first = ORIGIN, Point second = ORIGIN, int width = 10);
	MazeHedge getNorthBoundary();
	MazeHedge getSouthBoundary();
	MazeHedge getWestBoundary();
	MazeHedge getEastBoundary();
	Point* getFirstPoint();
	Point* getSecondPoint();
	bool contains(Point* p);
	int getHedgeWidth();
};