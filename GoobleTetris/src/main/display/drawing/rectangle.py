'''
Created on 2012-03-10

@author: Gooble
'''
import pygame
from main.display.drawing.drawable import Drawable

LINE_THICKNESS = 1

class DrawableRectangle(Drawable):
    def __init__(self, x, y, total_length=10, height=10):
        Drawable.__init__(self, x, y)
        self.width = total_length
        self.height = height
        
    def draw(self, screen):
        pygame.draw.rect(screen, pygame.color.THECOLORS['white'], [self.x, self.y, self.width, self.height], LINE_THICKNESS)
    
    def get_width(self):
        return self.width
    
    def get_height(self):
        return self.height
