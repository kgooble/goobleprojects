/***********************************************************
     Starter code for Assignment 3

     This code was originally written by Jack Wang for
		    CSC418, SPRING 2005

		Implementations of functions in raytracer.h, 
		and the main function which specifies the 
		scene to be rendered.	

***********************************************************/


#include "raytracer.h"
#include "bmp_io.h"
#include <cmath>
#include <iostream>

Raytracer::Raytracer() : _lightSource(NULL) {
	_root = new SceneDagNode();
}

Raytracer::~Raytracer() {
	delete _root;
}

SceneDagNode* Raytracer::addObject( SceneDagNode* parent, 
		SceneObject* obj, Material* mat, Medium* medium ) {
	SceneDagNode* node = new SceneDagNode( obj, mat, medium );
	node->parent = parent;
	node->next = NULL;
	node->child = NULL;
	
	// Add the object to the parent's child list, this means
	// whatever transformation applied to the parent will also
	// be applied to the child.
	if (parent->child == NULL) {
		parent->child = node;
	}
	else {
		parent = parent->child;
		while (parent->next != NULL) {
			parent = parent->next;
		}
		parent->next = node;
	}
	
	return node;;
}

LightListNode* Raytracer::addLightSource( LightSource* light ) {
	LightListNode* tmp = _lightSource;
	_lightSource = new LightListNode( light, tmp );
	return _lightSource;
}

void Raytracer::rotate( SceneDagNode* node, char axis, double angle ) {
	Matrix4x4 rotation;
	double toRadian = 2*M_PI/360.0;
	int i;
	
	for (i = 0; i < 2; i++) {
		switch(axis) {
			case 'x':
				rotation[0][0] = 1;
				rotation[1][1] = cos(angle*toRadian);
				rotation[1][2] = -sin(angle*toRadian);
				rotation[2][1] = sin(angle*toRadian);
				rotation[2][2] = cos(angle*toRadian);
				rotation[3][3] = 1;
			break;
			case 'y':
				rotation[0][0] = cos(angle*toRadian);
				rotation[0][2] = sin(angle*toRadian);
				rotation[1][1] = 1;
				rotation[2][0] = -sin(angle*toRadian);
				rotation[2][2] = cos(angle*toRadian);
				rotation[3][3] = 1;
			break;
			case 'z':
				rotation[0][0] = cos(angle*toRadian);
				rotation[0][1] = -sin(angle*toRadian);
				rotation[1][0] = sin(angle*toRadian);
				rotation[1][1] = cos(angle*toRadian);
				rotation[2][2] = 1;
				rotation[3][3] = 1;
			break;
		}
		if (i == 0) {
		    node->trans = node->trans*rotation; 	
			angle = -angle;
		} 
		else {
			node->invtrans = rotation*node->invtrans; 
		}	
	}
}

void Raytracer::translate( SceneDagNode* node, Vector3D trans ) {
	Matrix4x4 translation;
	
	translation[0][3] = trans[0];
	translation[1][3] = trans[1];
	translation[2][3] = trans[2];
	node->trans = node->trans*translation; 	
	translation[0][3] = -trans[0];
	translation[1][3] = -trans[1];
	translation[2][3] = -trans[2];
	node->invtrans = translation*node->invtrans; 
}

void Raytracer::scale( SceneDagNode* node, Point3D origin, double factor[3] ) {
	Matrix4x4 scale;
	
	scale[0][0] = factor[0];
	scale[0][3] = origin[0] - factor[0] * origin[0];
	scale[1][1] = factor[1];
	scale[1][3] = origin[1] - factor[1] * origin[1];
	scale[2][2] = factor[2];
	scale[2][3] = origin[2] - factor[2] * origin[2];
	node->trans = node->trans*scale; 	
	scale[0][0] = 1/factor[0];
	scale[0][3] = origin[0] - 1/factor[0] * origin[0];
	scale[1][1] = 1/factor[1];
	scale[1][3] = origin[1] - 1/factor[1] * origin[1];
	scale[2][2] = 1/factor[2];
	scale[2][3] = origin[2] - 1/factor[2] * origin[2];
	node->invtrans = scale*node->invtrans; 
}

