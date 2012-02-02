'''
Created on 2012-02-01

@author: Gooble
'''

from main.direction import LEFT, RIGHT, UP, DOWN
from main.edges import Edges

class EdgesFromPieces(object):
    
    def __init__(self, pieces):
        self.pieces = pieces
        
    def create_edges(self):
        width = len(self.pieces[0])
        height = len(self.pieces)
        return Edges({LEFT:0, RIGHT:width-1, UP:0, DOWN:height-1})
