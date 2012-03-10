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

class SplashState(State):
    
    def __init__(self, eventhandler=None, view=None):
        self.drawables = TextButton("Hello", 20, 20).createDrawablesUsingFactory()
    
    def display(self, screen):
        for d in self.drawables:
            d.draw(screen)
            
    def process(self, event):
        if event.type == pygame.MOUSEBUTTONDOWN:
            game = Game()
            return GameState(GameEventHandler(game), DrawBoard(game), TaskThread(game))
        return self