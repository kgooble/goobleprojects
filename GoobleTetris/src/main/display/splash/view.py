'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.drawing.textbutton import TextButton
from main.display.view import View
    
class StartGameButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "Start Game")
    def get_action(self):
        return 'Start'    
    
class HowToPlayButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "How To Play")
        
    def get_action(self):
        return 'HowToPlay'
    
class SettingsButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "Settings")
    def get_action(self):
        return 'Settings'

class SplashView(View):
    def create_drawables(self):
        return [StartGameButton(), HowToPlayButton(), SettingsButton()]