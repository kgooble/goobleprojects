'''
Created on 2012-01-29

@author: Gooble
'''

class Direction(object):
    
    def __init__(self, x_dir, y_dir):
        self.x_dir = x_dir
        self.y_dir = y_dir

LEFT = Direction(-1, 0)
RIGHT = Direction(1, 0)
DOWN = Direction(0, 1)