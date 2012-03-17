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
        down = LabeledImage("downarrow.bmp", "Press the Down Arrow Key to speed up the shape")
        rotate = LabeledImage("uparrow.bmp", "Press the Up Arrow Key to rotate the shape")
        left = LabeledImage("leftarrow.bmp", "Press the Left Arrow Key to move the shape left")
        right = LabeledImage("rightarrow.bmp", "Press the Right Arrow Key to move the shape right")
        spacebar = LabeledImage("spacebar.bmp", "Press the Space Bar to drop the shape") 
        backbutton = BackButton()
        
        layout = Layout()
        layout.add_to_row(0, left)
        layout.add_to_row(1, right)
        layout.add_to_row(2, down)
        layout.add_to_row(3, rotate)
        layout.add_to_row(4, spacebar)
        layout.add_to_row(6, backbutton)

        self.buttons = [backbutton]
        self.layout = layout