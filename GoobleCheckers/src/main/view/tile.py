'''
Created on 2011-11-25

@author: Gooble
'''
import main.game.origin as origin
import pygame

class Tile(object):
    
    PIECE_COLOURS = {origin.TOP.desc:[255,0,0], origin.BOTTOM.desc:[0,0,0]}
    
    def __init__(self, piece, board, screen):
        self.piece = piece
        self.board = board
        self.screen = screen
        
    def draw_background(self, startx, starty, width, height, colour):
        pygame.draw.rect(self.screen, colour, [startx, starty, width, height])
            
    def draw_piece(self, width, height, x, y):
        if self.piece is not None:
            colour = Tile.PIECE_COLOURS[self.piece.get_origin().desc]
            pygame.draw.ellipse(self.screen, colour, [x, y, width, height])
            if self.piece.is_king():
                pygame.draw.line(self.screen, [255,255,255], [x, y], [x+width,y+height])
