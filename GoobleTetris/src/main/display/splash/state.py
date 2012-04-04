'''
Created on 2012-03-09

@author: Gooble
'''
import main.display.state
import main.display.splash.view
import main.display.settings.state
import main.display.howto.state
import main.display.game.state
import main.gameplay.game
import main.display.game.eventhandler
from main.display.taskthread import TaskThread
from main.display.game.draw import DrawBoard
    
class SettingsAction(object):
    def do_action(self, info, state):
        return main.display.settings.state.SettingsState()
    
class HowToPlayAction(object):
    def do_action(self, info, state):
        return main.display.howto.state.HowToPlayState()
    
class StartGameAction(object):
    def do_action(self, info, state):
        predict = False
        level = 1
        if 'predictions.enabled' in info.keys():
            predict = info['predictions.enabled']
        if 'start.level' in info.keys():
            level = info['start.level']
        game = main.gameplay.game.Game(level=level)
        return main.display.game.state.GameState(
                         main.display.game.eventhandler.GameEventHandler(game),
                         DrawBoard(game, predicting=predict),
                         TaskThread(game))
        
        
ACTIONS = {'Settings':SettingsAction(),
             'HowToPlay':HowToPlayAction(),
             'Start':StartGameAction()}

class SplashState(main.display.state.State):
    
    def __init__(self, eventhandler=None, view=None):
        if eventhandler is None:
            eventhandler = main.display.buttonhandler.ButtonClickHandler(None,ACTIONS)
        if view is None:
            view = main.display.splash.view.SplashView()
            
        main.display.state.State.__init__(self, eventhandler, view)
            
    def preprocess(self):
        self.eventhandler.buttons = self.view.buttons
