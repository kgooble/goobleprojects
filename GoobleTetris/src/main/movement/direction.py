'''
Created on 2012-01-29

@author: Gooble
'''

class Direction(object):
    
    def __init__(self, row_dir, col_dir):
        self.row_dir = row_dir
        self.col_dir = col_dir

LEFT = Direction(0, -1)
RIGHT = Direction(0, 1)
DOWN = Direction(1, 0)
UP = Direction(-1, 0)