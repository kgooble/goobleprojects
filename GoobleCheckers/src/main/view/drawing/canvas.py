'''
Created on 2012-02-26

@author: Gooble
'''

import pygame

class Canvas(object):
    
    def __init__(self, screen, graphics=pygame):
        self.screen = screen
        self.graphics = graphics
    
    def draw(self, drawables):
        for drawable in drawables:
            drawable.draw(self.graphics, self.screen)
