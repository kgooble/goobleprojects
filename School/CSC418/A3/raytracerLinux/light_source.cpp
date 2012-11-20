/***********************************************************
     Starter code for Assignment 3

     This code was originally written by Jack Wang for
		    CSC418, SPRING 2005

		implements light_source.h

***********************************************************/

#include <cmath>
#include "light_source.h"

Colour PointLight::calculateDiffuse(Vector3D& normal, Vector3D& dirLight,
		Material*& mat) {
	double magnitude = normal.dot(dirLight);
	if (magnitude < 0)
		magnitude = 0.0;

	return magnitude * (_col_diffuse * mat->diffuse);
}
Colour PointLight::calculateAmbient(Material*& mat) {
	return  _col_ambient * mat->ambient;
}

Colour PointLight::calculateSpecular(Vector3D normal, Vector3D dirLight,
		Vector3D normalizedRay, Material* mat) {
	Vector3D h = normalizedRay + dirLight;
	h.normalize();
	double delta = normal.dot(h);
	if (delta < 0)
		delta = 0.0;
	delta = pow(delta, mat->specular_exp);

	return delta * (_col_specular * mat->specular);
}
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
	Colour diffuse = calculateDiffuse(normal, dirLight, mat);

	// AMBIENT CALCULATIONS
	Colour ambient = calculateAmbient(mat);

	// SPECULAR CALCULATIONS
	Colour specular = calculateSpecular(normal, dirLight, normalizedRay, mat);

	ray.col = diffuse + ambient + specular;
	ray.col.clamp();
}

