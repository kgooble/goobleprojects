'''
Created on 2012-02-26

@author: Gooble
'''
from main.view.drawing import colours
from main.view.splashstatebuttons import SplashStateButtons
import main.view.drawing.canvas
import pygame


class SplashState(object):
    
    def __init__(self, info={"mode":"Easy"}):
        self.buttons = None
        self.info = info
    
    def display(self, screen, event=None):
        if self.buttons == None:
            self.buttons = SplashStateButtons().create(pygame)
        screen.fill(colours.WHITE)
        main.view.drawing.canvas.Canvas(screen).draw(self.buttons)
    
    def process(self, event):
        if event.type == pygame.MOUSEBUTTONUP:
            for button in self.buttons:
                if button.was_clicked(event.pos):
                    return button.get_state(self.info, self)
        return self
    
    def post_process(self):
        pass