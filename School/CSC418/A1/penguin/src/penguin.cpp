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
int animation_mode = 0;     // 0 = not playing animation; 1 = playing animation
int animation_frame = 0;      // Specify current frame of animation

// All joint parameters are in draw.h

// ***********  FUNCTION HEADER DECLARATIONS ****************


// Initialization functions
void initGlut(char* winName);
void initGlui();
void initGl();


// Callbacks for handling events in glut
void myReshape(int w, int h);
void animate();
void animation();
void refresh(); // refresh the screen after updating values in animation functions
void display(void);

// Callback for handling events in glui
void GLUI_Control(int id);

// Functions to help draw the object
float getSinDegrees(float rad, float min, float max);
float getCosDegrees(float rad, float min, float max);

// Return the current system clock (in seconds)
double getTime();


// ******************** FUNCTIONS ************************

// Flags to determine whether or not we should animate each part which are
// used by the GLUI checkboxes.
int animateLeg1 = 0;
int animateLeg2 = 0;
int animateFoot1 = 0;
int animateFoot2 = 0;
int animateBeak = 0;
int animateHead = 0;
int animateArm = 0;
float bodyPositionX = 0.0f;

// constants for the animation
const float MOVEMENT_SPEED = 3.0f;
const int CLOSE_TO_ZERO = 3;

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

// Animation button handler. called when "Play animation" checkbox is checked
void animationButton(int){
    glui->sync_live();
    animation_frame = 0;
    if (animation_mode == 1){
    	// Initializes the body position as off to the right
    	// so that the penguin can walk to the left during animation.
    	bodyPositionX = 50.0f;
        GLUI_Master.set_glutIdleFunc(animation);
    } else{
        GLUI_Master.set_glutIdleFunc(NULL);
    }
}

void doNothing(int){
    // do nothing; to be passed to checkboxes whose callbacks aren't meant to do anything
}

// Add a float spinner with the given name, pointer to the float (angle),
// the speed at which the spinner should move, and the minimum and maximum
// values for the angle.
void addFloatSpinner(char* name, float* angle, float speed, float minAngle, float maxAngle){
    GLUI_Spinner *joint_spinner
        = glui->add_spinner(name, GLUI_SPINNER_FLOAT, angle);
    joint_spinner->set_speed(speed);
    joint_spinner->set_float_limits(minAngle, maxAngle, GLUI_LIMIT_CLAMP);
}

