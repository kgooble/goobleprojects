'''
Created on 2012-02-01

@author: Gooble
'''
from main.shape import Shape
from main.spawn import AbstractSpawn

class OneShapeSpawn(AbstractSpawn):
    
    def __init__(self, shapetypetile):
        self.tile = shapetypetile
    
    def create_shape(self):
        return Shape(self.tile)
