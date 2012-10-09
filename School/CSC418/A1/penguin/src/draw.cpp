/*
 * draw.cpp
 *
 *  Created on: Oct 9, 2012
 *      Author: g9sk
 */

#include "draw.h"

float joint_rot[] = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
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
