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
		// Move the arm into the hinge position
		glTranslatef(0.1, ARM_SCALE/2, 0.0);
		// Rotate the arm along the hinge
		glRotatef(armRotation, 0.0, 0.0, 1.0);
		// Scale the arm down from the size of the body
		glScalef(ARM_SCALE, ARM_SCALE, 1.0);
		// Translate the arm to its true position
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

	// Draw the shoulder joint
    glPushMatrix();
    {
        glTranslatef(0.1, ARM_SCALE/2, 0.0);
        glScalef(LEG_SCALE * 0.1, LEG_SCALE * 0.1, 1.0);
        glColor3f(1.0, 1.0, 1.0);
        drawCircle(1.0);
    }
    glPopMatrix();

}

// Draw an arbitrary n-point polygon
void drawPolygon(int n, Point points[]){
	glBegin(GL_POLYGON);
	for (int i = 0; i < n; i++){
		glVertex2f(points[i].x, points[i].y);
	}
	glEnd();

}

// Draw a circle with the given radius.
// This is used for drawing the joints.
void drawCircle(float radius){
    glBegin(GL_LINE_LOOP);
    for (int i = 0; i < 360; i++){
        float degInRad = i * DEG2RAD;
        glVertex2f(cos(degInRad) * radius, sin(degInRad) * radius);
    }
    glEnd();
}


void drawBeak(){
	// Draw the top beak
	glPushMatrix();{
		// Scale the beak down from the size of the head
		glScalef(0.5, 0.5, 1.0);
		// Move the beak away from the head, off to the left
		glTranslatef(-1.0, beakDistance / 20.0f, 0.0);
		glColor3f(0.5, 0.5, 1.0);

		Point topBeakPoints[] = {
				// top left point
		        {BEAK_DISTANCE_FROM_HEAD, 0.1},
		        // bottom left point
		        {BEAK_DISTANCE_FROM_HEAD, -0.1},
		        // bottom right point
				{0.10, -0.1},
				// top right point
				{0.15, 0.3}};
		drawPolygon(4, topBeakPoints);
	}
	glPopMatrix();

	// Draw the bottom beak
	glPushMatrix();{
		// Scale the beak down from the size of the head
		glScalef(0.5, 0.5, 1.0);
		// Move the beak to the left of the head
		glTranslatef(-1.0, -beakDistance / 20.0f, 0.0);
		glColor3f(0.5, 0.1, 1.0);

		Point bottomBeakPoints[] = {
				// top left point
				{BEAK_DISTANCE_FROM_HEAD, 0.1},
				// bottom left point
		        {BEAK_DISTANCE_FROM_HEAD, -0.1},
		        // bottom right point
				{0.1, -0.1},
				// bottom left point
				{0.1, 0.1}};
		drawPolygon(4, bottomBeakPoints);
	}
	glPopMatrix();
}


void drawHead(){
	glPushMatrix();{
		// Move the head up to the hinge
		glTranslatef(0.0, HEAD_SCALE, 0.0);
		// Rotate the head along the hinge
		glRotatef(headRotation, 0.0, 0.0, 1.0);
		// Scale the head down from the size of the body
		glScalef(HEAD_SCALE, HEAD_SCALE, 1.0);
		// Move the head into its correct position
		glTranslatef(0.0, HEAD_SCALE/4, 0.0);
		glColor3f(1.0, 0.0, 0.0);
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

		// Draw eye
		glPushMatrix();
		{
			// Move the eye to the top left part of the head
		    glTranslatef(-0.2, 0.2, 0.0);
		    // Scale the eye down
		    glScalef(0.05, 0.05, 1.0);
		    glColor3f(1.0, 1.0, 1.0);

			drawCircle(1.0);

		    glPushMatrix();
		    {
		    	// Move the pupil a bit to the left so it looks like
		    	// the penguin is looking forward
		        glTranslatef(-0.1, 0.0, 0.0);
		        // Scale the pupil down
		        glScalef(0.5, 0.5, 1.0);
                glColor3f(0.0, 0.0, 0.0);
                drawCircle(1.0);
		    }
		    glPopMatrix();
		}
		glPopMatrix();
	}
	glPopMatrix();

	// Draw neck joint
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
	// This function is called twice; once for each leg
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
            // rotate the foot along the joint
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

void drawEntireBody(){
	// Draw our hinged object
	// Push the current transformation matrix on the stack
	glPushMatrix();
		glPushMatrix();
		{
			glTranslatef(bodyPositionX, 0.0, 0.0);
			// Scale to size of body
			glScalef(BODY_WIDTH, BODY_LENGTH, 1.0);
			// Set the colour to green
			glColor3f(0.0, 1.0, 0.0);

			// Draw the body
			Point bodyPoints[] = {
					// top left, right points
					{-NECK_WIDTH/2, 0.5f}, {NECK_WIDTH/2, 0.5f},
					// middle right point
					{BELLY_WIDTH/2, -0.4f},
					// bottom right point
					{BOTTOM_WIDTH/2, -0.7f},
					// bottom left point
					{-BOTTOM_WIDTH/2, -0.7f},
					// middle left point
					{-BELLY_WIDTH/2, -0.4f}};
			drawPolygon(6, bodyPoints);

			drawArm();
			drawLeg(1.0, leg1Rotation, foot1Rotation);
			drawLeg(-1.0, leg2Rotation, foot2Rotation);
			drawHead();
		}
		glPopMatrix();


	// Retrieve the previous state of the transformation stack
	glPopMatrix();
}
