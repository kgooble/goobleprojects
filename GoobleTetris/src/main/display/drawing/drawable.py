'''
Created on 2012-03-10

@author: Gooble
'''

class Drawable(object):
    def __init__(self, x, y):
        self.x = x
        self.y = y
        
    def get_x(self):
        return self.x
    
    def get_y(self):
        return self.y
        
    def set_x(self, x):
        self.x = x
        
    def set_y(self, y):
        self.y = y
        
    def contains(self, point):
        pointx = point[0]
        pointy = point[1]
        return self.get_x() <= pointx <= self.get_x() + self.get_width() \
           and self.get_y() <= pointy <= self.get_y() + self.get_height()