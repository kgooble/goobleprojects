'''
Created on 2012-02-04

@author: Gooble
'''

import pygame
from main.display.tilecolors import WHITE, TILE_COLORS
from main.display.screencoords import ScreenCoords

PADDING = 20
SCREEN_WIDTH = 500
SCREEN_HEIGHT = 600
TILE_WIDTH = SCREEN_HEIGHT / 20
STATS_START_X = TILE_WIDTH * 10

class DrawBoard(object):
    def __init__(self, game, screen):
        self.game = game
        self.screen = screen

    def write_score(self):
        pygame.draw.line(self.screen, WHITE, [STATS_START_X, 0], [STATS_START_X, 600], 5)
        font = pygame.font.Font(None, 25)
        text = font.render("Score", True, WHITE)
        self.screen.blit(text, [STATS_START_X + PADDING, 200])
        text = font.render(str(self.game.get_score()), True, WHITE)
        self.screen.blit(text, [STATS_START_X + PADDING, 250])
    
    
    def draw_piece(self, i, j):
        piece = self.game.get_pieces()[i][j]
        coords = ScreenCoords(row=i, col=j, tile_width=TILE_WIDTH)
        color = TILE_COLORS[piece]
        pygame.draw.rect(self.screen, color, [coords.get_start_x(), 
                                         coords.get_start_y(), 
                                         coords.get_width(), 
                                         coords.get_height()])
        if not piece.is_empty():
            pygame.draw.rect(self.screen, WHITE, [coords.get_start_x(), 
                                             coords.get_start_y(), 
                                             coords.get_width(), 
                                             coords.get_height()], 1)
    
    def draw_board(self):
        pieces = self.game.get_pieces()
        for i in range(0, len(pieces)):
            for j in range(0, len(pieces[0])):
                self.draw_piece(i, j)