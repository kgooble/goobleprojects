'''
Created on 2012-03-10

@author: Gooble
'''
import pygame
from main.display.drawing.drawable import Drawable

LINE_THICKNESS = 1

class DrawableRectangle(Drawable):
    def __init__(self, x, y, total_length=10, height=10, thickness=LINE_THICKNESS, colour=pygame.color.THECOLORS['white']):
        Drawable.__init__(self, x, y)
        self.width = total_length
        self.height = height
        self.thickness = thickness
        self.colour = colour
        
    def draw(self, screen):
        pygame.draw.rect(screen, self.colour, [self.x, self.y, self.width, self.height], self.thickness)
    
    def get_width(self):
        return self.width
    
    def get_height(self):
        return self.height
