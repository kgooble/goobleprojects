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

float jointRotation[] = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
int beakDistance = 0;

void drawArm(){
	glPushMatrix();
	{
		glTranslatef(0.1, 0.0, 0.0);
		glScalef(1.0/3.0, 0.5, 1.0);
		glColor3f(0.0, 0.0, 1.0);
		Point armPoints[] = {{-0.4f, 0.5f}, {0.4f, 0.5f}, {0.3f, -0.5f}, {-0.3f, -0.5f}};
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


void drawBeak(){
	// draw top beak
	glPushMatrix();{
		glScalef(0.8, 0.4, 1.0);
		glTranslatef(-1.0, beakDistance / 20.0f, 0.0);
		glColor3f(0.5, 0.5, 1.0);

		Point topBeakPoints[] = {{-0.5, 0.1}, {-0.5, -0.1},
				{0.5, -0.1}, {0.5, 0.3}};
		drawPolygon(4, topBeakPoints);
	}
	glPopMatrix();

	// draw bottom beak
	glPushMatrix();{
		glScalef(0.8, 0.2, 1.0);
		glTranslatef(-1.0, -beakDistance / 20.0f, 0.0);
		glColor3f(0.5, 0.1, 1.0);

		Point bottomBeakPoints[] = {{-0.5, 0.1}, {-0.5, -0.1},
				{0.5, -0.1}, {0.5, 0.1}};
		drawPolygon(4, bottomBeakPoints);
	}
	glPopMatrix();
}


void drawHead(){
	glPushMatrix();{
		glTranslatef(0.0, 0.6, 0.0);
		glScalef(0.6, 0.25, 1.0);
		glColor3f(0.75, 0.75, 0.75);
		Point headPoints[] = {{-0.1, 0.7}, {0.4, 0.4},
				{0.5, -0.5}, {-0.5, -0.5}, {-0.4, 0.4}};
		drawPolygon(5, headPoints);
		drawBeak();
	}
	glPopMatrix();
}


void drawLeg(float leftOrRight, int rotationIndex){
    // Draw a leg
    glPushMatrix();
    {
        // Move the leg to the joint hinge
        glTranslatef(leftOrRight * LEG_OFFSET_X, LEG_OFFSET_Y, 0.0);
        // Rotate along the hinge
        glRotatef(jointRotation[rotationIndex], 0.0, 0.0, 1.0);
        // Scale the size of the leg
        glScalef(LEG_WIDTH_REL_TO_BODY, LEG_LENGTH_REL_TO_BODY, 1.0);
        // Move to center location of leg, under previous rotation
        glTranslatef(0.0, -0.5, 0.0);
        // Draw the square for the leg
        glColor3f(1.0, 0.5, 0.5);
        drawSquare(1.0);

        // Draw the foot
        glPushMatrix();
        {
            glTranslatef(FOOT_OFFSET_X, FOOT_OFFSET_Y, 0.0);
            glRotatef(jointRotation[2], 0.0, 0.0, 1.0);
            glScalef(FOOT_WIDTH_REL_TO_LEG, FOOT_LENGTH_REL_TO_LEG, 1.0);
            glColor3f(1.0, 1.0, 0.0);
            drawSquare(1.0);
        }
        glPopMatrix();

        // draw the ankle joint
        glPushMatrix();
        {
            glTranslatef(0.0, FOOT_OFFSET_Y, 0.0);
            glScalef(1.0/5.0, 0.1, 1.0);
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
        glScalef(LEG_WIDTH_REL_TO_BODY / 5.0, LEG_LENGTH_REL_TO_BODY / 10.0, 1.0);
        glColor3f(0.0, 0.0, 0.0);
        drawCircle(1.0);
    }
    glPopMatrix();
}

