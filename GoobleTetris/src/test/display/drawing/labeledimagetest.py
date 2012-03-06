'''
Created on 2012-03-04

@author: Gooble
'''
import unittest
from main.display.drawing.labeledimage import LabeledImage
from test.display.drawing.mock.mockdrawablefactory import MockDrawableFactory

class Test(unittest.TestCase):

    def test_create_labeled_image_positions_text_to_right_of_image_centered(self):
        labelledimg = LabeledImage(imagefile='someimg.bmp', label='This is my text', x=0, y=40)
        drawables = labelledimg.createDrawablesUsingFactory(MockDrawableFactory())
        img = drawables[0]
        txt = drawables[1]
        
        # text has width of 50 and height of 25
        # img has width of 50 and height of 50
        self.assertEqual(0, img.x)
        self.assertEqual(40, img.y)
        self.assertEqual(50, img.width)
        self.assertEqual(50, img.height)
        
        # padding is 10
        self.assertEqual(60, txt.x)
        self.assertEqual(52, txt.y)
        self.assertEqual(50, txt.width)
        self.assertEqual(25, txt.height)
        
        # total width of the two (including padding), max height of the two
        self.assertEqual(10+50+50, labelledimg.get_width())
        self.assertEqual(50, labelledimg.get_height())
        

if __name__ == "__main__":
    unittest.main()