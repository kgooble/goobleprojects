'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.backbutton import BackButton
from main.display.view import View
import main.display.drawing.factory
from main.display.drawing.textbutton import TextButton
from main.display.drawing.layout import Layout
from main.display.drawing.factory import DrawableFactory

class SettingsView(View):
    def make_text(self, text):
        return main.display.drawing.factory.DrawableFactory().create_text(text)
    
    def create_layout(self, info={}):
        back = BackButton()
        turnoff = DrawableFactory().create_image("leftarrow.bmp", "TurnOffPredictions")
        turnon = DrawableFactory().create_image("rightarrow.bmp", "TurnOnPredictions")
        
        if 'predictions.enabled' in info.keys():
            if info['predictions.enabled']:
                onoroff = "Enabled"
            else:
                onoroff = "Disabled"
            predictions_text = "Predictions " + onoroff
        else:
            predictions_text = "Predictions Disabled"
        predictions_label = TextButton(predictions_text)
        
        decrement = DrawableFactory().create_image("leftarrow.bmp", "DecrementLevel")
        increment = DrawableFactory().create_image("rightarrow.bmp", "IncrementLevel")
        
        if 'start.level' in info.keys():
            level_text = "Level " + str(info['start.level'])
        else:
            level_text = "Level 1"
        
        level_label = TextButton(level_text)
        
        layout = Layout()
        layout.add_to_row(0, turnoff, predictions_label, turnon)
        layout.add_to_row(1, decrement, level_label, increment)
        layout.add_to_row(2, back)
        
        self.layout = layout
        self.buttons = [back, turnon, turnoff, decrement, increment]
