'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.state import State
import main.display.buttonhandler
from main.display.howto.view import HowToPlayView
from main.display.backaction import BackAction

ACTIONS={'Back':BackAction()}

class HowToPlayState(State):
    def __init__(self, eventhandler=main.display.buttonhandler.ButtonClickHandler(None, actions=ACTIONS), view=HowToPlayView()):
        State.__init__(self, eventhandler, view)
        
    def preprocess(self):
        self.eventhandler.buttons = self.view.buttons