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
const float LIMB_MIN = -45.0f;
const float LIMB_MAX =  45.0f;
const float FOOT_MIN = -30.0f;
const float FOOT_MAX =  30.0f;
const float HEAD_MIN = -10.0f;
const float HEAD_MAX =  10.0f;
const int BEAK_MIN = 0;
const int BEAK_MAX = 6;
extern float leg1Rotation;
extern float leg2Rotation;
extern float armRotation;
extern float foot1Rotation;
extern float foot2Rotation;
extern float headRotation;
extern int beakDistance;

extern float bodyPositionX;

// head shape constants
const float HEAD_SCALE = 0.5f;
const float BEAK_DISTANCE_FROM_HEAD = -1.3f;

// body shape constants
const float BODY_WIDTH = 200.0f;
const float BODY_LENGTH = 200.0f;
const float BELLY_WIDTH = 0.8f;
const float NECK_WIDTH = 0.4f;
const float BOTTOM_WIDTH = 0.2f;

// arm shape constants
const float ARM_SCALE = 0.5f;
const float ARM_WIDTH_TOP = 0.5f;
const float ARM_WIDTH_BOTTOM = 0.3f;
const float ARM_LENGTH = 1.0f;

// leg shape constants
const float LEG_OFFSET_X = 0.2f;
const float LEG_OFFSET_Y = -0.5;

const float LEG_SCALE = 0.3f;
const float LEG_WIDTH = 0.4f;
const float LEG_LENGTH = 1.0f;

// foot shape constants
const float FOOT_OFFSET_X = -0.5f;
const float FOOT_OFFSET_Y = -0.4f;
const float FOOT_LENGTH = 1.0f;
const float FOOT_THICKNESS = 0.4f;

// animation constants
const double ROTATION_SPEED = 0.1;
const int BEAK_SEPARATION_SPEED = 1;

// drawing function definitions
void drawPolygon(int n, Point points[]);
void drawCircle(float radius);
void drawEntireBody();
void drawHead();
void drawBeak();
void drawArm();
void drawLeg(float leftOrRight, float legRotation, float footRotation);

#endif /* DRAW_H_ */
