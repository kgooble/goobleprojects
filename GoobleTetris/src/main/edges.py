'''
Created on 2012-01-29

@author: Gooble
'''

import main.direction

NONE = {main.direction.LEFT:-1000, main.direction.RIGHT:1000, main.direction.DOWN:1000}
DEFAULT = {main.direction.LEFT: 0, main.direction.RIGHT:9, main.direction.DOWN:19, main.direction.UP: 0}

class Edges(object):
    
    def __init__(self, edges=DEFAULT):
        self.edges = edges
        
    def get_edge(self, direction):
        if direction not in self.edges.keys():
            return DEFAULT[direction]
        
        return self.edges[direction]
    
    def is_point_outside_boundary(self, point):
        return point.col > self.get_edge(main.direction.RIGHT)\
            or point.col < self.get_edge(main.direction.LEFT)\
            or point.row > self.get_edge(main.direction.DOWN)\
            or point.row < self.get_edge(main.direction.UP)
            
    def get_width(self):
        return self.get_edge(main.direction.RIGHT) - self.get_edge(main.direction.LEFT) + 1
