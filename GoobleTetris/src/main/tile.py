'''
Created on 2012-01-29

@author: Gooble
'''

import main.shapetype

class Tile(object):

    def __init__(self, shapetype):
        self.shapetype = shapetype
    
    def is_empty(self):
        return self.shapetype == main.shapetype.ShapeType.NONE
    
EMPTY_TILE = Tile(main.shapetype.ShapeType.NONE)
T_TILE = Tile(main.shapetype.ShapeType.T)