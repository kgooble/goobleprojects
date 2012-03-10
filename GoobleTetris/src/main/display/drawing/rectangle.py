'''
Created on 2012-03-10

@author: Gooble
'''
import pygame

LINE_THICKNESS = 1

class DrawableRectangle(object):
    def __init__(self, x, y, total_length=10, height=10):
        self.x = x
        self.y = y
        self.width = total_length
        self.height = height
        
    def draw(self, screen):
        pygame.draw.rect(screen, pygame.color.THECOLORS['white'], [self.x, self.y, self.width, self.height], LINE_THICKNESS)
    
    def contains(self, point):
        pointx = point[0]
        pointy = point[1]
        return self.x <= pointx <= self.x + self.width and self.y <= pointy <= self.y + self.height
    
    def get_width(self):
        return self.width
    
    def get_height(self):
        return self.height
    
    def set_x(self, x):
        self.x = x
        
    def set_y(self, y):
        self.y = y