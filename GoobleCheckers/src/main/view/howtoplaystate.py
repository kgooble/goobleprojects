'''
Created on 2012-03-02

@author: Gooble
'''
import main.view.drawing
from main.view.drawing import colours
from main.view.drawing.drawablethinking import DrawableText
import main.view.buttons
import pygame
from main.view.buttonpositions import ButtonPositions

class HowToPlayState(object):
    def __init__(self, info={}):
        self.drawables = None
        self.button = None
        self.info = info
        
    def display(self, screen, event=None):
        if self.drawables == None:
            self.drawables = self.create_drawables()
        screen.fill(colours.WHITE)
        main.view.drawing.canvas.Canvas(screen).draw(self.drawables)
        
    def process(self, event):
        if event.type == pygame.MOUSEBUTTONUP:
            if self.button.was_clicked(event.pos):
                return self.button.get_state(self.info, self)
        return self
        
    def create_drawables(self):
        self.button = main.view.buttons.BackButton()
        ButtonPositions(pygame).center([self.button], ['back.bmp'])
        drawables = [
        DrawableText("To play checkers, drag your piece ", 25, 50, 100),
        DrawableText("to your desired position.", 25, 50, 130),
        DrawableText("You start off as the top player.", 25, 50, 160),
        DrawableText("For more rules, please google the game.", 25, 50, 190),
        self.button]
        return drawables
    
    def post_process(self):
        pass