Matrix4x4 Raytracer::initInvViewMatrix( Point3D eye, Vector3D view, 
		Vector3D up ) {
	Matrix4x4 mat; 
	Vector3D w;
	view.normalize();
	up = up - up.dot(view)*view;
	up.normalize();
	w = view.cross(up);

	mat[0][0] = w[0];
	mat[1][0] = w[1];
	mat[2][0] = w[2];
	mat[0][1] = up[0];
	mat[1][1] = up[1];
	mat[2][1] = up[2];
	mat[0][2] = -view[0];
	mat[1][2] = -view[1];
	mat[2][2] = -view[2];
	mat[0][3] = eye[0];
	mat[1][3] = eye[1];
	mat[2][3] = eye[2];

	return mat; 
}

void Raytracer::traverseScene( SceneDagNode* node, Ray3D& ray ) {
	SceneDagNode *childPtr;

	// Applies transformation of the current node to the global
	// transformation matrices.
	_modelToWorld = _modelToWorld*node->trans;
	_worldToModel = node->invtrans*_worldToModel; 
	if (node->obj) {
		// Perform intersection.
		if (node->obj->intersect(ray, _worldToModel, _modelToWorld)) {
			ray.intersection.mat = node->mat;
			ray.intersection.medium = node->medium;
		}
	}
	// Traverse the children.
	childPtr = node->child;
	while (childPtr != NULL) {
		traverseScene(childPtr, ray);
		childPtr = childPtr->next;
	}

	// Removes transformation of the current node from the global
	// transformation matrices.
	_worldToModel = node->trans*_worldToModel;
	_modelToWorld = _modelToWorld*node->invtrans;
}

void Raytracer::computeShading( Ray3D& ray ) {
	LightListNode* curLight = _lightSource;
	for (;;) {
		if (curLight == NULL) break;
		// Each lightSource provides its own shading function.

		// Implement shadows here if needed.

		std::vector<Ray3D> shadowRays;
		if (SHADOWS_ENABLED){
			std::vector<Point3D> samples = curLight->light->generateSamples();
			for (unsigned int i = 0; i < samples.size(); i++){
				Ray3D shadowRay(ray.intersection.point, samples[i] - ray.intersection.point);
				traverseScene(_root, shadowRay);
				shadowRays.push_back(shadowRay);
			}
		}

		curLight->light->shade(ray, shadowRays);
		curLight = curLight->next;
	}
}

void Raytracer::initPixelBuffer() {
	int numbytes = _scrWidth * _scrHeight * sizeof(unsigned char);
	_rbuffer = new unsigned char[numbytes];
	_gbuffer = new unsigned char[numbytes];
	_bbuffer = new unsigned char[numbytes];

	if (ANTIALIASING_ENABLED){
		int numbytesForSampling = numbytes * AA_SAMPLE_SIZE * AA_SAMPLE_SIZE;
		_rsamplebuffer = new unsigned char[numbytesForSampling];
		_gsamplebuffer = new unsigned char[numbytesForSampling];
		_bsamplebuffer = new unsigned char[numbytesForSampling];
	}
	for (int i = 0; i < _scrHeight; i++) {
		for (int j = 0; j < _scrWidth; j++) {
			_rbuffer[i*_scrWidth+j] = 0;
			_gbuffer[i*_scrWidth+j] = 0;
			_bbuffer[i*_scrWidth+j] = 0;
		}
	}
}

void Raytracer::flushPixelBuffer( char *file_name ) {
	bmp_write( file_name, _scrWidth, _scrHeight, _rbuffer, _gbuffer, _bbuffer );
	if (ANTIALIASING_ENABLED){
		delete _rsamplebuffer;
		delete _gsamplebuffer;
		delete _bsamplebuffer;
	}
	delete _rbuffer;
	delete _gbuffer;
	delete _bbuffer;
}

