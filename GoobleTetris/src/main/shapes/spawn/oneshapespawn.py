'''
Created on 2012-02-01

@author: Gooble
'''
from main.shapes.spawn.spawn import AbstractSpawn
from main.shapes.shape import Shape

class OneShapeSpawn(AbstractSpawn):
    
    def __init__(self, shapetypetile):
        self.tile = shapetypetile

    def get_next_shapetype(self):
        return self.tile
    
    def create_shape(self):
        return Shape(self.tile)
