'''
Created on 2012-03-10

@author: Gooble
'''
import main.display.state
import main.display.settings.view
from main.display.buttonhandler import ButtonClickHandler
from main.display.backaction import BackAction

class TogglePredictionsAction(object):
    
    def __init__(self, on):
        self.on = on
    
    def do_action(self, info):
        info['predictions.enabled'] = self.on


ACTIONS={'Back':BackAction(),
         'TurnOnPredictions':TogglePredictionsAction(True),
         'TurnOffPredictions':TogglePredictionsAction(False)}

class SettingsState(main.display.state.State):
    def __init__(self, 
                 eventhandler=ButtonClickHandler(None, actions=ACTIONS), 
                 view=main.display.settings.view.SettingsView()):
        main.display.state.State.__init__(self, eventhandler, view)
        
    def preprocess(self):
        self.eventhandler.buttons = self.view.buttons