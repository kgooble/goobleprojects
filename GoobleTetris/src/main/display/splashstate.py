'''
Created on 2012-03-09

@author: Gooble
'''
from main.display.drawing.textbutton import TextButton
from main.display.state import State
from main.display.drawing.centerall import CenterAll
from main.display.quitplushandler import QuitPlusHandler
from main.display.buttonhandler import ButtonClickHandler
from main.gameplay.game import Game
from main.display.draw import DrawBoard
from main.display.gameeventhandler import GameEventHandler
from main.display.gamestate import GameState
from main.display.taskthread import TaskThread


class StartGameButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "Start Game")
    def get_state(self):
        game = Game()
        return GameState(QuitPlusHandler(GameEventHandler(game)), DrawBoard(game), TaskThread(game))

class SplashStateView(object):
    
    def __init__(self):
        self.drawables = None
    
    def display(self, screen):
        if self.drawables is None:
            self.drawables = self.create_drawables()
            CenterAll(self.drawables).in_screen_sized(screen.get_width(), screen.get_height())
        
        for d in self.drawables:
            d.draw(screen);
            
    def create_drawables(self):
        return [StartGameButton(), TextButton("How To Play"), TextButton("Settings")]

class SplashState(State):
    
    def __init__(self, eventhandler=QuitPlusHandler(ButtonClickHandler(None)), view=SplashStateView()):
        self.eventhandler = eventhandler
        self.eventhandler.state = self
        self.view = view
            
    def preprocess(self):
        self.eventhandler.otherhandler.buttons = self.view.drawables
