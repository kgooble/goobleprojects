#include "BorderHedges.h"

const int DEFAULT_MAZE_WIDTH = 500;
const int DEFAULT_MAZE_HEIGHT = 500;

class Maze{
private:
	std::vector<MazeHedge> mazeHedges;
	int mazeWidth;
	int mazeHeight;

public:
	Maze(std::vector<MazeHedge> hedges, int width = DEFAULT_MAZE_WIDTH, int height = DEFAULT_MAZE_HEIGHT);
	bool isValidLocation(Vector2* p);
};

