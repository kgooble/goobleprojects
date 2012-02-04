'''
Created on 2012-02-04

@author: Gooble
'''
from main.shapes.tile import EMPTY_TILE

class RowClearing(object):
    
    def __init__(self, pieces):
        self.pieces = pieces

    def clear_and_get_rows(self):
        height = len(self.pieces)
        width = len(self.pieces[0])
        rows_cleared = []
        
        for i in range (0, height):
            if self._is_row_full(self.pieces[i]):
                self._clear_row(width, i)
                rows_cleared.append(i)
                
        return rows_cleared

    def _is_row_full(self, row):
        for piece in row:
            if piece.is_empty():
                return False
        return True

    def _clear_row(self, width, i):
        for j in range(0, width):
            self.pieces[i][j] = EMPTY_TILE