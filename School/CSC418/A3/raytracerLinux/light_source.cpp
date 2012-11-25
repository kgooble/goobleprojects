/***********************************************************
     Starter code for Assignment 3

     This code was originally written by Jack Wang for
		    CSC418, SPRING 2005

		implements light_source.h

***********************************************************/

#include <cmath>
#include "light_source.h"
Colour calculateDiffuse(Colour _col_diffuse, Vector3D& normal, Vector3D& dirLight,
		Material*& mat) {
	return std::max(normal.dot(dirLight), 0.0) * (_col_diffuse * mat->diffuse);
}
Colour calculateAmbient(Colour _col_ambient, Material*& mat) {
	return _col_ambient * mat->ambient;
}

Colour calculateSpecular(Colour _col_specular, Vector3D& normal, Vector3D& lightDir,
		Vector3D& rayDir, Material* mat) {
	Vector3D r = 2 * (normal.dot(lightDir)) * normal - lightDir;
	r.normalize();
	double dotProd = rayDir.dot(r);
	return pow(std::max(0.0, -dotProd), mat->specular_exp) * (_col_specular * mat->specular);
}
void PointLight::shade( Ray3D& ray, std::vector<Ray3D> shadowRays ) {
	// TODO: implement this function to fill in values for ray.col 
	// using phong shading.  Make sure your vectors are normalized, and
	// clamp colour values to 1.0.
	//
	// It is assumed at this point that the intersection information in ray 
	// is available.  So be sure that traverseScene() is called on the ray 
	// before this function.  
	Material* mat = ray.intersection.mat;
	ray.col = calculateAmbient(_col_ambient, mat);
	Ray3D shadowRay = shadowRays[0];

	if (shadowRay.intersection.none){
		Vector3D normal = ray.intersection.normal;
		normal.normalize();

		Vector3D lightDir = get_position() - ray.intersection.point;
		lightDir.normalize();

		Vector3D rayDir = ray.dir;
		rayDir.normalize();

		Colour diffuse = calculateDiffuse(_col_diffuse, normal, lightDir, mat);
		Colour specular = calculateSpecular(_col_specular, normal, lightDir, rayDir, mat);
		ray.col = ray.col + diffuse + specular;
	}
	ray.col.clamp();
}

Vector3D AreaLight::calculateNormal(){
	Vector3D line1 = _topLeft - _topRight;
	Vector3D line2 = _topLeft - _bottomLeft;
	Vector3D normal = line1.cross(line2);
	normal.normalize();
	return normal;
}

std::vector<Point3D> PointLight::generateSamples(){
	std::vector<Point3D> samples;
	samples.push_back(get_position());
	return samples;
}

std::vector<Point3D> AreaLight::generateSamples(){
	double startX = _topRight[0];
	double numX = _topRight[0] - _topLeft[0];

	double startY = _topLeft[1];
	double numY = _bottomRight[1] - _topRight[1];

	std::vector<Point3D> samples;
	for (int i = 0; i < SHADOW_SAMPLE_SIZE; i++){
		double x = rand() % (int)numX + startX;
		double y = rand() % (int)numY + startY;

		double z;
		if (abs(_normal[1]) < 0.0001)
			z = _topRight[2];
		else
			z = (-_normal[0] * (x - _topLeft[0])  - _normal[1] * (y - _topLeft[1])) / _normal[1] + _topLeft[2];
		samples.push_back(Point3D(x, y, z));
	}
	return samples;
}

void AreaLight::shade( Ray3D& ray, std::vector<Ray3D> shadowRays ){
	Material* mat = ray.intersection.mat;

	Vector3D normal = ray.intersection.normal;
	normal.normalize();

	Vector3D lightDir = get_position() - ray.intersection.point;
	lightDir.normalize();

	Vector3D rayDir = ray.dir;
	rayDir.normalize();

	Colour ambient = calculateAmbient(_col_ambient, mat);
	Colour diffuse = calculateDiffuse(_col_diffuse, normal, lightDir, mat);
	Colour specular = calculateSpecular(_col_specular, normal, lightDir, rayDir, mat);

	Colour col = calculateAmbient(_col_ambient, mat);

	if (shadowRays.empty()){
		col = col + diffuse + specular;
	} else{
		for (unsigned int i = 0; i < shadowRays.size(); i++){
			col = col + ambient;
			Ray3D shadowRay = shadowRays[i];
			if (shadowRay.intersection.none){
				col = col + diffuse + specular;
			}
		}
		col = (1.0 / shadowRays.size()) * col;
	}
	col.clamp();

	ray.col = col;
}
