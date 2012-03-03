'''
Created on 2011-11-27

@author: Gooble
'''
from main.view.boardcoordinate import BoardCoordinate

class Slotting(object):
    
    def __init__(self, game):
        self.game = game
        self.start_row = None
        self.start_col = None
        
    def select_piece(self, event):
        self.start_row, self.start_col = BoardCoordinate().get_from(event[0], event[1])

    def release_piece(self, event):
        if self.is_holding_piece():
            to_row, to_col = BoardCoordinate().get_from(event[0], event[1])
            self.game.move_piece((self.start_row, self.start_col), (to_row, to_col))
            self.start_row = None
            self.start_col = None
        
    def is_holding_piece(self, row=None, col=None):
        if row is not None and col is not None:
            return self.start_row == row and self.start_col == col
        return self.start_row is not None and self.start_col is not None
