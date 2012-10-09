/***********************************************************
             CSC418/2504, Fall 2009
  
                 penguin.cpp
                 
       Simple demo program using OpenGL and the glut/glui 
       libraries

  
    Instructions:
        Please read the assignment page to determine 
        exactly what needs to be implemented.  Then read 
        over this file and become acquainted with its 
        design.

        Add source code where it appears appropriate. In
        particular, see lines marked 'TODO'.

        You should not need to change the overall structure
        of the program. However it should be clear what
        your changes do, and you should use sufficient comments
        to explain your code.  While the point of the assignment
        is to draw and animate the character, you will
        also be marked based on your design.

***********************************************************/

#ifdef _WIN32
#include <windows.h>
#endif

#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>
#include <GL/glui.h>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#ifndef _WIN32
#include <unistd.h>
#else
void usleep(unsigned int nanosec)
{
    Sleep(nanosec / 1000);
}
#endif


// *************** GLOBAL VARIABLES *************************


const float PI = 3.14159;

// --------------- USER INTERFACE VARIABLES -----------------

// Window settings
int windowID;               // Glut window ID (for display)
GLUI *glui;                 // Glui window (for controls)
int Win[2];                 // window (x,y) size


// ---------------- ANIMATION VARIABLES ---------------------

// Animation settings
int animate_mode = 0;       // 0 = no anim, 1 = animate
int animation_frame = 0;      // Specify current frame of animation

// Joint parameters
const float JOINT_MIN = -45.0f;
const float JOINT_MAX =  45.0f;
const int BEAK_MIN = 0;
const int BEAK_MAX = 6;
float joint_rot[] = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f}; // six values for each joint
int beakDistance = 0;

//////////////////////////////////////////////////////
// TODO: Add additional joint parameters here
//////////////////////////////////////////////////////


// ------------ REQUIRED DATA STRUCTURES --------------------
typedef struct{
	float x;
	float y;
} Point;


// ***********  FUNCTION HEADER DECLARATIONS ****************


// Initialization functions
void initGlut(char* winName);
void initGlui();
void initGl();


// Callbacks for handling events in glut
void myReshape(int w, int h);
void animate();
void display(void);

// Callback for handling events in glui
void GLUI_Control(int id);


// Functions to help draw the object
void drawSquare(float size);
void drawCircle(float radius);
void drawPolygon(int n, Point points[]);
void drawArm();
void drawLeg(float leftOrRight, int rotationIndex);
void drawBeak();
void drawHead();

float getDegrees(float rad, float min, float max);

// Return the current system clock (in seconds)
double getTime();


// ******************** FUNCTIONS ************************



// main() function
// Initializes the user interface (and any user variables)
// then hands over control to the event handler, which calls 
// display() whenever the GL window needs to be redrawn.
int main(int argc, char** argv)
{

    // Process program arguments
    if(argc != 3) {
        printf("Usage: demo [width] [height]\n");
        printf("Using 800x600 window by default...\n");
        Win[0] = 800;
        Win[1] = 600;
    } else {
        Win[0] = atoi(argv[1]);
        Win[1] = atoi(argv[2]);
    }


    // Initialize glut, glui, and opengl
    glutInit(&argc, argv);
    initGlut(argv[0]);
    initGlui();
    initGl();

    // Invoke the standard GLUT main event loop
    glutMainLoop();

    return 0;         // never reached
}


// Initialize glut and create a window with the specified caption 
void initGlut(char* winName)
{
    // Set video mode: double-buffered, color, depth-buffered
    glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);

    // Create window
    glutInitWindowPosition (0, 0);
    glutInitWindowSize(Win[0],Win[1]);
    windowID = glutCreateWindow(winName);

    // Setup callback functions to handle events
    glutReshapeFunc(myReshape); // Call myReshape whenever window resized
    glutDisplayFunc(display);   // Call display whenever new frame needed 
}


// Quit button handler.  Called when the "quit" button is pressed.
void quitButton(int)
{
  exit(0);
}

