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

#include "draw.h"

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

//////////////////////////////////////////////////////
// TODO: Add additional joint parameters here
//////////////////////////////////////////////////////

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
//void drawCircle(float radius);
//void drawPolygon(int n, Point points[]);
//void drawArm();
void drawLeg(float leftOrRight, int rotationIndex);
//void drawBeak();
//void drawHead();

float getDegrees(float rad, float min, float max);

// Return the current system clock (in seconds)
double getTime();


// ******************** FUNCTIONS ************************

int animateLeg1 = 0;
int animateLeg2 = 0;
int animateFoot1 = 0;
int animateFoot2 = 0;
int animateBeak = 0;
int animateHead = 0;
int animateArm = 0;


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

void doNothing(int){
    // do nothing; to be passed to checkboxes whose callbacks aren't meant to do anything
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
    glui->add_checkbox("Animate Leg 1", &animateLeg1, 0, doNothing);

    GLUI_Spinner *leg2Spinner
		= glui->add_spinner("Leg 2", GLUI_SPINNER_FLOAT, &joint_rot[1]);
    leg2Spinner->set_speed(0.1);
    leg2Spinner->set_float_limits(JOINT_MIN, JOINT_MAX, GLUI_LIMIT_CLAMP);
    glui->add_checkbox("Animate Leg 2", &animateLeg2, 0, doNothing);

    GLUI_Spinner *foot1Spinner
		= glui->add_spinner("Foot 1", GLUI_SPINNER_FLOAT, &joint_rot[2]);
    foot1Spinner->set_speed(0.1);
    foot1Spinner->set_float_limits(JOINT_MIN, JOINT_MAX, GLUI_LIMIT_CLAMP);
    glui->add_checkbox("Animate Foot 1", &animateFoot1, 0, doNothing);

    GLUI_Spinner *foot2Spinner
		= glui->add_spinner("Foot 2", GLUI_SPINNER_FLOAT, &joint_rot[2]);
	foot2Spinner->set_speed(0.1);
	foot2Spinner->set_float_limits(JOINT_MIN, JOINT_MAX, GLUI_LIMIT_CLAMP);
	glui->add_checkbox("Animate Foot 2", &animateFoot2, 0, doNothing);

    GLUI_Spinner *beakSpinner
		= glui->add_spinner("Beak", GLUI_SPINNER_INT, &beakDistance);
    beakSpinner->set_speed(1);
    beakSpinner->set_int_limits(BEAK_MIN, BEAK_MAX, GLUI_LIMIT_CLAMP);
    glui->add_checkbox("Animate Beak", &animateBeak, 0, doNothing);

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
    float rad = animation_frame * LEG_ROTATION_SPEED;
    if (animateLeg1 == 1)
    	joint_rot[0] = getDegrees(rad, JOINT_MIN, JOINT_MAX);

    if (animateLeg2 == 1)
    	joint_rot[1] = getDegrees(rad, JOINT_MIN, JOINT_MAX);

    if (animateFoot1 == 1)
    	joint_rot[2] = getDegrees(rad, JOINT_MIN, JOINT_MAX);
    
    if (animateBeak == 1)
    	beakDistance = getDegrees(BEAK_SEPARATION_SPEED * animation_frame, BEAK_MIN, BEAK_MAX);

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

const float BODY_WIDTH = 140.0f;
const float BODY_LENGTH = 200.0f;

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
     * - draw the eye (on head)
     * - draw circles representing joints
     * - rotate joints
     * - make spinners for each joint
     */

    // Draw our hinged object
    // Push the current transformation matrix on the stack
    glPushMatrix();
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


/**
 * data structure idea:
 *
 * PenguinComponent
 * int animateFlag
 * float rotationAngle
 * int separation
 * float speed
 *
 * float min
 * float max
 */

float getDegrees(float rad, float min, float max){
    double joint_rot_t = (sin(rad) + 1.0) / 2.0;
    return joint_rot_t * min + (1 - joint_rot_t) * max;
}
