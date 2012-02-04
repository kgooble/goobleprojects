'''
Created on 2012-01-29

@author: Gooble
'''
from main.movement.transform.point import Point

class ShapeType(object):
    
    def __init__(self, initial_points):
        self.initial_points = initial_points
    
    def get_initial_points(self):
        return self.initial_points
    
    
NONE = ShapeType([])
T = ShapeType([Point(0, 0),
               Point(0, -1),
               Point(-1, 0),
               Point(0, 1)])
LINE = ShapeType([Point(0, 0),
                  Point(-1, 0),
                  Point(1, 0),
                  Point(2, 0)])