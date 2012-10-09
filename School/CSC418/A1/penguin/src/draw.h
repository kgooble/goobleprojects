/*
 * draw.h
 *
 *  Created on: Oct 9, 2012
 *      Author: g9sk
 */

#ifndef DRAW_H_
#define DRAW_H_

#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>
#include <GL/glui.h>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

// ------------ REQUIRED DATA STRUCTURES --------------------
typedef struct{
	float x;
	float y;
} Point;

const float DEG2RAD = 3.14159 / 180;
// Joint parameters
const float JOINT_MIN = -45.0f;
const float JOINT_MAX =  45.0f;
const int BEAK_MIN = 0;
const int BEAK_MAX = 6;
extern float joint_rot[]; // six values for each joint
extern int beakDistance;

const double LEG_ROTATION_SPEED = 0.1;
const int BEAK_SEPARATION_SPEED = 1;

void drawPolygon(int n, Point points[]);
void drawCircle(float radius);
void drawHead();
void drawBeak();
void drawArm();

#endif /* DRAW_H_ */
