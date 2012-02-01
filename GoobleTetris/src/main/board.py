'''
Created on 2012-01-29

@author: Gooble
'''
from main.edges import Edges

class Board(object):

    def __init__(self, pieces, edges=Edges()):
        self.edges = edges
        self.pieces = pieces
        
    def is_valid_move(self, old_points, new_points):
        for point in new_points:
            if (not self.pieces[point.row][point.col].is_empty() \
               and point not in old_points)\
               or self.edges.is_point_outside_boundary(point):
                return False
        
        return True