// Animate button handler.  Called when the "animate" checkbox is pressed.
void animateButton(int)
{
  // synchronize variables that GLUT uses
  glui->sync_live();

  animation_frame = 0;
  if(animate_mode == 1) {
    // start animation
    GLUI_Master.set_glutIdleFunc(animate);
  } else {
    // stop animation
    GLUI_Master.set_glutIdleFunc(NULL);
  }
}

/**
 * There are 6 points of rotation
 * 7 degrees of freedom total, including the beak which moves up and down.
 *
 * --> each joint will have its own INDEX Into the joint rotation array.
 * --> each joint may have its own set of MAX & MIN values.
 *
 */

// Initialize GLUI and the user interface
void initGlui()
{
    GLUI_Master.set_glutIdleFunc(NULL);

    // Create GLUI window
    glui = GLUI_Master.create_glui("Glui Window", 0, Win[0]+10, 0);

    // Create a control to specify the rotation of the joint
    GLUI_Spinner *joint_spinner
        = glui->add_spinner("Leg 1", GLUI_SPINNER_FLOAT, &joint_rot[0]);
    joint_spinner->set_speed(0.1);
    joint_spinner->set_float_limits(JOINT_MIN, JOINT_MAX, GLUI_LIMIT_CLAMP);

    GLUI_Spinner *leg2Spinner
		= glui->add_spinner("Leg 2", GLUI_SPINNER_FLOAT, &joint_rot[1]);
    leg2Spinner->set_speed(0.1);
    leg2Spinner->set_float_limits(JOINT_MIN, JOINT_MAX, GLUI_LIMIT_CLAMP);

    GLUI_Spinner *beakSpinner
		= glui->add_spinner("Beak", GLUI_SPINNER_INT, &beakDistance);
    beakSpinner->set_speed(1);
    beakSpinner->set_int_limits(BEAK_MIN, BEAK_MAX, GLUI_LIMIT_CLAMP);

    ///////////////////////////////////////////////////////////
    // TODO: 
    //   Add controls for additional joints here
    ///////////////////////////////////////////////////////////

    // Add button to specify animation mode 
    glui->add_separator();
    glui->add_checkbox("Animate", &animate_mode, 0, animateButton);

    // Add "Quit" button
    glui->add_separator();
    glui->add_button("Quit", 0, quitButton);

    // Set the main window to be the "active" window
    glui->set_main_gfx_window(windowID);
}


// Performs most of the OpenGL intialization
void initGl(void)
{
    // glClearColor (red, green, blue, alpha)
    // Ignore the meaning of the 'alpha' value for now
    glClearColor(0.7f,0.7f,0.9f,0.5f);
}

// Callback idle function for animating the scene
void animate()
{
    // Update geometry
	// We want to start at the original position of the legs...
	// then we need to store Time, not joint rotation... that or figure out the radians from the initial angle
    const double joint_rot_speed = 0.1;
    const int beakSeparationSpeed = 1;

    float rad = animation_frame * joint_rot_speed;
    joint_rot[0] = getDegrees(rad, JOINT_MIN, JOINT_MAX);
    joint_rot[1] = getDegrees(rad, JOINT_MIN, JOINT_MAX);
    
    beakDistance = getDegrees(beakSeparationSpeed * animation_frame, BEAK_MIN, BEAK_MAX);

    ///////////////////////////////////////////////////////////
    // TODO:
    //   Modify this function animate the character's joints
    //   Note: Nothing should be drawn in this function!  OpenGL drawing
    //   should only happen in the display() callback.
    ///////////////////////////////////////////////////////////

    // Update user interface
    glui->sync_live();

    // Tell glut window to update itself.  This will cause the display()
    // callback to be called, which renders the object (once you've written
    // the callback).
    glutSetWindow(windowID);
    glutPostRedisplay();

    // increment the frame number.
    animation_frame++;

    // Wait 50 ms between frames (20 frames per second)
    usleep(50000);
}


// Handles the window being resized by updating the viewport
// and projection matrices
void myReshape(int w, int h)
{
    // Setup projection matrix for new window
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-w/2, w/2, -h/2, h/2);

    // Update OpenGL viewport and internal variables
    glViewport(0,0, w,h);
    Win[0] = w;
    Win[1] = h;
}



