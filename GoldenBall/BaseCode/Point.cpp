#include "Point.h"

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