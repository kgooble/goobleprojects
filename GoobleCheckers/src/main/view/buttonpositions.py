'''
Created on 2012-03-02

@author: Gooble
'''
import os
from main.view.dimensions import DEFAULT_WIDTH, DEFAULT_HEIGHT

PADDING = 50
class ButtonPositions(object):
    def __init__(self, graphics):
        self.graphics = graphics
    
    def center(self, buttons, imagefiles):
        y = 0
        total_button_height = 0
        for i in range (0, len(buttons)):
            image = self.graphics.image.load_basic(os.path.join('resources', imagefiles[i]))
            image = image.convert()
            sprite = self.graphics.sprite.Sprite()
            sprite.image = image
            sprite.rect = image.get_rect()
            x = int(DEFAULT_WIDTH/2 - sprite.rect.width/2) 
            sprite.rect.topleft = x, y
            total_button_height += sprite.rect.height + PADDING
            buttons[i].sprite = sprite
            
        total_button_height -= PADDING
        y = int((DEFAULT_HEIGHT - total_button_height) / 2)
        for button in buttons:
            x = button.sprite.rect.topleft[0]
            button.sprite.rect.topleft = x, y
            y += button.sprite.rect.height + PADDING