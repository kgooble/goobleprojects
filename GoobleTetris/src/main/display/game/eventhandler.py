'''
Created on 2012-03-04

@author: Gooble
'''
import pygame
from main.movement.direction import LEFT, RIGHT

class GameEventHandler(object):
    def __init__(self, game):
        self.game = game
    
    def process(self, event):
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