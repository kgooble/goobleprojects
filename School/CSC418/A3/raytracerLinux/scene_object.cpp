/***********************************************************
     Starter code for Assignment 3

     This code was originally written by Jack Wang for
		    CSC418, SPRING 2005

		implements scene_object.h

***********************************************************/

#include <cmath>
#include <iostream>
#include "scene_object.h"

bool UnitSquare::intersect( Ray3D& ray, const Matrix4x4& worldToModel,
		const Matrix4x4& modelToWorld ) {
	// TODO: implement intersection code for UnitSquare, which is
	// defined on the xy-plane, with vertices (0.5, 0.5, 0), 
	// (-0.5, 0.5, 0), (-0.5, -0.5, 0), (0.5, -0.5, 0), and normal
	// (0, 0, 1).
	//
	// Your goal here is to fill ray.intersection with correct values
	// should an intersection occur.  This includes intersection.point, 
	// intersection.normal, intersection.none, intersection.t_value.   
	//
	// HINT: Remember to first transform the ray into object space  
	// to simplify the intersection test.
	Vector3D rayDirection = worldToModel * ray.dir;
	Point3D rayOrigin = worldToModel * ray.origin;
	Vector3D surfaceNormal(0.0, 0.0, 1.0);

	Point3D topRight(0.5, 0.5, 0.0);
	Point3D topLeft(-0.5, 0.5, 0.0);
	Point3D bottomLeft(-0.5, -0.5, 0.0);
	Point3D bottomRight(0.5, -0.5, 0.0);
	Point3D points[] = {topRight, topLeft, bottomLeft, bottomRight};
	int numPoints = 4;

	double dotProd = rayDirection.dot(surfaceNormal);

	if (dotProd == 0){
		// ray is parallel to plane
		//TODO: check if ray lies along plane
		// in that case, what is the intersection? many points...

		ray.intersection.none = true;
		return false;
	}else{
		// this isn't general at all
		double t = (surfaceNormal.dot(topRight - rayOrigin)) / (surfaceNormal.dot(rayDirection));
		Point3D intersection = rayOrigin + t * rayDirection;

		double x = intersection[0];
		double y = intersection[1];
		double z = intersection[2];

		if (0.0001 > z && z > -0.0001 &&
			0.5 > x && x > -0.5 &&
			0.5 > y && y > -0.5){
			ray.intersection.none = false;
			ray.intersection.t_value = t;
			ray.intersection.point = modelToWorld * intersection;
			ray.intersection.normal = modelToWorld * surfaceNormal;
		}else{
			ray.intersection.none = true;
		}

//		// check if intersection is actually within the plane
//		// by creating a ray on the plane starting at the intersection point
//		// extending in any direction; count the number of intersections
//		// with the sides of the polygon
//		int prevPoint = numPoints - 1;
//		for (int i = 0; i < numPoints; i++){
//			int nextPoint = i == numPoints - 1? 0 : i + 1;
//			Vector3D line = points[nextPoint] - points[i];
//
//		}
//
//		ray.intersection.none = false;
//		ray.intersection.point = modelToWorld * intersection;
//		ray.intersection.normal = modelToWorld * surfaceNormal;
//		ray.intersection.t_value = t;

	}

	return !ray.intersection.none;
}

bool UnitSphere::intersect( Ray3D& ray, const Matrix4x4& worldToModel,
		const Matrix4x4& modelToWorld ) {
	// TODO: implement intersection code for UnitSphere, which is centred 
	// on the origin.  
	//
	// Your goal here is to fill ray.intersection with correct values
	// should an intersection occur.  This includes intersection.point, 
	// intersection.normal, intersection.none, intersection.t_value.   
	//
	// HINT: Remember to first transform the ray into object space  
	// to simplify the intersection test.
	Vector3D rayDirection = worldToModel * ray.dir;
	Point3D rayOrigin = worldToModel * ray.origin;
	Point3D sphereOrigin(0, 0, 0);
	Vector3D sphereOriginToRayOrigin = rayOrigin - sphereOrigin;
	double radius = 1.0;

	double A = rayDirection.dot(rayDirection);
	double B = sphereOriginToRayOrigin.dot(rayDirection);
	double C = sphereOriginToRayOrigin.dot(sphereOriginToRayOrigin) - radius * radius;
	double discriminant = B * B - A * C;
	double epsilon = 0.0001;
	if (discriminant < -epsilon){
		ray.intersection.none = true;
	} else if (discriminant < epsilon && discriminant > -epsilon){
		double t = -B / A;
		if (t < 0){
			ray.intersection.none = true;
		}else{
			std::cout<< "Intersection occurred!\n";
			ray.intersection.none = false;
			Point3D intersection = rayOrigin + t * rayDirection;
			Vector3D normal = (1 / radius) * (intersection - sphereOrigin);

			ray.intersection.normal = modelToWorld * normal;
			ray.intersection.point = modelToWorld * intersection;
			ray.intersection.t_value = t;
		}
	} else{
		ray.intersection.none = false;
		double t1 = (-B + discriminant) / A;
		double t2 = (-B - discriminant) / A;

		// take smaller t, this occurs "earlier" on the ray
		// so it's on the front of the surface
		double t = 0;
		if (t1 > t2 && t2 > 0){
			t = t2;
		}else if (t2 > t1 && t1 > 0){
			t = t1;
		}else{
			ray.intersection.none = true;
			return false;
		}
		std::cout<< "Intersection occurred!\n";

		Point3D intersection = rayOrigin + t * rayDirection;
		Vector3D normal = (1 / radius) * (intersection - sphereOrigin);

		ray.intersection.normal = modelToWorld * normal;
		ray.intersection.point = modelToWorld * intersection;
		ray.intersection.t_value = t;
	}

	return !ray.intersection.none;
}

