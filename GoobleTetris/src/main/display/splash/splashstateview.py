'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.drawing.textbutton import TextButton
from main.display.game.draw import DrawBoard
from main.display.game.gameeventhandler import GameEventHandler
from main.display.game.gamestate import GameState
from main.display.howto.howtoplaystate import HowToPlayState
from main.display.quitplushandler import QuitPlusHandler
from main.display.settings.settingsstate import SettingsState
from main.display.taskthread import TaskThread
from main.display.view import View
from main.gameplay.game import Game
    
class StartGameButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "Start Game")
        
    def get_state(self):
        game = Game()
        return GameState(QuitPlusHandler(GameEventHandler(game)), 
                         DrawBoard(game), TaskThread(game))
    
class HowToPlayButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "How To Play")
    def get_state(self):
        return HowToPlayState()
    
class SettingsButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "Settings")
    def get_state(self):
        return SettingsState()


class SplashStateView(View):
    def create_drawables(self):
        return [StartGameButton(), HowToPlayButton(), SettingsButton()]