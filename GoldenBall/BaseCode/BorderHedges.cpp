#include "BorderHedges.h"

BorderHedges::BorderHedges(int mWidth, int mHeight, int hWidth){
	mazeWidth = mWidth;
	mazeHeight = mHeight;
	hedgeWidth = hWidth;
}

MazeHedge BorderHedges::getNorthBorder(){
	return MazeHedge(
		Vector2(0, (int) (hedgeWidth/2)), 
		Vector2(mazeWidth, (int) (hedgeWidth/2)), 
		hedgeWidth);
}

MazeHedge BorderHedges::getSouthBorder(){
	return MazeHedge(
		Vector2(0, mazeHeight - (int) (hedgeWidth/2)), 
		Vector2(mazeWidth, mazeHeight - (int) (hedgeWidth/2)), 
		hedgeWidth);
}

MazeHedge BorderHedges::getWestBorder(){
	return MazeHedge(
		Vector2((int) (hedgeWidth/2), 0), 
		Vector2((int) (hedgeWidth/2), mazeHeight), 
		hedgeWidth);
}

MazeHedge BorderHedges::getEastBorder(){
	return MazeHedge(
		Vector2(mazeWidth - (int) (hedgeWidth/2), 0), 
		Vector2(mazeWidth - (int) (hedgeWidth/2), mazeHeight), 
		hedgeWidth);
}

void BorderHedges::addBorderHedgesTo(std::vector<MazeHedge>* hedges){
	hedges->push_back(getNorthBorder());
	hedges->push_back(getSouthBorder());
	hedges->push_back(getEastBorder());
	hedges->push_back(getWestBorder());
}