'''
Created on 2012-02-01

@author: Gooble
'''
from main.shapes.spawn.spawn import AbstractSpawn
import random
from main.shapes.tile import ALL_TILES
from main.shapes.shape import Shape

class RandomSpawn(AbstractSpawn):
    
    def __init__(self):
        n = random.Random().randint(0, len(ALL_TILES) - 1)
        self.next_shapetype = ALL_TILES[n]
    
    def create_shape(self):
        cur_shape = Shape(self.next_shapetype)
        n = random.Random().randint(0, len(ALL_TILES) - 1)
        self.next_shapetype = ALL_TILES[n]
        return cur_shape

    def get_next_shapetype(self):
        return self.next_shapetype