'''
Created on 2011-10-05

@author: Gooble
'''
from main.game.board import Board
from main.game.piece import Piece
from main.game.gameplay import GamePlay

class TestBoard(object):

    def __init__(self, game=None):
        if game is None:
            self.game = GamePlay()
        else:
            self.game = game
            
        self.clear_board()
        
    def clear_board(self):
        self.game.state.num_bottom_pieces = 0
        self.game.state.num_top_pieces = 0
        self.game.board.pieces = [[None] * Board.DEFAULT_WIDTH for row in self.game.board.pieces]
        
    def place_piece(self, row, col, origin):
        self.game.set_piece(row, col, Piece(origin))
        
    def place_king(self, row, col, origin):
        self.place_piece(row, col, origin);
        self.game.board.get_piece(row, col).set_king(True)
