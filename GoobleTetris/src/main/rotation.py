'''
Created on 2012-01-30

@author: Gooble
'''

from main.point import Point

class Rotation(object):

    def __init__(self, points):
        self.points = points
        
    def get_new_points(self):
        new_points = []
        for point in self.points:
            new_points.append(Point(-point.col, point.row))
                
        return new_points