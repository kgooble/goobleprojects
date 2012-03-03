'''
Created on 2011-10-09

@author: Gooble
'''
from main.game import origin

class Piece(object):
    
    def __init__(self, origin):
        self.origin = origin
        self.king = False
        
    def get_origin(self):
        return self.origin
    
    def is_king(self):
        return self.king
    
    def set_king(self, king):
        self.king = king
        
    def is_from_top(self):
        return self.origin == origin.TOP
    
    def is_from_bottom(self):
        return self.origin == origin.BOTTOM
        
