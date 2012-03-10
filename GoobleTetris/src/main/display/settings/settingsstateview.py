'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.backbutton import BackButton
from main.display.view import View
import main.display.drawing.factory

class SettingsStateView(View):
    def make_text(self, text):
        return main.display.drawing.factory.DrawableFactory().create_text(text)
    
    def create_drawables(self):
        backbutton = BackButton()
        self.buttons = [backbutton]
        return [self.make_text("Settings"),
                self.make_text("Don't kill a cow"),
                backbutton]
