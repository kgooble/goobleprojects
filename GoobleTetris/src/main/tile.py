'''
Created on 2012-01-29

@author: Gooble
'''

import main.shapetype

class Tile(object):

    def __init__(self, shapetype):
        self.shapetype = shapetype
    
    def is_empty(self):
        return self.shapetype == main.shapetype.NONE
    
    def __repr__(self):
        if self.is_empty():
            return "0"
        return "1"
    
EMPTY_TILE = Tile(main.shapetype.NONE)
T_TILE = Tile(main.shapetype.T)