'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.backbutton import BackButton
from main.display.view import View
import main.display.drawing.factory
from main.display.drawing.textbutton import TextButton

class TurnOnPredictionsButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "Turn On Predictions")
    def get_action(self):
        return 'TurnOnPredictions'

class TurnOffPredictionsButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "Turn Off Predictions")
        
    def get_action(self):
        return 'TurnOffPredictions'


class SettingsView(View):
    def make_text(self, text):
        return main.display.drawing.factory.DrawableFactory().create_text(text)
    
    def create_drawables(self):
        self.buttons = [BackButton(), TurnOnPredictionsButton(), TurnOffPredictionsButton()]
        return self.buttons
