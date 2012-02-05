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
I = ShapeType([Point(0, 0),
               Point(-1, 0),
               Point(1, 0),
               Point(2, 0)])
J = ShapeType([Point(0, 0),
               Point(0, -1),
               Point(0, 1),
               Point(1, 1)])
L = ShapeType([Point(0, 0),
               Point(0, -1),
               Point(0, 1),
               Point(1, -1)])
O = ShapeType([Point(0, 0),
               Point(0, 1),
               Point(-1, 0),
               Point(-1, 1)])
S = ShapeType([Point(0, 0),
               Point(-1, 0),
               Point(-1, 1),
               Point(0, -1)]) 
T = ShapeType([Point(0, 0),
               Point(0, -1),
               Point(-1, 0),
               Point(0, 1)])
Z = ShapeType([Point(0, 0),
               Point(-1, -1),
               Point(-1, 0),
               Point(0, 1)])
ALL = [I, J, L, O, S, T, Z]