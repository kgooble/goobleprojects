#include "Point.h"

class Position{
private:
	int width;
	int height;
	Point topLeft;

public:
	Position(Point tl = Point(0, 0), int w = 0, int h = 0);
	bool overlaps(Position other);
	int getX() const;
	int getY() const;
	int getEndX() const;
	int getEndY() const;
	int getWidth() const;
	int getHeight() const;
};

bool operator==(const Position& lhs, const Position& rhs);
bool operator!=(const Position& lhs, const Position& rhs);