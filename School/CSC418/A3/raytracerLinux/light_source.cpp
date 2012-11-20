/***********************************************************
     Starter code for Assignment 3

     This code was originally written by Jack Wang for
		    CSC418, SPRING 2005

		implements light_source.h

***********************************************************/

#include <cmath>
#include <algorithm>
#include "light_source.h"

Colour PointLight::calculateDiffuse(Vector3D& normal, Vector3D& dirLight,
		Material*& mat) {
	return std::max(normal.dot(dirLight), 0.0) * (_col_diffuse * mat->diffuse);
}
Colour PointLight::calculateAmbient(Material*& mat) {
	return _col_ambient * mat->ambient;
}

Colour PointLight::calculateSpecular(Vector3D& normal, Vector3D& lightDir,
		Vector3D& rayDir, Material* mat) {
	Vector3D r = 2 * (normal.dot(lightDir)) * normal - lightDir;
	r.normalize();
	return pow(std::max(0.0, rayDir.dot(r)), mat->specular_exp) * (_col_specular * mat->specular);
}
void PointLight::shade( Ray3D& ray ) {
	// TODO: implement this function to fill in values for ray.col 
	// using phong shading.  Make sure your vectors are normalized, and
	// clamp colour values to 1.0.
	//
	// It is assumed at this point that the intersection information in ray 
	// is available.  So be sure that traverseScene() is called on the ray 
	// before this function.  

	Vector3D normal = ray.intersection.normal;
	normal.normalize();

	Vector3D lightDir = _pos - ray.intersection.point;
	lightDir.normalize();

	Vector3D rayDir = ray.dir;
	rayDir.normalize();

	Material* mat = ray.intersection.mat;

	Colour diffuse = calculateDiffuse(normal, lightDir, mat);
	Colour ambient = calculateAmbient(mat);
	Colour specular = calculateSpecular(normal, lightDir, rayDir, mat);

	ray.col = ambient + diffuse + specular;
	ray.col.clamp();
}

