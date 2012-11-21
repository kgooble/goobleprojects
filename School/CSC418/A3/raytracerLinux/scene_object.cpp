/***********************************************************
     Starter code for Assignment 3

     This code was originally written by Jack Wang for
		    CSC418, SPRING 2005

		implements scene_object.h

***********************************************************/

#include <cmath>
#include <iostream>
#include <algorithm>
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

	double dotProd = rayDirection.dot(surfaceNormal);

	if (dotProd > -0.0001 && 0.0001 > dotProd){ // ray lies on the plane
		return false;
	}else{
		double t = (surfaceNormal.dot(topRight - rayOrigin)) / dotProd;

		if (t < 0){
			return false;
		}

		Point3D intersection = rayOrigin + t * rayDirection;

		double x = intersection[0];
		double y = intersection[1];
		double z = intersection[2];

		bool inPlane = (0.0001 > z && z > -0.0001 &&
									 0.5 >= x && x >= -0.5 &&
									 0.5 >= y && y >= -0.5);

		if (((!ray.intersection.none && t < ray.intersection.t_value) || ray.intersection.none) && inPlane){
			ray.intersection.none = false;
			ray.intersection.t_value = t;
			ray.intersection.point = modelToWorld * intersection;
			ray.intersection.normal = worldToModel.transpose() * surfaceNormal;
			return true;
		}else{
			return false;
		}
	}
}

void UnitSphere::printPoint(Ray3D& ray) {
	std::cout << "intersection: " << ray.intersection.point[0] << ", "
			<< ray.intersection.point[1] << ", " << ray.intersection.point[2]
			<< std::endl;
}

void printVector(Vector3D& vector){
	std::cout << "vector: " << vector[0] << ", "
				<< vector[1] << ", " << vector[2]
				<< std::endl;
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

	rayDirection.normalize();
	sphereOriginToRayOrigin.normalize();

	double A = rayDirection.dot(rayDirection);
	double B = sphereOriginToRayOrigin.dot(rayDirection);
	double C = sphereOriginToRayOrigin.dot(sphereOriginToRayOrigin) - 1;
	double discriminant = B * B - A * C;
	double epsilon = 0.0001;
	if (discriminant < 0){ // no intersections
		ray.intersection.none = ray.intersection.none && true;
	} else if (discriminant < epsilon){ // exactly one intersection point
		double t = -B / A;
		if (t < 0 || (!ray.intersection.none && t > ray.intersection.t_value)){
			// either intersection is behind camera
			// or the ray has intersected something earlier
			return false;
		}
		Point3D intersection = rayOrigin + t * rayDirection;
		Vector3D normal = intersection - sphereOrigin;
		ray.intersection.none = false;
		ray.intersection.normal = worldToModel.transpose() * normal;
		ray.intersection.point = modelToWorld * intersection;
		ray.intersection.t_value = t;
	} else{ // two intersections
		double t1 = (-B + sqrt(discriminant)) / A;
		double t2 = (-B - sqrt(discriminant)) / A;

		if (t1 < 0 && t2 < 0) // behind camera
			return false;

		// take smaller t, this occurs "earlier" on the ray
		// so it's on the front of the surface
		double t = std::min(t1, t2);
		if (t < 0)
			t = std::max(t1, t2);

		if (!ray.intersection.none && t > ray.intersection.t_value){
			// earlier intersection occurred
			return false;
		}

		Point3D intersection = rayOrigin + t * rayDirection;
		Vector3D normal = intersection - sphereOrigin;

		ray.intersection.none = false;
		ray.intersection.normal = worldToModel.transpose() * normal;
		ray.intersection.point = modelToWorld * intersection;
		ray.intersection.t_value = t;
	}
	return !ray.intersection.none;
}

