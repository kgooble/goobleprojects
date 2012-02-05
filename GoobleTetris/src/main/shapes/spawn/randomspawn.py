'''
Created on 2012-02-01

@author: Gooble
'''
from main.shapes.spawn.spawn import AbstractSpawn
import random
from main.shapes.tile import ALL_TILES
from main.shapes.shape import Shape

class RandomSpawn(AbstractSpawn):
    
    def create_shape(self):
        n = random.Random().randint(0, len(ALL_TILES) - 1)
        return Shape(ALL_TILES[n])
