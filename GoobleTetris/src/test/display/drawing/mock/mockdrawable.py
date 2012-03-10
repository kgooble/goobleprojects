'''
Created on 2012-03-04

@author: Gooble
'''

class MockDrawable(object):
    def __init__(self, text, x, y):
        self.text = text
        self.x = x
        self.y = y
        
    def get_width(self):
        return self.width
    
    def get_height(self):
        return self.height
    
    def set_x(self, x):
        self.x = x
        
    def set_y(self, y):
        self.y = y
        
    def get_y(self):
        return self.y
    
    def get_x(self):
        return self.x
    