// display callback
//
// This gets called by the event handler to draw
// the scene, so this is where you need to build
// your scene -- make your changes and additions here.
// All rendering happens in this function.  For Assignment 1,
// updates to geometry should happen in the "animate" function.
void display(void)
{
    // glClearColor (red, green, blue, alpha)
    // Ignore the meaning of the 'alpha' value for now
    glClearColor(0.7f,0.7f,0.9f,1.0f);

    // OK, now clear the screen with the background colour
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  

    // Setup the model-view transformation matrix
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

    ///////////////////////////////////////////////////////////
    // TODO:
    //   Modify this function draw the scene
    //   This should include function calls to pieces that
    //   apply the appropriate transformation matrice and
    //   render the individual body parts.
    ///////////////////////////////////////////////////////////

    /**
     * what needs to be done still?
     * - draw the eye
     * - move beak up and down during animate
     * - draw circles representing joints
     * - rotate joints
     * - make spinners for each joint
     */

    // Draw our hinged object
    const float BODY_WIDTH = 140.0f;
    const float BODY_LENGTH = 200.0f;

    // Push the current transformation matrix on the stack
    glPushMatrix();
        
        // Draw the body
        glPushMatrix();
        {
            // Scale to size of body
            glScalef(BODY_WIDTH, BODY_LENGTH, 1.0);

            // Set the colour to green
            glColor3f(0.0, 1.0, 0.0);

            // Draw the body
            Point bodyPoints[] = {{-0.3f, 0.5f}, {0.3f, 0.5f},

                              {0.5f, -0.5f}, {0.2f, -0.7f},

                              {-0.2f, -0.7f}, {-0.5f, -0.5f}};
            drawPolygon(6, bodyPoints);

            drawArm();
            drawLeg(1.0, 0);
            drawLeg(-1.0, 1);
            drawHead();
        }
        glPopMatrix();


    // Retrieve the previous state of the transformation stack
    glPopMatrix();


    // Execute any GL functions that are in the queue just to be safe
    glFlush();

    // Now, show the frame buffer that we just drew into.
    // (this prevents flickering).
    glutSwapBuffers();
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

float LEG_OFFSET_X = 1.0f/5.0f;
float LEG_OFFSET_Y = -0.5;

float LEG_WIDTH_REL_TO_BODY = 0.2f;
float LEG_LENGTH_REL_TO_BODY = 0.3f;

float FOOT_OFFSET_X = -0.5f;
float FOOT_OFFSET_Y = -0.5f;

float FOOT_WIDTH_REL_TO_LEG = 2.0f;
float FOOT_LENGTH_REL_TO_LEG = 1.0f/3.0f;

void drawLeg(float leftOrRight, int rotationIndex){
    // Draw a leg
    glPushMatrix();
    {
        // Move the leg to the joint hinge
        glTranslatef(leftOrRight * LEG_OFFSET_X, LEG_OFFSET_Y, 0.0);
        // Rotate along the hinge
        glRotatef(joint_rot[rotationIndex], 0.0, 0.0, 1.0);
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
            glScalef(FOOT_WIDTH_REL_TO_LEG, FOOT_LENGTH_REL_TO_LEG, 1.0);
            glColor3f(1.0, 1.0, 0.0);
            drawSquare(1.0);
        }
        glPopMatrix();

        // draw the ankle joint
        glPushMatrix();
        {
            glScalef(1.0/3.0, 0.1, 1.0);
            glTranslatef(0.0, -1.0, 0.0);
            glColor3f(0.0, 0.0, 0.0);
            drawCircle(1.0);
        }
        glPopMatrix();

    }glPopMatrix();
}

const float DEG2RAD = 3.14159 / 180;

void drawCircle(float radius){
    glBegin(GL_LINE_LOOP);
    for (int i = 0; i < 360; i++){
        float degInRad = i * DEG2RAD;
        glVertex2f(cos(degInRad) * radius, sin(degInRad) * radius);
    }
    glEnd();
}

float getDegrees(float rad, float min, float max){
    double joint_rot_t = (sin(rad) + 1.0) / 2.0;
    return joint_rot_t * min + (1 - joint_rot_t) * max;
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

void drawArm(){
	// draw arm
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

void drawHead(){
	// draw head
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
