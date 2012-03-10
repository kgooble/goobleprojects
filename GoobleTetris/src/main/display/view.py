'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.drawing.centerall import CenterAll

class View(object):
    def __init__(self):
        self.drawables = None
        
    def display(self, screen):
        if self.drawables is None:
            self.drawables = self.create_drawables()
            CenterAll(self.drawables).in_screen_sized(screen.get_width(), screen.get_height())
            
        for d in self.drawables:
            d.draw(screen)
            
    def create_drawables(self):
        pass
