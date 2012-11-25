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

	double dotProd = rayDirection.dot(surfaceNormal);

	if (dotProd > -EPSILON && EPSILON > dotProd){ // ray lies on the plane
		return false;
	}else{
		double t = (surfaceNormal.dot(topRight - rayOrigin)) / dotProd;

		if (t < EPSILON){
			return false;
		}

		Point3D intersection = rayOrigin + t * rayDirection;

		double x = intersection[0];
		double y = intersection[1];
		double z = intersection[2];

		bool inPlane = (EPSILON > z && z > -EPSILON &&
						 0.5 >= x && x >= -0.5 &&
						 0.5 >= y && y >= -0.5);

		if (inPlane
		&& (ray.intersection.none || (!ray.intersection.none && t < ray.intersection.t_value))){

			ray.intersection.none = false;
			ray.intersection.t_value = t;
			ray.intersection.point = modelToWorld * intersection;
			ray.intersection.normal = transNorm(worldToModel, surfaceNormal);
			return true;
		}else{
			return false;
		}
	}
}

struct QuadraticSolutions{
	double t1;
	double t2;
};

QuadraticSolutions solveQuadratic(double A, double B, double C){
	double discriminant = B * B - A * C;
	double t = -1;
	QuadraticSolutions solns;
	if (discriminant < EPSILON){ // no intersections
		solns.t1 = -1.0;
		solns.t2 = -1.0;
		return solns;
	} else if (discriminant < EPSILON){ // exactly one intersection point
		t = -B / A;
		if (t < EPSILON){
			solns.t1 = -1.0;
			solns.t2 = -1.0;
			return solns;
		}
	} else{ // two intersections
		double t1 = (-B + sqrt(discriminant)) / A;
		double t2 = (-B - sqrt(discriminant)) / A;

		if (t1 < EPSILON && t2 < EPSILON){ // behind camera
			solns.t1 = -1.0;
			solns.t2 = -1.0;
			return solns;
		}

		// take smallest positive t
		solns.t1 = std::min(t1, t2);
		solns.t2 = std::max(t1, t2);
		if (solns.t1 < EPSILON){
			solns.t1 = std::max(t1, t2);
			solns.t2 = std::min(t1, t2);
		}
	}
	return solns;
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

	double A = rayDirection.dot(rayDirection);
	double B = sphereOriginToRayOrigin.dot(rayDirection);
	double C = sphereOriginToRayOrigin.dot(sphereOriginToRayOrigin) - 1;

	double t = solveQuadratic(A, B, C).t1;
	if (t < EPSILON || (!ray.intersection.none && t > ray.intersection.t_value)){
		return false;
	}
	Point3D intersection = rayOrigin + t * rayDirection;
	Vector3D normal = intersection - sphereOrigin;
	normal.normalize();
	ray.intersection.none = false;
	ray.intersection.normal = transNorm(worldToModel, normal);
	ray.intersection.point = modelToWorld * intersection;
	ray.intersection.t_value = t;
	return true;
}

bool withinZBound(Point3D point, double bound){
	return point[2] < bound && point[2] > -bound;
}
bool withinCircle(Point3D point, double radius){
	return point[0] * point[0] + point[1] * point[1] <= radius * radius;
}

bool UnitCylinder::intersect( Ray3D& ray, const Matrix4x4& worldToModel,
		const Matrix4x4& modelToWorld ) {
	Vector3D rayDirection = worldToModel * ray.dir;
	Point3D rayOrigin = worldToModel * ray.origin;
	Point3D cylOrigin(0, 0, 0);

	rayDirection.normalize();

	Vector3D flatRayDirection = rayDirection;
	Point3D flatRayOrigin = rayOrigin;
	flatRayDirection[2] = 0.0;
	flatRayOrigin[2] = 0.0;
	Vector3D cylOriginToRayOrigin = flatRayOrigin - cylOrigin;

	// check round part of cylinder intersection
	double A = flatRayDirection.dot(flatRayDirection);
	double B = flatRayDirection.dot(cylOriginToRayOrigin);
	double C = cylOriginToRayOrigin.dot(cylOriginToRayOrigin) - 1;

	QuadraticSolutions solns = solveQuadratic(A, B, C);

	if (solns.t1 < EPSILON && solns.t2 < EPSILON)
		return false;

	Point3D intersection1 = rayOrigin + solns.t1 * rayDirection;
	Point3D intersection2 = intersection1;
	if (solns.t2 > EPSILON){
		intersection2 = rayOrigin + solns.t2 * rayDirection;
	}

	Point3D intersection;
	double halfCylHeight = CYLINDER_HEIGHT/2;
	double tSurface = -1;
	// figure out which point on the cylindrical surface we
	// intersected first
	if (withinZBound(intersection1, halfCylHeight)){
		intersection = intersection1;
		tSurface = solns.t1;
	} else if (withinZBound(intersection2, halfCylHeight)){
		intersection = intersection2;
		tSurface = solns.t2;
	}

	// check for plane intersections (top/bottom circles of cylinder)
	double tTopCircle = (halfCylHeight - rayOrigin[2]) / rayDirection[2];
	double tBottomCircle = (-halfCylHeight - rayOrigin[2]) / rayDirection[2];

	if (tTopCircle < EPSILON && tSurface < EPSILON && tBottomCircle < EPSILON)
		return false;

	Point3D topCircleIntersection = rayOrigin + tTopCircle * rayDirection;
	Point3D bottomCircleIntersection = rayOrigin + tBottomCircle * rayDirection;

	Point3D circleIntersection;
	Vector3D circleNormal;
	double tCircle = -1;
	// figure out which circle was intersected first
	if (withinCircle(topCircleIntersection, 1.0) && tTopCircle > EPSILON){
		if (withinCircle(bottomCircleIntersection, 1.0) && tBottomCircle > EPSILON && tBottomCircle < tTopCircle){
			tCircle = tBottomCircle;
			circleIntersection = bottomCircleIntersection;
			circleNormal = Vector3D(0, 0, -1.0);
		}else{
			tCircle = tTopCircle;
			circleIntersection = topCircleIntersection;
			circleNormal = Vector3D(0, 0, 1.0);
		}
	} else if (withinCircle(bottomCircleIntersection, 1.0) && tBottomCircle > EPSILON){
		tCircle = tBottomCircle;
		circleNormal = Vector3D(0, 0, -1.0);
		circleIntersection = bottomCircleIntersection;
	}

	if (tCircle < EPSILON && tSurface < EPSILON)
		return false;

	Vector3D normal;
	double t;
	// figure out if we intersected a circle or the cylindrical surface first
	if (tSurface < EPSILON || (tCircle < tSurface && tCircle > EPSILON)){
		t = tCircle;
		intersection = circleIntersection;
		normal = circleNormal;
	} else {
		t = tSurface;
		normal = Vector3D(intersection[0], intersection[1], 0.0);
	}

	if (!ray.intersection.none && t > ray.intersection.t_value)
		return false;

	normal.normalize();
	ray.intersection.none = false;
	ray.intersection.normal = transNorm(worldToModel, normal);
	ray.intersection.point = modelToWorld * intersection;
	ray.intersection.t_value = t;
	return true;
}
