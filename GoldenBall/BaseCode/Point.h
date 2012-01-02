class Point{
private:
	int x;
	int y;

public:
	Point(int X = 0, int Y = 0);
	int getX() const;
	int getY() const;
};

bool operator==(const Point& lhs, const Point& rhs);

const Point ORIGIN;
