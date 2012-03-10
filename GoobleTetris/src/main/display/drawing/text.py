'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.drawing.drawable import Drawable

class DrawableText(Drawable):
    def __init__(self, text, x, y):
        Drawable.__init__(self, x, y)
        self.text = text
        
    def draw(self, screen):
        screen.blit(self.text, [self.x, self.y])
        
    def get_width(self):
        return self.text.get_width()
    
    def get_height(self):
        return self.text.get_height()
