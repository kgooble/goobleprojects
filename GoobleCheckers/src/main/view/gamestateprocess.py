'''
Created on 2012-02-26

@author: Gooble
'''
import pygame
from main.view.eventhandler import EventHandler

class ProcessGameState(EventHandler):
    
    def __init__(self, game):
        self.game = game
    
    def handle(self, event):
        pos = EventHandler.handle(self, event)
        if self.game.is_computers_turn():
            return
        self.handle_input(event, pos)
        return pos

    def handle_input(self, event, pos):
        if event.type == pygame.MOUSEBUTTONDOWN:
            self.game.select_piece(pos)
        if event.type == pygame.MOUSEBUTTONUP:
            self.game.release_piece(pos)