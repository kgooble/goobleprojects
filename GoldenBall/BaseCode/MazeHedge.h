#include "Point.h"

class MazeHedge{
private:
	Point firstPoint;
	Point secondPoint;
	int hedgeWidth;
	bool isHorizontalLine();
	Point getNorthWestHorizontalPoint();
	Point getNorthEastHorizontalPoint();

public:
	MazeHedge(int width = 10, Point first = ORIGIN, Point second = ORIGIN);
	MazeHedge getNorthBoundary();
	MazeHedge getSouthBoundary();
	MazeHedge getWestBoundary();
	MazeHedge getEastBoundary();
	Point* getFirstPoint();
	Point* getSecondPoint();
};