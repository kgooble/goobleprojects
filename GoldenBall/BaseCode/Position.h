#ifndef MOVEMENT_POSITION_H
#define MOVEMENT_POSITION_H
#include "Vector2.h"

class Position{
private:
	int width;
	int height;
	Vector2 topLeft;

public:
	Position(Vector2 tl = Vector2(0, 0), int w = 0, int h = 0);
	bool overlaps(Position other);
	int getX() const;
	int getY() const;
	int getCenterX();
	int getCenterY();
	int getEndX() const;
	int getEndY() const;
	int getWidth() const;
	int getHeight() const;
};

bool operator==(const Position& lhs, const Position& rhs);
bool operator!=(const Position& lhs, const Position& rhs);

#endif