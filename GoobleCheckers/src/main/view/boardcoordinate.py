'''
Created on 2011-11-26

@author: Gooble
'''
from main.view.dimensions import DEFAULT_COLS, DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_ROWS

class BoardCoordinate(object):
    
    def __init__(self, 
                 canvas_size=(DEFAULT_WIDTH, DEFAULT_HEIGHT),
                 board_size=(DEFAULT_ROWS, DEFAULT_COLS)):
        self.canvas_width = canvas_size[0]
        self.canvas_height = canvas_size[1]
        self.board_width = board_size[0]
        self.board_height = board_size[1]
    
    def get_from(self, x, y):
        tile_width = self.canvas_width / self.board_width
        tile_height = self.canvas_height / self.board_height
        
        return int(y / tile_height), int(x / tile_width)
    
