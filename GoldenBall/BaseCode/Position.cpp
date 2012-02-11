#include "Position.h"

Position::Position(int startX, int startY, int w, int h){
	x = startX;
	y = startY;
	width = w;
	height = h;
}

bool Position::overlaps(Position other){
	return ((x <= other.x && other.x <= x + width) 
			&& (y <= other.y && other.y <= y + height)) 
			|| ((other.x <= x && x <= other.x + other.width) 
			&& (other.y <= y && y <= other.y + other.height));
}