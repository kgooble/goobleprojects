'''
Created on 2012-03-10

@author: Gooble
'''
import main.display.drawing
import main.display.view
from main.display.backbutton import BackButton
from main.display.drawing.labeledimage import LabeledImage

class HowToPlayStateView(main.display.view.View):

    def make_text(self, text):
        return main.display.drawing.factory.DrawableFactory().create_text(text)

    def make_pic(self, imagefile):
        return main.display.drawing.factory.DrawableFactory().create_image(imagefile)

    def create_drawables(self):
        backbutton = BackButton()
        self.buttons = [backbutton]
        return [LabeledImage("downarrow.bmp", "Down"),
                backbutton]