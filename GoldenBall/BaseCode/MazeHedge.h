class Point{
private:
	int x;
	int y;

public:
	Point(int X = 0, int Y = 0);
	int getX() const;
	int getY() const;
};

bool operator==(const Point& lhs, const Point& rhs);

const Point ORIGIN;


class MazeHedge{
private:
	Point firstPoint;
	Point secondPoint;
	int hedgeWidth;

public:
	MazeHedge(int width = 10, Point first = ORIGIN, Point second = ORIGIN);
	MazeHedge getNorthBoundary();
	MazeHedge getSouthBoundary();
	MazeHedge getWestBoundary();
	MazeHedge getEastBoundary();
	Point* getFirstPoint();
	Point* getSecondPoint();

};