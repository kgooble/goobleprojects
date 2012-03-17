'''
Created on 2012-03-04

@author: Gooble
'''

import pygame
import os
from main.display.drawing.rectangle import DrawableRectangle
from main.display.drawing.sprite import DrawableSprite
from main.display.drawing.text import DrawableText
from main.display.drawing.piece import DrawablePiece

class DrawableFactory(object):
    def create_image(self, imagefile, action='', x=0, y=0):
        image = pygame.image.load_basic(os.path.join('resources', imagefile))
        image = image.convert()
        sprite = pygame.sprite.Sprite()
        sprite.image = image
        sprite.rect = image.get_rect()
        sprite.rect.topleft = x, y
        return DrawableSprite(sprite, action)
    
    def create_text(self, text, x=0, y=0, fontsize=25):
        font = pygame.font.Font(None, fontsize)
        rendered_text = font.render(text, True, pygame.color.THECOLORS['white'])
        return DrawableText(rendered_text, x, y)
    
    def create_rectangle(self, x, y):
        return DrawableRectangle(x, y)
    
    def create_piece(self, coords, colour, empty, faded=False):
        if faded:
            colour = self.fade(colour)
        return DrawablePiece(coords.get_start_x(), coords.get_start_y(), coords.get_width(), coords.get_height(), colour, empty)
    
    def fade(self, color):
        new_color = [color[0] - 200, color[1] - 200, color[2] - 200]
        for i in range (0, len(new_color)):
            if new_color[i] < 0:
                new_color[i] = 0
        return new_color
    
