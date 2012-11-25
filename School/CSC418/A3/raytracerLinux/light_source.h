/***********************************************************
     Starter code for Assignment 3

     This code was originally written by Jack Wang for
		    CSC418, SPRING 2005

		light source classes

***********************************************************/

#include <vector>
#include "util.h"

// Base class for a light source.  You could define different types
// of lights here, but point light is sufficient for most scenes you
// might want to render.  Different light sources shade the ray 
// differently.
class LightSource {
public:
	virtual void shade( Ray3D&,  std::vector<Ray3D> ) = 0;
	virtual Point3D get_position() const = 0; 
	virtual std::vector<Point3D> generateSamples() = 0;
};

// A point light is defined by its position in world space and its
// colour.
class PointLight : public LightSource {
public:
	PointLight( Point3D pos, Colour col ) : _pos(pos), _col_ambient(col), 
	_col_diffuse(col), _col_specular(col) {}
	PointLight( Point3D pos, Colour ambient, Colour diffuse, Colour specular ) 
	: _pos(pos), _col_ambient(ambient), _col_diffuse(diffuse), 
	_col_specular(specular) {}
	void shade( Ray3D& ray,  std::vector<Ray3D> shadowRays );
	Point3D get_position() const { return _pos; }
	std::vector<Point3D> generateSamples();
	
private:
	Point3D _pos;
	Colour _col_ambient;
	Colour _col_diffuse; 
	Colour _col_specular; 

};

const int SHADOW_SAMPLE_SIZE = 16;

class AreaLight : public LightSource {
public:
	AreaLight(  Point3D topLeft, Point3D topRight,
				Point3D bottomLeft, Point3D bottomRight, Colour col ):
	_topLeft(topLeft), _topRight(topRight), _bottomLeft(bottomLeft), _bottomRight(bottomRight),
		_col_ambient(col), _col_diffuse(col), _col_specular(col) {
		_normal = calculateNormal();
	}
	AreaLight(  Point3D topLeft, Point3D topRight,
				Point3D bottomLeft, Point3D bottomRight, Colour ambient,
				Colour diffuse, Colour specular ):
	_topLeft(topLeft), _topRight(topRight), _bottomLeft(bottomLeft), _bottomRight(bottomRight),
		_col_ambient(ambient), _col_diffuse(diffuse), _col_specular(specular) {
		_normal = calculateNormal();
	}

	void shade( Ray3D& ray, std::vector<Ray3D> shadowRays );
	std::vector<Point3D> generateSamples();
	Point3D get_position() const {return Point3D(0,0,0);}

private:
	Point3D _topLeft;
	Point3D _topRight;
	Point3D _bottomLeft;
	Point3D _bottomRight;
	Vector3D _normal;
	Colour _col_ambient;
	Colour _col_diffuse;
	Colour _col_specular;

	Vector3D calculateNormal();
};
