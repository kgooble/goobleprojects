'''
Created on 2012-03-04

@author: Gooble
'''
import pygame
import main.display.splash.state
from main.movement.direction import LEFT, RIGHT
from main.display.generaleventshandler import GeneralEventsHandler

class GameEventHandler(GeneralEventsHandler):
    def __init__(self, game):
        self.game = game
    
    def process(self, event, info):
        if self.game.is_game_over():
            if event.type == pygame.KEYUP and event.key == pygame.K_ESCAPE:
                return main.display.splash.state.SplashState()
        
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                self.game.move(LEFT)
            if event.key == pygame.K_RIGHT:
                self.game.move(RIGHT)
            if event.key == pygame.K_DOWN:
                self.game.speed_up()
            if event.key == pygame.K_UP:
                self.game.rotate()
            if event.key == pygame.K_SPACE:
                self.game.drop()
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_DOWN:
                self.game.slow_down()
                
