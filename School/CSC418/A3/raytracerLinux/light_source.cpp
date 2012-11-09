/***********************************************************
     Starter code for Assignment 3

     This code was originally written by Jack Wang for
		    CSC418, SPRING 2005

		implements light_source.h

***********************************************************/

#include <cmath>
#include "light_source.h"

void PointLight::shade( Ray3D& ray ) {
	// TODO: implement this function to fill in values for ray.col 
	// using phong shading.  Make sure your vectors are normalized, and
	// clamp colour values to 1.0.
	//
	// It is assumed at this point that the intersection information in ray 
	// is available.  So be sure that traverseScene() is called on the ray 
	// before this function.  

	/*
	 * SK:
	 * how do we calculate the normal to the surface?
	 * we have access to:
	 *   -the different shading colours;
	 *   -the "position" of the point light;
	 *   -the ray itself
	 *     -we know the ray intersection... which has a normal.
	 *
	 * ...is this right?
	 */

	Vector3D normal = ray.intersection.normal;
	normal.normalize();

	Vector3D dirLight = _pos - ray.intersection.point;
	dirLight.normalize();

	Vector3D normalizedRay = ray.dir;
	normalizedRay.normalize();

	Material* mat = ray.intersection.mat;

	// DIFFUSE CALCULATIONS

	double magnitude = normal.dot(dirLight);
	if (magnitude < 0)
		magnitude = 0;

	Colour diffuse = magnitude * (_col_diffuse * mat->diffuse);

	// AMBIENT CALCULATIONS
	Colour ambient = _col_ambient * mat->ambient;

	// SPECULAR CALCULATIONS
	Vector3D reflectionDir = 2.0 * (normal.dot(dirLight)) * normal - dirLight;
	reflectionDir.normalize();
	double delta = reflectionDir.dot(normalizedRay) - 1;
	if (delta == 0)
		delta = 1.0;

	Colour specular = delta * (_col_specular * mat->specular);

	ray.col = diffuse + ambient + specular;
	ray.col.clamp();
}

