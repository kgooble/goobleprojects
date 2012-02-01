'''
Created on 2012-01-31

@author: Gooble
'''
from main.translation import Translation
from main.direction import LEFT

class Shape(object):
    
    def __init__(self, shapetype):
        self.shapetype = shapetype
        self.points = shapetype.get_initial_points()
    
    def get_points(self):
        return self.points

    def move_left(self):
        self.points = Translation().in_direction(self.points, LEFT)

