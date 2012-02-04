'''
Created on 2012-02-01

@author: Gooble
'''

class BoardDisplay(object):
    
    def __init__(self, board, pieces=None):
        self.board = board
        if not pieces:
            self.pieces = board.get_pieces()
        else:
            self.pieces = pieces
    
    def get_pieces_string(self):
        result = ""
        for row in self.pieces:
            for piece in row:
                result += str(piece) + " "
            result += "\n" 
        return result