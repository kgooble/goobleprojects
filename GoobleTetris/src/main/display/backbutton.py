'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.drawing.textbutton import TextButton
import main.display.splashstate

class BackButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "Back")

    def get_state(self):
        return main.display.splashstate.SplashState()