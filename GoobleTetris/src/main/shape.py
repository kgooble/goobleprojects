'''
Created on 2012-01-31

@author: Gooble
'''

class Shape(object):
    
    def __init__(self, shapetype):
        self.shapetype = shapetype
        self.points = shapetype.get_initial_points()
    
    def get_points(self):
        return self.points

    def set_position(self, points):
        self.points = points

