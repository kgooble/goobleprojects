'''
Created on 2012-01-29

@author: Gooble
'''

import main.direction

class Edges(object):
    
    def __init__(self, edges={}):
        self.edges = edges
        
    def get_edge(self, direction):
        if direction not in self.edges.keys():
            return NONE[direction]
        
        return self.edges[direction]
    
    def is_point_outside_boundary(self, point):
        return point.col > self.get_edge(main.direction.RIGHT)\
            or point.col < self.get_edge(main.direction.LEFT)\
            or point.row > self.get_edge(main.direction.DOWN)

NONE = {main.direction.LEFT:-1000, main.direction.RIGHT:1000, main.direction.DOWN:1000}