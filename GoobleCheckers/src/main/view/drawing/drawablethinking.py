'''
Created on 2012-02-26

@author: Gooble
'''
from main.view.dimensions import DEFAULT_HEIGHT, DEFAULT_WIDTH
from main.view.drawing import colours

ANTIALIAS = True

class DrawableText(object):
    def __init__(self, text, fontsize, x, y):
        self.text = text
        self.fontsize = fontsize
        self.x = x
        self.y = y
        
    def draw(self, graphics, screen):
        font = graphics.font.Font(None, self.fontsize)
        rendered_text = font.render(self.text, ANTIALIAS, colours.MEDIUMGRAY, colours.WHITE)
        screen.blit(rendered_text, [self.x, self.y - self.fontsize])
        
class DrawableThinkingText(DrawableText):
    def __init__(self):
        DrawableText.__init__(self, "The computer is thinking...", 25, DEFAULT_WIDTH/4, DEFAULT_HEIGHT/2)
        