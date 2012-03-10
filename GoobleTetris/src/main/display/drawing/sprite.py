'''
Created on 2012-03-10

@author: Gooble
'''
import pygame

class DrawableSprite(object):
    def __init__(self, sprite):
        self.sprite = sprite
    
    def draw(self, screen):
        pygame.sprite.RenderPlain(self.sprite).draw(screen)
        
    def contains(self, point):
        return self.sprite.rect.collidepoint(point)
    
    def get_height(self):
        return self.sprite.rect.height
    
    def get_width(self):
        return self.sprite.rect.width
    
    def get_x(self):
        return self.sprite.rect.topleft[0]
    
    def get_y(self):
        return self.sprite.rect.topleft[1]
    
    def set_x(self, x):
        y = self.sprite.rect.topleft[1] 
        self.sprite.rect.topleft = x, y
        
    def set_y(self, y):
        x = self.sprite.rect.topleft[0]
        self.sprite.rect.topleft = x, y