'''
Created on 2012-02-04

@author: Gooble
'''
from main.shapes.tile import EMPTY_TILE

class RowShift(object):
    
    def __init__(self, pieces):
        self.pieces = pieces
        
    def down(self, start_row):
        width = len(self.pieces[0])
        
        for i in range(start_row, 0, -1):
            self.pieces[i] = self.pieces[i - 1]
            
        self.pieces[0] = []
        for i in range(0, width):
            self.pieces[0].append(EMPTY_TILE)
        
        return self.pieces