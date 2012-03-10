'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.state import State
from main.display.buttonhandler import ButtonClickHandler
from main.display.quitplushandler import QuitPlusHandler
from main.display.settingsstateview import SettingsStateView

class SettingsState(State):
    def __init__(self, eventhandler=QuitPlusHandler(ButtonClickHandler(None)), view=SettingsStateView()):
        State.__init__(self, eventhandler, view)
        
    def preprocess(self):
        self.eventhandler.otherhandler.buttons = self.view.buttons