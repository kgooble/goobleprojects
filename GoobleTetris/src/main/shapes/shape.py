'''
Created on 2012-01-31

@author: Gooble
'''

class Shape(object):
    
    def __init__(self, tile):
        self.tile = tile
        self.points = tile.shapetype.get_initial_points()
    
    def get_points(self):
        return self.points

    def set_position(self, points):
        self.points = points

    def get_tile(self):
        return self.tile
