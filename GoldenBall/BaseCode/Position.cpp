#include "Position.h"

Position::Position(Point tl, int w, int h){
	topLeft = tl;
	width = w;
	height = h;
}

int Position::getX() const{
	return topLeft.getX();
}

int Position::getY() const{
	return topLeft.getY();
}

int Position::getCenterX(){
	return (getX() + getEndX()) / 2;
}

int Position::getCenterY(){
	return (getY() + getEndY()) / 2;
}

int Position::getEndX() const{
	return getX() + width;
}

int Position::getEndY() const{
	return getY() + height;
}

int Position::getWidth() const{
	return width;
}

int Position::getHeight() const{
	return height;
}

bool Position::overlaps(Position other){
	return (((getY() <= other.getY() && other.getY() <= getEndY()) || (getY() <= other.getEndY() && other.getEndY() <= getEndY()))
		||  ((other.getY() <= getY() && getY() <= other.getEndY()) || (other.getY() <= getEndY() && getEndY() <= other.getEndY())))
		&& (((getX() <= other.getX() && other.getX() <= getEndX()) || (getX() <= other.getEndX() && other.getEndX() <= getEndX()))
		||  ((other.getX() <= getX() && getX() <= other.getEndX()) || (other.getX() <= getEndX() && getEndX() <= other.getEndX())));
}

bool operator ==(const Position& lhs, const Position& rhs){
	return lhs.getX() == rhs.getX() && lhs.getY() == rhs.getY()
		&& lhs.getEndX() == rhs.getEndX() && lhs.getEndY() == rhs.getEndY();

}

bool operator !=(const Position& lhs, const Position& rhs){
	return !(lhs == rhs);
}
