'''
Created on 2012-03-10

@author: Gooble
'''

import pygame

class QuitPlusHandler(object):
    def __init__(self, otherhandler):
        self.otherhandler = otherhandler
        
    def process(self, event):
        if event.type == pygame.QUIT:
            raise SystemExit
        return self.otherhandler.process(event)