Ray3D reflectRay(Ray3D& ray){
	ray.dir.normalize();
	ray.intersection.normal.normalize();
	Vector3D direction = ray.dir - 2 * (ray.dir.dot(ray.intersection.normal)) * ray.intersection.normal;
	return Ray3D (ray.intersection.point, direction);
}

double getSqrtedValue(double refractionIndex, Vector3D dir, Vector3D normal){
	double dDotN = dir.dot(normal);
	return 1 - std::pow(refractionIndex, 2) * (1 - dDotN * dDotN);
}

Vector3D calculateRefractionDirection(double refractionIndex, Vector3D dir, Vector3D normal){
	double dDotN = dir.dot(normal);
	double rootedValue = getSqrtedValue(refractionIndex, dir, normal);
	return refractionIndex * (dir - dDotN * normal) - sqrt(rootedValue) * normal;
}

Colour Raytracer::shadeRay( Ray3D& ray, int depth, double refractionIndex ) {
	Colour col(0.0, 0.0, 0.0); 

	if (depth == MAX_RAY_DEPTH)
		return col;

	traverseScene(_root, ray); 
	
	// Don't bother shading if the ray didn't hit 
	// anything.
	if (!ray.intersection.none) {
		computeShading(ray); 
		col = ray.col;

		// calculate reflection/refraction values
		if (REFLECTION_ENABLED
			&& ray.intersection.mat->specular[0] > 0.0001
			&& ray.intersection.mat->specular[1] > 0.0001
			&& ray.intersection.mat->specular[2] > 0.0001){

			Ray3D reflectionRay = reflectRay(ray);
			Colour reflectedColour = ray.intersection.mat->specular * shadeRay(reflectionRay, depth + 1, refractionIndex);

			// if refraction is enabled, calculate the refracted colour
			if (REFRACTION_ENABLED && ray.intersection.medium->transparent){
				Vector3D normal = ray.intersection.normal;
				normal.normalize();
				Medium* medium = ray.intersection.medium;

				double dir = 1;
				if (normal.dot(ray.dir) > EPSILON)
					dir = -1;

				normal = dir * normal;

				double rIndex = refractionIndex / medium->refractionIndex;
				double cosI = -normal.dot(ray.dir);
				double cosT2 = 1.0 - rIndex * rIndex * (1.0 - cosI * cosI);
				if (cosT2 > EPSILON){
					Vector3D refractionDir = (rIndex * ray.dir) + (rIndex * cosI - sqrt(cosT2)) * normal;
					Ray3D refractedRay(ray.intersection.point, refractionDir);
					col = shadeRay(refractedRay, depth + 1, medium->refractionIndex);
				}
			}else{
				col = col + reflectedColour;
			}
		}
	}

	// You'll want to call shadeRay recursively (with a different ray, 
	// of course) here to implement reflection/refraction effects.  
	col.clamp();
	return col;
}

// Get average pixel value in the large sample buffer for the
// given pixel in the render buffer
int *Raytracer::getAverageInSquareAround(int row, int col){
	int startRow = row * AA_SAMPLE_SIZE;
	int startCol = col * AA_SAMPLE_SIZE;
	int endRow = startRow + AA_SAMPLE_SIZE - 1;
	int endCol = startCol + AA_SAMPLE_SIZE - 1;

	int red = 0;
	int green = 0;
	int blue = 0;
	int pixels = 0;

	for (int i = startRow; i < endRow; i++){
		for (int j = startCol; j < endCol; j++){
			int index = i*_scrWidth * AA_SAMPLE_SIZE + j;
			red += _rsamplebuffer[index];
			green += _gsamplebuffer[index];
			blue += _bsamplebuffer[index];
			pixels++;
		}
	}
	int colour[] = {red / pixels, green / pixels, blue / pixels};
	return colour;
}

