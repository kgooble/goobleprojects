/*
 * draw.cpp
 *
 *  Created on: Oct 9, 2012
 *      Author: g9sk
 *
 *
 *  This file contains all drawing functions & relevant transformations
 *  (rotation, translation, and scaling) for each part.
 */

#include "draw.h"

float leg1Rotation = 0.0f;
float leg2Rotation = 0.0f;
float armRotation = 0.0f;
float foot1Rotation = 0.0f;
float foot2Rotation = 0.0f;
float headRotation = 0.0f;
int beakDistance = 0;

void drawArm(){
	glPushMatrix();
	{
		glTranslatef(0.1, 0.0, 0.0);
		glRotatef(armRotation, 0.0, 0.0, 1.0);
		glScalef(1.0/3.0, 0.5, 1.0);
		glColor3f(0.0, 0.0, 1.0);
		Point armPoints[] = {{-0.4f, 0.5f}, {0.4f, 0.5f},
		        {0.3f, -0.5f}, {-0.3f, -0.5f}};
		drawPolygon(4, armPoints);
	}
	glPopMatrix();
}

// Draw a square of the specified size, centered at the current location
void drawSquare(float width)
{
    Point points[] = {{-width/2, -width/2},
                      {width/2, -width/2},
                      {width/2, width/2},
                      {-width/2, width/2}};
    drawPolygon(4, points);
}

void drawPolygon(int n, Point points[]){
	glBegin(GL_POLYGON);
	for (int i = 0; i < n; i++){
		glVertex2f(points[i].x, points[i].y);
	}
	glEnd();

}

void drawCircle(float radius){
    glBegin(GL_LINE_LOOP);
    for (int i = 0; i < 360; i++){
        float degInRad = i * DEG2RAD;
        glVertex2f(cos(degInRad) * radius, sin(degInRad) * radius);
    }
    glEnd();
}


const float BEAK_DISTANCE_FROM_HEAD = -1.3f;

void drawBeak(){
	// draw top beak
	glPushMatrix();{
		glScalef(0.5, 0.5, 1.0);
		glTranslatef(-1.0, beakDistance / 20.0f, 0.0);
		glColor3f(0.5, 0.5, 1.0);

		Point topBeakPoints[] = {
		        {BEAK_DISTANCE_FROM_HEAD, 0.1},
		        {BEAK_DISTANCE_FROM_HEAD, -0.1},
				{0.10, -0.1}, {0.15, 0.3}};
		drawPolygon(4, topBeakPoints);
	}
	glPopMatrix();

	// draw bottom beak
	glPushMatrix();{
		glScalef(0.5, 0.5, 1.0);
		glTranslatef(-1.0, -beakDistance / 20.0f, 0.0);
		glColor3f(0.5, 0.1, 1.0);

		Point bottomBeakPoints[] = {{BEAK_DISTANCE_FROM_HEAD, 0.1},
		        {BEAK_DISTANCE_FROM_HEAD, -0.1},
				{0.1, -0.1}, {0.1, 0.1}};
		drawPolygon(4, bottomBeakPoints);
	}
	glPopMatrix();
}


void drawHead(){
	glPushMatrix();{
		glTranslatef(0.0, 0.6, 0.0);
		glRotatef(headRotation, 0.0, 0.0, 1.0);
		glScalef(0.5, 0.5, 1.0);
		glColor3f(0.75, 0.75, 0.75);
		Point headPoints[] = {
		        // top point
		        {-0.2, 0.5},
		        // top right point
		        {0.35, 0.25},
		        // bottom right point
				{0.5, -0.35},
				// bottom left point
				{-0.5, -0.35},
				// top left point
				{-0.4, 0.3}};
		drawPolygon(5, headPoints);
		drawBeak();
	}
	glPopMatrix();
}


void drawLeg(float leftOrRight, float legRotation, float footRotation){
    // Draw a leg
    glPushMatrix();
    {
        // Move the leg to the joint hinge
        glTranslatef(leftOrRight * LEG_OFFSET_X, LEG_OFFSET_Y, 0.0);
        // Rotate along the hinge
        glRotatef(legRotation, 0.0, 0.0, 1.0);
        // Scale the size of the leg
        glScalef(LEG_SCALE, LEG_SCALE, 1.0);
        // Move to center location of leg, under previous rotation
        glTranslatef(0.0, LEG_OFFSET_Y, 0.0);
        // Draw the square for the leg
        glColor3f(1.0, 0.5, 0.5);

        float legWidth = 0.4f;
        float legLength = 1.0f;

        Point legPoints[] = {
                // top left point
                {-legWidth/2, legLength/2},
                // top right point
                {legWidth/2, legLength/2},
                // bottom right point
                {legWidth/2, -legLength/2},
                // bottom left point
                {-legWidth/2, -legLength/2}};
        drawPolygon(4, legPoints);

        // Draw the foot
        glPushMatrix();
        {
            // move the foot to the joint
            glTranslatef(0.0, FOOT_OFFSET_Y, 0.0);
            glRotatef(footRotation, 0.0, 0.0, 1.0);
            // move the foot to its correct position
            glTranslatef(FOOT_OFFSET_X + legWidth/2, 0.0, 0.0);
            glColor3f(1.0, 1.0, 0.0);

            float footLength = 1.0f;
            float footThickness = 0.4f;
            Point footPoints[] = {
                    // top left point
                    {-footLength/2, footThickness/2},
                    // top right point
                    {footLength/2, footThickness/2},
                    // bottom right point
                    {footLength/2, -footThickness/2},
                    // bottom left point
                    {-footLength/2, -footThickness/2}};
            drawPolygon(4, footPoints);
        }
        glPopMatrix();

        // draw the ankle joint
        glPushMatrix();
        {
            glTranslatef(0.0, FOOT_OFFSET_Y, 0.0);
            glScalef(0.1, 0.1, 1.0);
            glColor3f(0.0, 0.0, 0.0);
            drawCircle(1.0);
        }
        glPopMatrix();

    }
    glPopMatrix();

    //draw the hip joints
    glPushMatrix();
    {
        glTranslatef(leftOrRight * LEG_OFFSET_X, LEG_OFFSET_Y, 0.0);
        glScalef(LEG_SCALE * 0.1, LEG_SCALE * 0.1, 1.0);
        glColor3f(0.0, 0.0, 0.0);
        drawCircle(1.0);
    }
    glPopMatrix();
}

