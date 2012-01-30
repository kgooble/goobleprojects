'''
Created on 2012-01-29

@author: Gooble
'''

class Board(object):

    def __init__(self, pieces):
        self.pieces = pieces
        
    def is_valid_position(self, points):
        for point in points:
            if not self.pieces[point.row][point.col].is_empty():
                return False
        
        return True