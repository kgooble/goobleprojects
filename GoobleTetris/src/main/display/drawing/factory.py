'''
Created on 2012-03-04

@author: Gooble
'''

import pygame
import os

LINE_THICKNESS = 1

class DrawableSprite(object):
    def __init__(self, sprite):
        self.sprite = sprite
    
    def draw(self, screen):
        pygame.sprite.RenderPlain(self.sprite).draw(screen)
        
    def contains(self, point):
        return self.sprite.rect.collidepoint(point)
        
class DrawableText(object):
    def __init__(self, text, x, y):
        self.text = text
        self.x = x
        self.y = y
        
    def draw(self, screen):
        screen.blit(self.text, [self.x, self.y])
        
    def contains(self, point):
        pointx = point[0]
        pointy = point[1]
        return self.x <= pointx <= self.x + self.get_width() and self.y <= pointy <= self.y + self.get_height()
    
    def get_width(self):
        return self.text.get_width()
    
    def get_height(self):
        return self.text.get_height()
        
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

class DrawableFactory(object):
    def create_image(self, imagefile, x, y):
        image = pygame.image.load_basic(os.path.join('resources', imagefile))
        image = image.convert()
        sprite = pygame.sprite.Sprite()
        sprite.image = image
        sprite.rect = image.get_rect()
        sprite.rect.topleft = x, y
        return DrawableSprite(sprite)
    
    def create_text(self, text, x, y, fontsize=25):
        font = pygame.font.Font(None, fontsize)
        rendered_text = font.render(text, True, pygame.color.THECOLORS['white'])
        return DrawableText(rendered_text, x, y)
    
    def create_rectangle(self, x, y):
        return DrawableRectangle(x, y)
    
