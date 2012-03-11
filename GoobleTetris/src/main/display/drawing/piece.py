'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.drawing.drawable import Drawable
from main.display.drawing.rectangle import DrawableRectangle
 
class DrawablePiece(Drawable):
    
    def __init__(self, x, y, width, height, colour, empty):
        Drawable.__init__(self, x, y)
        self.width = width
        self.height = height
        self.colour = colour
        self.empty = empty

    def draw(self, screen):
        DrawableRectangle(self.x, self.y, 
                          self.width, self.height, thickness=0, colour=self.colour).draw(screen)
        if not self.empty:
            DrawableRectangle(self.x, self.y, 
                          self.width, self.height).draw(screen)
                          
    def get_width(self):
        return self.width
    
    def get_height(self):
        return self.height