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
    
    def draw(self, screen):
        if self.drawables is None:
            self.create_drawables_using_factory()
            
        for d in self.drawables:
            d.draw(screen)
            
    def create_drawables_using_factory(self, factory=DrawableFactory()):
        if self.drawables is None:
            image = factory.create_image(self.imagefile, self.x, self.y)
            text = factory.create_text(self.label, self.x + image.get_width() + PADDING, 0)
            text.y = Center(start=image.get_y(), end=image.get_y()+image.get_height()).object_with_length(text.get_height())
            self.drawables = [image, text]
            
        return self.drawables
    
    def get_width(self):
        if self.drawables is None:
            self.create_drawables_using_factory()
        return self.drawables[0].get_width() + PADDING + self.drawables[1].get_width()
    
    def get_height(self):
        if self.drawables is None:
            self.create_drawables_using_factory()
        return max(self.drawables[0].get_height(), self.drawables[1].get_height())
    
    def set_x(self, x):
        distance_between_text_and_rect = self.drawables[1].get_x() - self.drawables[0].get_x()
        self.drawables[0].set_x(x)
        self.drawables[1].set_x(x + distance_between_text_and_rect)
        self.x = x
    
    def set_y(self, y):
        distance_between_text_and_rect = self.drawables[1].get_y() - self.drawables[0].get_y()
        self.drawables[0].set_y(y)
        self.drawables[1].set_y(y + distance_between_text_and_rect)
        self.y = y