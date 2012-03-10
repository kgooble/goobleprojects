'''
Created on 2012-02-04

@author: Gooble
'''
from main.shapes.tile import EMPTY_TILE, I_TILE, J_TILE, O_TILE, S_TILE, T_TILE, Z_TILE, L_TILE

BLACK  = [   0,   0,   0]
CYAN   = [   0, 255, 255]
BLUE   = [   0,   0, 255]
ORANGE = [ 255, 165,   0]
YELLOW = [ 255, 255,   0]
GREEN  = [   0, 255,   0]
PURPLE = [ 128,   0, 128]
RED    = [ 255,   0,   0] 
WHITE  = [ 255, 255, 255]

TILE_COLORS = {EMPTY_TILE : BLACK,
               I_TILE :     CYAN,
               J_TILE :     BLUE,
               O_TILE :     YELLOW,
               S_TILE :     GREEN,
               T_TILE :     PURPLE,
               Z_TILE :     RED,
               L_TILE :     ORANGE}    