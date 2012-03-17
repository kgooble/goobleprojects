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
    
    def do_action(self, info, state):
        info['predictions.enabled'] = self.on
        state.update_view(info)


class ChangeLevel(object):
    def __init__(self, inc_or_dec):
        self.inc_or_dec = inc_or_dec

    def do_action(self, info, state):
        if 'start.level' in info.keys():
            curr_level = info['start.level']
        else:
            curr_level = 1
        info['start.level'] = self.get_next_level(curr_level)
        state.update_view(info)
        
    def get_next_level(self, curr_level):
        new_level = curr_level + self.inc_or_dec
        
        if new_level > 20:
            new_level = 20
        elif new_level < 1:
            new_level = 1
        
        return new_level

ACTIONS={'Back':BackAction(),
         'TurnOnPredictions':TogglePredictionsAction(True),
         'TurnOffPredictions':TogglePredictionsAction(False),
         'DecrementLevel':ChangeLevel(-1),
         'IncrementLevel':ChangeLevel(+1)}

class SettingsState(main.display.state.State):
    def __init__(self, 
                 eventhandler=ButtonClickHandler(None, actions=ACTIONS), 
                 view=main.display.settings.view.SettingsView()):
        main.display.state.State.__init__(self, eventhandler, view)
        
    def preprocess(self):
        self.eventhandler.buttons = self.view.buttons
        
    def update_view(self, info):
        self.view.create_layout(info)