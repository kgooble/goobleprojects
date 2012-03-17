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
    
    def create_layout(self):
        back = BackButton()
        turnon = DrawableFactory().create_image("leftarrow.bmp", "TurnOnPredictions")
        turnoff = DrawableFactory().create_image("rightarrow.bmp", "TurnOffPredictions")
        label = TextButton("Toggle Predictions")
        
        layout = Layout()
        layout.add_to_row(0, turnon, label, turnoff)
        layout.add_to_row(1, back)
        
        self.layout = layout        
        self.buttons = [back, turnon, turnoff]
