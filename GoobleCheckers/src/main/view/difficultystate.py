'''
Created on 2012-03-02

@author: Gooble
'''
import pygame
import main.view.drawing
from main.view.buttonpositions import ButtonPositions

import main.view.buttons
from main.view.drawing import colours

class DifficultyButtons(object):
    def create(self, graphics):
        imagefiles = ['easy.bmp', 'medium.bmp', 'hard.bmp', 'back.bmp']
        buttons = [main.view.buttons.EasyButton(), 
                   main.view.buttons.MediumButton(), 
                   main.view.buttons.HardButton(), 
                   main.view.buttons.BackButton()]

        ButtonPositions(graphics).center(buttons, imagefiles)
        return buttons

class DifficultyState(object):
    def __init__(self, info={}):
        self.buttons = None
        self.info = info
    
    def display(self, screen, event=None):
        if self.buttons == None:
            self.buttons = DifficultyButtons().create(pygame)
        screen.fill(colours.WHITE)
        main.view.drawing.canvas.Canvas(screen).draw(self.buttons)
    
    def process(self, event):
        next_state = self
        clicked_button = None
        if event.type == pygame.MOUSEBUTTONUP:
            for button in self.buttons:
                if button.was_clicked(event.pos):
                    clicked_button = button
                    next_state = button.get_state(self.info, self)
                    
        self.highlight_clicked_button(clicked_button)
        return next_state
    
    def post_process(self):
        pass
    
    def highlight_clicked_button(self, clicked_button):
        if clicked_button is not None:
            for button in self.buttons:
                button.unhighlight()
            clicked_button.highlight()