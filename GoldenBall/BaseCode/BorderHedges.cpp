#include "BorderHedges.h"

BorderHedges::BorderHedges(int mWidth, int mHeight, int hWidth){
	mazeWidth = mWidth;
	mazeHeight = mHeight;
	hedgeWidth = hWidth;
}

MazeHedge BorderHedges::getNorthBorder(){
	return MazeHedge(
		Point(0, (int) (hedgeWidth/2)), 
		Point(mazeWidth, (int) (hedgeWidth/2)), 
		hedgeWidth);
}

MazeHedge BorderHedges::getSouthBorder(){
	return MazeHedge(
		Point(0, mazeHeight - (int) (hedgeWidth/2)), 
		Point(mazeWidth, mazeHeight - (int) (hedgeWidth/2)), 
		hedgeWidth);
}

MazeHedge BorderHedges::getWestBorder(){
	return MazeHedge(
		Point((int) (hedgeWidth/2), 0), 
		Point((int) (hedgeWidth/2), mazeHeight), 
		hedgeWidth);
}

MazeHedge BorderHedges::getEastBorder(){
	return MazeHedge(
		Point(mazeWidth - (int) (hedgeWidth/2), 0), 
		Point(mazeWidth - (int) (hedgeWidth/2), mazeHeight), 
		hedgeWidth);
}

void BorderHedges::addBorderHedgesTo(std::vector<MazeHedge>* hedges){
	hedges->push_back(getNorthBorder());
	hedges->push_back(getSouthBorder());
	hedges->push_back(getEastBorder());
	hedges->push_back(getWestBorder());
}