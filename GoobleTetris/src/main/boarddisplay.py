'''
Created on 2012-02-01

@author: Gooble
'''

class BoardDisplay(object):
    
    def __init__(self, board):
        self.board = board
    
    def get_pieces_string(self):
        result = ""
        for row in self.board.get_pieces():
            for piece in row:
                result += str(piece) + " "
            result += "\n" 
        return result