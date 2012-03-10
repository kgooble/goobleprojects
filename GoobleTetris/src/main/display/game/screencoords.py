'''
Created on 2012-02-04

@author: Gooble
'''

class ScreenCoords(object):
    
    def __init__(self, row, col, tile_width=20):
        self.row = row
        self.col = col
        self.tile_width = tile_width

    def get_start_x(self):
        return self.col * self.tile_width
    
    def get_start_y(self):
        return self.row * self.tile_width
    
    def get_end_x(self):
        return (self.col + 1) * self.tile_width
    
    def get_end_y(self):
        return (self.row + 1) * self.tile_width
    
    def get_width(self):
        return self.tile_width
    
    def get_height(self):
        return self.tile_width