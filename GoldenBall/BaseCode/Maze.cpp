#include "Maze.h"

Maze::Maze(std::vector<MazeHedge> hedges, int width, int height){
	BorderHedges(width, height, hedges[0].getHedgeWidth())
		.addBorderHedgesTo(&hedges);
	mazeHedges = hedges;
	mazeWidth = width;
	mazeHeight = height;
}

bool Maze::isValidLocation(Point* p){
	std::vector<MazeHedge>::iterator hedge;
	for (hedge = mazeHedges.begin(); hedge != mazeHedges.end(); hedge++){
		if (hedge->contains(p)){
			return false;
		}
	}
	return true;
}