void Raytracer::render( int width, int height, Point3D eye, Vector3D view, 
		Vector3D up, double fov, char* fileName ) {
	Matrix4x4 viewToWorld;
	_scrWidth = width;
	_scrHeight = height;

	int renderWidth = width;
	int renderHeight = height;
	if (ANTIALIASING_ENABLED){
		renderWidth = renderWidth * AA_SAMPLE_SIZE;
		renderHeight = renderHeight * AA_SAMPLE_SIZE;
	}

	double factor = (double(renderHeight)/2)/tan(fov*M_PI/360.0);

	initPixelBuffer();
	viewToWorld = initInvViewMatrix(eye, view, up);

	// Construct a ray for each pixel.
	for (int i = 0; i < renderHeight; i++) {
		for (int j = 0; j < renderWidth; j++) {
			// Sets up ray origin and direction in view space, 
			// image plane is at z = -1.
			Point3D origin(0, 0, 0);
			Point3D imagePlane;
			imagePlane[0] = (-double(renderWidth)/2 + 0.5 + j)/factor;
			imagePlane[1] = (-double(renderHeight)/2 + 0.5 + i)/factor;
			imagePlane[2] = -1;

			Vector3D direction = imagePlane - origin;

			int samples = DEPTH_OF_FIELD_ENABLED? DOF_SAMPLE_SIZE : 1;

			Point3D pointAimed = DEPTH_OF_FIELD_ENABLED? (origin + FOCAL_LENGTH * direction) : imagePlane;

			// TODO: Convert ray to world space and call 
			// shadeRay(ray) to generate pixel colour. 	
			Colour col(0.0, 0.0, 0.0);
			for (int s = 0; s < samples; s++){
				float dx = 0, dy = 0;
				if (DEPTH_OF_FIELD_ENABLED){
					dx = (rand() % 5) / 5.0f;
					dy = (rand() % 5) / 5.0f;
				}

				// jitter start position of the ray if DOF is enabled
				Point3D start = Point3D(origin[0] + dx, origin[1] + dx, origin[2]);
				Vector3D newDirection = pointAimed - start;
				newDirection.normalize();

				Ray3D ray(viewToWorld * start, viewToWorld * newDirection);
				col = col + shadeRay(ray, 0, 1.0);
			}

			col = (1.0 / samples) * col;
			col.clamp();

			// if antialiasing is enabled, we want to write to the
			// larger sample buffer first
			if (ANTIALIASING_ENABLED){
				_rsamplebuffer[i*renderWidth+j] = int(col[0]*255);
				_gsamplebuffer[i*renderWidth+j] = int(col[1]*255);
				_bsamplebuffer[i*renderWidth+j] = int(col[2]*255);
			} else{
				_rbuffer[i*renderWidth+j] = int(col[0]*255);
				_gbuffer[i*renderWidth+j] = int(col[1]*255);
				_bbuffer[i*renderWidth+j] = int(col[2]*255);
			}
		}
	}

	// antialiasing via super sampling a 3x3 square
	if (ANTIALIASING_ENABLED){
		for (int i = 0; i < _scrHeight; i++) {
			for (int j = 0; j < _scrWidth; j++) {
				int *colour = getAverageInSquareAround(i, j);
				_rbuffer[i*width+j] = colour[0];
				_gbuffer[i*width+j] = colour[1];
				_bbuffer[i*width+j] = colour[2];
			}
		}
	}

	flushPixelBuffer(fileName);
}

