#include "Vector2.h"

class MazeHedge{
private:
	Vector2 firstPoint;
	Vector2 secondPoint;
	int hedgeWidth;
	bool isVerticalLine();
	Vector2 MazeHedge::getWestHorizontalPoint(Vector2* vertPoint);
	Vector2 MazeHedge::getEastHorizontalPoint(Vector2* vertPoint);
	Vector2 MazeHedge::getNorthVerticalPoint(Vector2* horizPoint);
	Vector2 MazeHedge::getSouthVerticalPoint(Vector2* horizPoint);

public:
	MazeHedge(Vector2 first = ORIGIN, Vector2 second = ORIGIN, int width = 10);
	MazeHedge getNorthBoundary();
	MazeHedge getSouthBoundary();
	MazeHedge getWestBoundary();
	MazeHedge getEastBoundary();
	Vector2* getFirstPoint();
	Vector2* getSecondPoint();
	bool contains(Vector2* p);
	int getHedgeWidth();
};