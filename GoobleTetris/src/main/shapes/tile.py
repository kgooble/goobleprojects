'''
Created on 2012-01-29

@author: Gooble
'''
from main.shapes import shapetype

class Tile(object):

    def __init__(self, shapetype):
        self.shapetype = shapetype
    
    def is_empty(self):
        return self.shapetype == shapetype.NONE
    
    def __repr__(self):
        if self.is_empty():
            return "0"
        return "1"
    
EMPTY_TILE = Tile(shapetype.NONE)
T_TILE = Tile(shapetype.T)
I_TILE = Tile(shapetype.I)
J_TILE = Tile(shapetype.J)
L_TILE = Tile(shapetype.L)
S_TILE = Tile(shapetype.S)
Z_TILE = Tile(shapetype.Z)
O_TILE = Tile(shapetype.O)
ALL_TILES = [T_TILE, I_TILE, J_TILE, L_TILE, S_TILE, Z_TILE, O_TILE]