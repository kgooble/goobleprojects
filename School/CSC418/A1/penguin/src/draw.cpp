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
		glTranslatef(0.1, ARM_SCALE/2, 0.0);
		glRotatef(armRotation, 0.0, 0.0, 1.0);
		glScalef(ARM_SCALE, ARM_SCALE, 1.0);
		glTranslatef(0.0, -ARM_SCALE + 0.1, 0.0);
		glColor3f(0.0, 0.0, 1.0);
		Point armPoints[] = {
		        // top left point
		        {-ARM_WIDTH_TOP/2, ARM_LENGTH/2},
		        // top right point
		        {ARM_WIDTH_TOP/2, ARM_LENGTH/2},
		        // bottom right point
		        {ARM_WIDTH_BOTTOM/2, -ARM_LENGTH/2},
		        // bottom left point
		        {-ARM_WIDTH_BOTTOM/2, -ARM_LENGTH/2}};
		drawPolygon(4, armPoints);
	}
	glPopMatrix();

	//draw the shoulder joint
    glPushMatrix();
    {
        glTranslatef(0.1, ARM_SCALE/2, 0.0);
        glScalef(LEG_SCALE * 0.1, LEG_SCALE * 0.1, 1.0);
        glColor3f(1.0, 1.0, 1.0);
        drawCircle(1.0);
    }
    glPopMatrix();

}

// draw an arbitrary n-point polygon
void drawPolygon(int n, Point points[]){
	glBegin(GL_POLYGON);
	for (int i = 0; i < n; i++){
		glVertex2f(points[i].x, points[i].y);
	}
	glEnd();

}

// this is used for drawing the joints
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
		glTranslatef(0.0, HEAD_SCALE, 0.0);
		glRotatef(headRotation, 0.0, 0.0, 1.0);
		glScalef(HEAD_SCALE, HEAD_SCALE, 1.0);
		glTranslatef(0.0, HEAD_SCALE/4, 0.0);
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

		// draw eye
		glPushMatrix();
		{
		    glTranslatef(-0.2, 0.2, 0.0);
		    glScalef(0.05, 0.05, 1.0);
		    glColor3f(1.0, 1.0, 1.0);
		    drawCircle(1.0);

		    glPushMatrix();
		    {
		        glTranslatef(0.1, 0.0, 0.0);
		        glScalef(0.5, 0.5, 1.0);
                glColor3f(0.0, 0.0, 0.0);
                drawCircle(1.0);
		    }
		    glPopMatrix();
		}
		glPopMatrix();
	}
	glPopMatrix();

	// draw neck joint
	glPushMatrix();
	{
        glTranslatef(0.0, HEAD_SCALE, 0.0);
        glScalef(LEG_SCALE * 0.1, LEG_SCALE * 0.1, 1.0);
        glColor3f(1.0, 1.0, 1.0);
        drawCircle(1.0);
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
        glTranslatef(0.0, LEG_OFFSET_Y + 0.15, 0.0);
        // Draw the square for the leg
        glColor3f(1.0, 0.5, 0.5);

        Point legPoints[] = {
                // top left point
                {-LEG_WIDTH/2, LEG_LENGTH/2},
                // top right point
                {LEG_WIDTH/2, LEG_LENGTH/2},
                // bottom right point
                {LEG_WIDTH/2, -LEG_LENGTH/2},
                // bottom left point
                {-LEG_WIDTH/2, -LEG_LENGTH/2}};
        drawPolygon(4, legPoints);

        // Draw the foot
        glPushMatrix();
        {
            // move the foot to the joint
            glTranslatef(0.0, FOOT_OFFSET_Y, 0.0);
            glRotatef(footRotation, 0.0, 0.0, 1.0);
            // move the foot to its correct position
            glTranslatef(FOOT_OFFSET_X + LEG_WIDTH/2, 0.0, 0.0);
            glColor3f(1.0, 1.0, 0.0);

            Point footPoints[] = {
                    // top left point
                    {-FOOT_LENGTH/2, FOOT_THICKNESS/2},
                    // top right point
                    {FOOT_LENGTH/2, FOOT_THICKNESS/2},
                    // bottom right point
                    {FOOT_LENGTH/2, -FOOT_THICKNESS/2},
                    // bottom left point
                    {-FOOT_LENGTH/2, -FOOT_THICKNESS/2}};
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

