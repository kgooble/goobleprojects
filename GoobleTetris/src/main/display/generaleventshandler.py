'''
Created on 2012-03-10

@author: Gooble
'''

import pygame

class GeneralEventsHandler(object):
    def process_general(self, event, info):
        if event.type == pygame.QUIT:
            raise SystemExit
        return self.process(event, info)
    
    def process(self, event, info):
        pass
