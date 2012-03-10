'''
Created on 2012-03-09

@author: Gooble
'''
from main.display.draw import DrawBoard
from main.display.drawing.textbutton import TextButton
from main.display.gameeventhandler import GameEventHandler
from main.display.gamestate import GameState
from main.display.state import State
from main.display.taskthread import TaskThread
from main.gameplay.game import Game
import pygame
from main.display.drawing.centerall import CenterAll


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
        return [TextButton("Start Game"), TextButton("How To Play"), TextButton("Settings")]

class SplashState(State):
    
    def __init__(self, eventhandler=None, view=SplashStateView()):
        self.view = view
            
    def process(self, event):
        if event.type == pygame.MOUSEBUTTONDOWN:
            game = Game()
            return GameState(GameEventHandler(game), DrawBoard(game), TaskThread(game))
        return self