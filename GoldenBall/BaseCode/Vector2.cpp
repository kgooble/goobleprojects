#include "Vector2.h"

Vector2::Vector2(int X, int Y){
	x = X;
	y = Y;
}

int Vector2::getX() const{
	return x;
}

int Vector2::getY() const{
	return y;
}

bool operator ==(const Vector2& lhs, const Vector2& rhs){
	return lhs.getX() == rhs.getX();
}