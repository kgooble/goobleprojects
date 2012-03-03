'''
Created on 2012-02-26

@author: Gooble
'''
from main.view.drawing import colours

class Drawable(object):
    def __init__(self, x, y, width, height, colour):
        self.x = x
        self.y = y
        self.width = width
        self.height = height
        self.colour = colour
        
    def __eq__(self, other):
        return self.__dict__ == other.__dict__
    def __ne__(self, other):
        return not self.__eq__(other)
    def __repr__(self):
        return "("+str(self.x)+", "+str(self.y)+")"

class DrawablePiece(Drawable):
    def draw(self, graphics, screen):
        graphics.draw.ellipse(screen, self.colour, [self.x, self.y, self.width, self.height])

class DrawableKing(DrawablePiece):
    def draw(self, graphics, screen):
        DrawablePiece.draw(self, graphics, screen)
        graphics.draw.line(screen, colours.WHITE, [self.x, self.y], [self.x+self.width, self.y+self.height])
        
class DrawableBackground(Drawable):
    def draw(self, graphics, screen):
        graphics.draw.rect(screen, self.colour, [self.x, self.y, self.width, self.height])
        