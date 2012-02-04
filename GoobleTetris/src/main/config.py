'''
Created on 2012-01-29

@author: Gooble
'''
from main.shapes.tile import EMPTY_TILE, T_TILE

class Configuration(object):
    
    def create(self, rowlist):
        pieces = []
        
        for row_index in range (0, len(rowlist)):
            pieces.append([])
            column = rowlist[row_index].split(" ")
            pieces[row_index] = []
            for col_index in range (0, len(column)):
                tile = column[col_index]
                pieces[row_index].append(TILE_MAP[tile])
                
        return pieces
    
TILE_MAP = {"0" : EMPTY_TILE, "1" : T_TILE}