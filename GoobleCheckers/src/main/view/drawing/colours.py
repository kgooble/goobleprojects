'''
Created on 2012-02-26

@author: Gooble
'''
from main.game import origin

RED = [255,0,0]
BLACK = [0,0,0]
WHITE = [255,255,255]
BLUE = [0,0,255]
MEDIUMGRAY = [150,150,150]

class Colours(object):
    
    def background(self, row, col):
        if (row + col) % 2 == 0:
            return WHITE
        else:
            return BLUE
        
    def piece(self, piece):
        if piece is not None and piece.get_origin() == origin.BOTTOM:
            return RED
        elif piece is not None:
            return BLACK

