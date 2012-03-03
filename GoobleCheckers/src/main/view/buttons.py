'''
Created on 2012-02-29

@author: Gooble
'''
import main.view.gamestate 
import main.view.howtoplaystate
import main.view.difficultystate 
import main.view.splashstate
from main.view.drawing import colours
THICKNESS = 5
class Button(object):
    def __init__(self, sprite=None):
        self.sprite = sprite
        self.highlighted = False
        
    def highlight(self):
        self.highlighted = True
        
    def unhighlight(self):
        self.highlighted = False

    def draw(self, graphics, screen):
        if self.highlighted:
            x = self.sprite.rect.topleft[0] - THICKNESS
            y = self.sprite.rect.topleft[1] - THICKNESS
            width = self.sprite.rect.width + 2 * THICKNESS
            height = self.sprite.rect.height + 2 * THICKNESS 
            graphics.draw.rect(screen, colours.BLACK, [x, y, width, height])
        graphics.sprite.RenderPlain(self.sprite).draw(screen)
        
    def was_clicked(self, pos):
        return self.sprite.rect.collidepoint(pos)

class NewGameButton(Button):
    def get_state(self, info, prev_state):
        return main.view.gamestate.GameState(info)

class DifficultyButton(Button):
    def get_state(self, info, prev_state):
        return main.view.difficultystate.DifficultyState(info)
    
class HowToPlayButton(Button):
    def get_state(self, info, prev_state):
        return main.view.howtoplaystate.HowToPlayState(info)
    
class QuitButton(Button):
    def get_state(self, info, prev_state):
        raise SystemExit

class EasyButton(Button):
    def get_state(self, info, prev_state):
        info["mode"] = "Easy"
        prev_state.info = info
        return prev_state
    
class MediumButton(Button):
    def get_state(self, info, prev_state):
        info["mode"] = "Medium"
        prev_state.info = info
        return prev_state
    
class HardButton(Button):
    def get_state(self, info, prev_state):
        info["mode"] = "Hard"
        prev_state.info = info
        return prev_state
    
class BackButton(Button):
    def get_state(self, info, prev_state):
        return main.view.splashstate.SplashState(info)
