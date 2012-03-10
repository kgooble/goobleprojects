'''
Created on 2012-03-10

@author: Gooble
'''
import pygame

class ButtonClickHandler(object):
    
    def __init__(self, state, buttons=None):
        self.state = state
        self.buttons = buttons

    def get_clicked_button(self, pos):
        for button in self.buttons:
            if button.contains(pos):
                return button
            
    def process(self, event):
        if event.type == pygame.MOUSEBUTTONUP:
            button = self.get_clicked_button(event.pos)
            if button is not None:
                return button.get_state()
        return self.state