'''
Created on 2012-02-26

@author: Gooble
'''
from main.game import origin
from main.game.piece import Piece

class MockGame(object):
    def __init__(self, slotting):
        self.slotting = slotting
        top_king = Piece(origin.TOP)
        top_king.set_king(True)
        bottom_king = Piece(origin.BOTTOM)
        bottom_king.set_king(True)
        self.pieces = [[Piece(origin.TOP), None, top_king], 
                       [Piece(origin.BOTTOM), None, bottom_king]]
        
    def get_tile_width(self):
        return 10
    def get_tile_height(self):
        return 15
    
    def get_num_rows(self):
        return len(self.pieces)
    
    def get_num_cols(self):
        return len(self.pieces[0])
    
    def get_piece(self, row, col):
        return self.pieces[row][col]
    
    def is_holding_piece(self, row, col):
        return self.slotting.is_holding_piece(row, col)
    
    def is_computers_turn(self):
        return False
    
    def is_game_over(self):
        return False