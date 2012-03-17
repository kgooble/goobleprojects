'''
Created on 2012-03-10

@author: Gooble
'''
import pygame
import main.display.generaleventshandler

class ButtonClickHandler(main.display.generaleventshandler.GeneralEventsHandler):
    
    def __init__(self, state, actions={}):
        self.state = state
        self.actions = actions
        self.buttons = None

    def get_clicked_button(self, pos):
        for button in self.buttons:
            if button.contains(pos):
                return button
            
    def process(self, event, info):
        if event.type == pygame.MOUSEBUTTONUP:
            button = self.get_clicked_button(event.pos)
            if button is not None:
                return self.actions[button.get_action()].do_action(info, self.state)
        return self.state