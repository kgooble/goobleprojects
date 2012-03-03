'''
Created on 2011-10-10

@author: Gooble
'''
from main.game.board import Board

class BoardDisplay(object):

    def __init__(self, board):
        self.board = board
        
    def print_piece_code(self, piece):
        if piece is not None and piece.is_king():
            print(piece.get_origin().desc[:1].lower(), end=' ')
        elif piece is not None and not piece.is_king():
            print(piece.get_origin().desc[:1].upper(), end=' ')
        else:
            print(' ', end=' ')
            
            
    def print_board(self):
        for row_of_pieces in self.board.pieces:
            [self.print_piece_code(piece) for piece in row_of_pieces]
            print()
            
            
if __name__ == '__main__':
    BoardDisplay(Board()).print_board()