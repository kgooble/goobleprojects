class Position{
private:
	int width;
	int height;
	int x;
	int y;

public:
	Position(int startX, int startY, int w, int h);
	bool overlaps(Position other);
};