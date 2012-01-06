#include <vector>
#include "MazeHedge.h"

class BorderHedges{
private:
	int mazeWidth;
	int mazeHeight;
	int hedgeWidth;
	MazeHedge getNorthBorder();
	MazeHedge getSouthBorder();
	MazeHedge getWestBorder();
	MazeHedge getEastBorder();

public:
	BorderHedges(int mWidth, int mHeight, int hWidth);
	void addBorderHedgesTo(std::vector<MazeHedge>* hedges);
};