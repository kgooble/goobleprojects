'''
Created on 2012-03-10

@author: Gooble
'''
import main.display.drawing
import main.display.view
from main.display.backbutton import BackButton
from main.display.drawing.labeledimage import LabeledImage
from main.display.drawing.layout import Layout

class HowToPlayView(main.display.view.View):

    def make_text(self, text):
        return main.display.drawing.factory.DrawableFactory().create_text(text)

    def make_pic(self, imagefile):
        return main.display.drawing.factory.DrawableFactory().create_image(imagefile)

    def create_layout(self):
        down = LabeledImage("downarrow.bmp", "Down")
        backbutton = BackButton()
        
        layout = Layout()
        layout.add_to_row(0, down)
        layout.add_to_row(1, backbutton)

        self.buttons = [backbutton]
        self.layout = layout