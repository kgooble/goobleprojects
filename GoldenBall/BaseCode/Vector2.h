#ifndef MAZES_VECTOR2_H
#define MAZES_VECTOR2_H
class Vector2{
private:
	int x;
	int y;

public:
	Vector2(int X = 0, int Y = 0);
	int getX() const;
	int getY() const;
};

bool operator==(const Vector2& lhs, const Vector2& rhs);

const Vector2 ORIGIN;
#endif