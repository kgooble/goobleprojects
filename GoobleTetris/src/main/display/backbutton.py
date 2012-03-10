'''
Created on 2012-03-10

@author: Gooble
'''
import main.display.splash.state
from main.display.drawing.textbutton import TextButton

class BackButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "Back")

    def get_state(self):
        return main.display.splash.state.SplashState()