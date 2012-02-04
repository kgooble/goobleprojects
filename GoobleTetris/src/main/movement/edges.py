'''
Created on 2012-01-29

@author: Gooble
'''
from main.movement.direction import DOWN, LEFT, RIGHT, UP

NONE = {LEFT:-1000, RIGHT:1000, DOWN:1000}
DEFAULT = {LEFT: 0, RIGHT:9, DOWN:19, UP: 0}

class Edges(object):
    
    def __init__(self, edges=DEFAULT):
        self.edges = edges
        
    def get_edge(self, direction):
        if direction not in self.edges.keys():
            return DEFAULT[direction]
        
        return self.edges[direction]
    
    def is_point_outside_boundary_except_top(self, point):
        return point.col > self.get_edge(RIGHT)\
            or point.col < self.get_edge(LEFT)\
            or point.row > self.get_edge(DOWN)
            
    def is_point_within_top_boundary(self, point):
        return point.row >= self.get_edge(UP)
    
    def is_point_outside_boundary(self, point):
        return self.is_point_outside_boundary_except_top(point)\
            or not self.is_point_within_top_boundary(point)
            
    def are_points_outside_boundary(self, points):
        for point in points:
            if self.is_point_outside_boundary(point):
                return True
        return False
            
    def get_width(self):
        return self.get_edge(RIGHT) - self.get_edge(LEFT) + 1
