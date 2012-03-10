'''
Created on 2012-03-05

@author: Gooble
'''
from main.display.drawing.factory import DrawableFactory

PADDING = 10

class TextButton(object):
    
    def __init__(self, label, x=0, y=0):
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
            rect = factory.create_rectangle(self.x, self.y)
            text = factory.create_text(self.label, self.x + PADDING, self.y + PADDING)
            rect.width = text.get_width() + 2 * PADDING
            rect.height = text.get_height() + 2 * PADDING
            self.drawables = [rect, text]
            
        return self.drawables
    
    def get_width(self):
        if self.drawables is None:
            self.create_drawables_using_factory()
        return self.drawables[0].width
    
    def get_height(self):
        if self.drawables is None:
            self.create_drawables_using_factory()
        return self.drawables[0].height
    
    def contains(self, point):
        if self.drawables[0].contains(point):
            return True
        return False
    
    def set_x(self, x):
        distance_between_text_and_rect = self.drawables[1].x - self.drawables[0].x
        self.drawables[0].x = x
        self.drawables[1].x = x + distance_between_text_and_rect
        
    def set_y(self, y):
        distance_between_text_and_rect = self.drawables[1].y - self.drawables[0].y
        self.drawables[0].y = y
        self.drawables[1].y = y + distance_between_text_and_rect