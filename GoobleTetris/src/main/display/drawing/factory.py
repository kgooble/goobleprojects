'''
Created on 2012-03-04

@author: Gooble
'''

import pygame
import os
from main.display.drawing.rectangle import DrawableRectangle
from main.display.drawing.sprite import DrawableSprite
from main.display.drawing.text import DrawableText
        
class DrawableFactory(object):
    def create_image(self, imagefile, x=0, y=0):
        image = pygame.image.load_basic(os.path.join('resources', imagefile))
        image = image.convert()
        sprite = pygame.sprite.Sprite()
        sprite.image = image
        sprite.rect = image.get_rect()
        sprite.rect.topleft = x, y
        return DrawableSprite(sprite)
    
    def create_text(self, text, x=0, y=0, fontsize=25):
        font = pygame.font.Font(None, fontsize)
        rendered_text = font.render(text, True, pygame.color.THECOLORS['white'])
        return DrawableText(rendered_text, x, y)
    
    def create_rectangle(self, x, y):
        return DrawableRectangle(x, y)
    
