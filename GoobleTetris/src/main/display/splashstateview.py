'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.draw import DrawBoard
from main.display.drawing.textbutton import TextButton
from main.display.gameeventhandler import GameEventHandler
from main.display.gamestate import GameState
from main.display.howtoplaystate import HowToPlayState
from main.display.quitplushandler import QuitPlusHandler
from main.display.taskthread import TaskThread
from main.display.view import View
from main.gameplay.game import Game
from main.display.settingsstate import SettingsState
    
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