'''
Created on 2012-03-04

@author: Gooble
'''
from main.display.center import Center
from main.display.drawing.factory import DrawableFactory
PADDING = 10

class LabeledImage(object):
    def __init__(self, imagefile='', label='', x=0, y=0):
        self.imagefile = imagefile
        self.label = label
        self.x = x
        self.y = y
        self.drawables = None
        
    def createDrawablesUsingFactory(self, factory=DrawableFactory()):
        if self.drawables is None:
            image = factory.createImage(self.imagefile, self.x, self.y)
            text = factory.createText(self.label, self.x + image.width + PADDING, 0)
            text.y = Center(start=image.y, end=image.y+image.height).objectWithLength(text.height)
            self.drawables = [image, text]
            
        return self.drawables
    
    def get_width(self):
        return self.drawables[0].width + PADDING + self.drawables[1].width
    
    def get_height(self):
        return max(self.drawables[0].height, self.drawables[1].height) 