// Initialize GLUI and the user interface
void initGlui()
{
    GLUI_Master.set_glutIdleFunc(NULL);

    // Create GLUI window
    glui = GLUI_Master.create_glui("Glui Window", 0, Win[0]+10, 0);

    /*
     * Add spinners and checkboxes for each degree of freedom.
     * The checkboxes determine whether or not that part should rotate
     * when the "animate" checkbox is on.
     *
     */

    addFloatSpinner("Leg 1", &leg1Rotation, 0.1, LIMB_MIN, LIMB_MAX);
    glui->add_checkbox("Animate Leg 1", &animateLeg1, 0, doNothing);

    addFloatSpinner("Leg 2", &leg2Rotation, 0.1, LIMB_MIN, LIMB_MAX);
    glui->add_checkbox("Animate Leg 2", &animateLeg2, 0, doNothing);

    addFloatSpinner("Foot 1", &foot1Rotation, 0.1, FOOT_MIN, FOOT_MAX);
    glui->add_checkbox("Animate Foot 1", &animateFoot1, 0, doNothing);

    addFloatSpinner("Foot 2", &foot2Rotation, 0.1, FOOT_MIN, FOOT_MAX);
	glui->add_checkbox("Animate Foot 2", &animateFoot2, 0, doNothing);

	addFloatSpinner("Arm", &armRotation, 0.1, LIMB_MIN, LIMB_MAX);
    glui->add_checkbox("Animate Arm", &animateArm, 0, doNothing);

    addFloatSpinner("Head", &headRotation, 0.1, HEAD_MIN, HEAD_MAX);
    glui->add_checkbox("Animate Head", &animateHead, 0, doNothing);

    /*
     * The beak spinner is an int value of the distance
     * between the top and bottom beaks
     *
     * The beak is also controlled by a checkbox
     */
    GLUI_Spinner *beakSpinner
		= glui->add_spinner("Beak", GLUI_SPINNER_INT, &beakDistance);
    beakSpinner->set_speed(1);
    beakSpinner->set_int_limits(BEAK_MIN, BEAK_MAX, GLUI_LIMIT_CLAMP);
    glui->add_checkbox("Animate Beak", &animateBeak, 0, doNothing);

    // Add button to specify animation mode 
    glui->add_separator();
    glui->add_checkbox("Animate Parts", &animate_mode, 0, animateButton);

    glui->add_checkbox("Play Animation", &animation_mode, 0, animationButton);

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

// Callback idle function for animating joint movements
void animate()
{
    // Update geometry
    float rad = animation_frame * ROTATION_SPEED;

    // update the rotation angle/separation distance of each part whose
    // checkbox is on
    if (animateLeg1 == 1)
    	leg1Rotation = getSinDegrees(rad, LIMB_MIN, LIMB_MAX);

    if (animateLeg2 == 1)
        leg2Rotation  = getSinDegrees(rad, LIMB_MIN, LIMB_MAX);

    if (animateFoot1 == 1)
    	foot1Rotation = getSinDegrees(rad, FOOT_MIN, FOOT_MAX);
    
    if (animateFoot2 == 1)
        foot2Rotation = getSinDegrees(rad, FOOT_MIN, FOOT_MAX);

    if (animateArm == 1)
        armRotation = getSinDegrees(rad, LIMB_MIN, LIMB_MAX);

    if (animateHead == 1)
        headRotation = getSinDegrees(rad, HEAD_MIN, HEAD_MAX);

    if (animateBeak == 1)
    	beakDistance = getSinDegrees(BEAK_SEPARATION_SPEED * animation_frame, BEAK_MIN, BEAK_MAX);

    refresh();
}

// Plays the animation
void animation(){

	float rad = animation_frame * ROTATION_SPEED * 2;
	if (animation_frame < 70){
		// penguin walking along flapping his beak
		bodyPositionX -= MOVEMENT_SPEED;
		leg1Rotation = getSinDegrees(rad, LIMB_MIN, LIMB_MAX);
		leg2Rotation = getCosDegrees(rad, LIMB_MIN, LIMB_MAX);
		foot1Rotation = getSinDegrees(rad, FOOT_MIN, FOOT_MAX);
		foot2Rotation = getCosDegrees(rad, FOOT_MIN, FOOT_MAX);
		armRotation = getSinDegrees(rad, LIMB_MIN, LIMB_MAX);
		beakDistance = getSinDegrees(BEAK_SEPARATION_SPEED * animation_frame, BEAK_MIN, BEAK_MAX);
	} else if (70 <= animation_frame && animation_frame < 100){
		bool stillMoving = false;
		// things slowly stop moving
		if (beakDistance > 0){
			beakDistance = getSinDegrees(BEAK_SEPARATION_SPEED * animation_frame, BEAK_MIN, BEAK_MAX);
		}
		if (abs(leg1Rotation) > CLOSE_TO_ZERO){
			leg1Rotation = getSinDegrees(rad, LIMB_MIN, LIMB_MAX);
			stillMoving = true;
		}
		if (abs(leg2Rotation) > CLOSE_TO_ZERO){
			leg2Rotation = getCosDegrees(rad, LIMB_MIN, LIMB_MAX);
			stillMoving = true;
		}
		if (abs(foot1Rotation) > CLOSE_TO_ZERO){
			foot1Rotation = getSinDegrees(rad, FOOT_MIN, FOOT_MAX);
			stillMoving = true;
		}
		if (abs(foot2Rotation) > CLOSE_TO_ZERO){
			foot2Rotation = getCosDegrees(rad, FOOT_MIN, FOOT_MAX);
			stillMoving = true;
		}
		if (abs(armRotation > CLOSE_TO_ZERO)){
			armRotation = getSinDegrees(rad, LIMB_MIN, LIMB_MAX);
		}
		if (stillMoving){
			bodyPositionX -= MOVEMENT_SPEED;
		}
	} else if (100 <= animation_frame && animation_frame < 110){
		// penguin stops, looks up
		rad = animation_frame * ROTATION_SPEED;
		headRotation = getCosDegrees(rad, HEAD_MIN, HEAD_MAX);
		if (beakDistance > 0){
			beakDistance = getSinDegrees(BEAK_SEPARATION_SPEED * animation_frame, BEAK_MIN, BEAK_MAX);
		}
	} else if (110 <= animation_frame && animation_frame < 116){
		// penguin appears shocked
		beakDistance += BEAK_SEPARATION_SPEED;
	}


    refresh();
}

void refresh(){
    // Update user interface
    glui->sync_live();

    // Tell glut window to update itself.  This will cause the display()
    // callback to be called, which renders the object (once you've written
    // the callback).
    glutSetWindow(windowID);
    glutPostRedisplay();

    animation_frame++;
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

    drawEntireBody();

    // Execute any GL functions that are in the queue just to be safe
    glFlush();

    // Now, show the frame buffer that we just drew into.
    // (this prevents flickering).
    glutSwapBuffers();
}

// get the rotation degrees in between the given minimum
// and maximum values, using sin
float getSinDegrees(float rad, float min, float max){
	double joint_rot_t = (sin(rad) + 1.0) / 2.0;
	return joint_rot_t * min + (1 - joint_rot_t) * max;
}

// same as above except uses cos
float getCosDegrees(float rad, float min, float max){
	double joint_rot_t = (cos(rad) + 1.0) / 2.0;
	return joint_rot_t * min + (1 - joint_rot_t) * max;
}
