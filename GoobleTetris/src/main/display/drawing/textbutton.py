'''
Created on 2012-03-05

@author: Gooble
'''
from main.display.drawing.factory import DrawableFactory

PADDING = 10

class TextButton(object):
    
    def __init__(self, label, x, y):
        self.label = label
        self.x = x
        self.y = y
        self.drawables = None
        
    def createDrawablesUsingFactory(self, factory=DrawableFactory()):
        if self.drawables is None:
            rect = factory.createRectangle(self.x, self.y)
            text = factory.createText(self.label, self.x + PADDING, self.y + PADDING)
            rect.width = text.width + 2 * PADDING
            rect.height = text.height + 2 * PADDING
            self.drawables = [rect, text]
            
        return self.drawables
    
    def get_width(self):
        if self.drawables is None:
            raise AttributeError
        
        return self.drawables[0].width 
    
    def get_height(self):
        if self.drawables is None:
            raise AttributeError
        
        return self.drawables[0].height