int main(int argc, char* argv[])
{	
	// Build your scene and setup your camera here, by calling 
	// functions from Raytracer.  The code here sets up an example
	// scene and renders it from two different view points, DO NOT
	// change this if you're just implementing part one of the 
	// assignment.  
	Raytracer raytracer;
	int width = 320; 
	int height = 240; 

	if (argc == 3) {
		width = atoi(argv[1]);
		height = atoi(argv[2]);
	}

	srand((unsigned)time(NULL));
	// Camera parameters.
	Point3D eye(0, 0, 1);
	Vector3D view(0, 0, -1);
	Vector3D up(0, 1, 0);
	double fov = 60;

	// Defines a material for shading.
	Material gold( Colour(0.3, 0.3, 0.3), Colour(0.75164, 0.60648, 0.22648), 
			Colour(0.628281, 0.555802, 0.366065), 
			51.2 );
	Material jade( Colour(0, 0, 0), Colour(0.54, 0.89, 0.63), 
			Colour(0.316228, 0.316228, 0.316228), 
			12.8 );
	Material rose( Colour(0.3, 0.3, 0.3), Colour(0.84990, 0.22859, 0.60992),
			Colour(0.75002, 0.36859, 0.60992),
			6.7);
	Medium nonTransparent = Medium();
	Medium marble(true, 2.0, Colour(0.5, 0.5, 0.5));

	// Defines a point light source.
	//raytracer.addLightSource( new PointLight(Point3D(0, 0, 5),
	//			Colour(0.9, 0.9, 0.9) ) );
	raytracer.addLightSource(new AreaLight( Point3D(-1, -1, 5), Point3D(1, -1, 5),
			Point3D(-1, 1, 5), Point3D(1, 1, 5),
			Colour(0.9, 0.9, 0.9)
		));

	// Add a unit square into the scene with material mat.
	SceneDagNode* sphere = raytracer.addObject( new UnitSphere(), &gold, &marble );
	SceneDagNode* plane = raytracer.addObject( new UnitSquare(), &jade, &nonTransparent );
	SceneDagNode* cylinder = raytracer.addObject(new UnitCylinder(), &rose, &nonTransparent );
	
	// Apply some transformations to the unit square.
	double factor1[3] = { 1.0, 2.0, 1.0 };
	double factor2[3] = { 6.0, 6.0, 6.0 };
	raytracer.translate(sphere, Vector3D(0, 0, -5));
	raytracer.rotate(sphere, 'x', -45);
	raytracer.rotate(sphere, 'z', 45);
	raytracer.scale(sphere, Point3D(0, 0, 0), factor1);

	raytracer.translate(cylinder, Vector3D(3, 0, -5));
	raytracer.rotate(cylinder, 'x', -45);
	raytracer.rotate(cylinder, 'z', 45);

	raytracer.translate(plane, Vector3D(0, 0, -7));
	raytracer.rotate(plane, 'z', 45);
	raytracer.scale(plane, Point3D(0, 0, 0), factor2);

	// Use the below transformations instead for DOF view
	/*raytracer.translate(sphere, Vector3D(1, 0, -5));
	raytracer.rotate(sphere, 'x', -45);
	raytracer.rotate(sphere, 'y', 75);
	raytracer.rotate(sphere, 'z', 45);
	raytracer.scale(sphere, Point3D(0, 0, 0), factor1);*/

	/*raytracer.translate(cylinder, Vector3D(5, 0, -5));
	raytracer.rotate(cylinder, 'x', -45);
	raytracer.rotate(cylinder, 'y', 75);
	raytracer.rotate(cylinder, 'z', 45); */

	/*raytracer.translate(plane, Vector3D(0, 0, -7));
	raytracer.rotate(plane, 'z', 45);
	raytracer.rotate(plane, 'y', 75);
	raytracer.scale(plane, Point3D(0, 0, 0), factor2);*/

	// Render the scene, feel free to make the image smaller for
	// testing purposes.	
	raytracer.render(width, height, eye, view, up, fov, "view1.bmp");
	
	// Render it from a different point of view.
	Point3D eye2(4, 2, 1);
	Vector3D view2(-4, -2, -6);
	raytracer.render(width, height, eye2, view2, up, fov, "view2.bmp");
	
	return 0;
}

