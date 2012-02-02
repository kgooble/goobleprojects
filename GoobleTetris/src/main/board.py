'''
Created on 2012-01-29

@author: Gooble
'''
from main.edges import Edges
from main.randomspawn import RandomSpawn

class Board(object):

    def __init__(self, pieces, spawner=RandomSpawn(), edges=Edges()):
        self.edges = edges
        self.pieces = pieces
        self.spawner = spawner
        
    def is_valid_move(self, old_points, new_points):
        for point in new_points:
            if (not self.pieces[point.row][point.col].is_empty() \
               and point not in old_points)\
               or self.edges.is_point_outside_boundary(point):
                return False
        
        return True
    
    def step(self):
        self.cur_shape = self.spawner.get_next_centered_shape(self.edges.get_width())
        for point in self.cur_shape.get_points():
            if not self.edges.is_point_outside_boundary(point):
                self.pieces[point.row][point.col] = self.cur_shape.get_tile()
    
    def get_pieces(self):
        return self.pieces