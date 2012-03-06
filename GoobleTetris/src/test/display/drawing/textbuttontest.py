'''
Created on 2012-03-05

@author: Gooble
'''
import unittest
from test.display.drawing.mock.mockdrawablefactory import MockDrawableFactory
from main.display.drawing.textbutton import TextButton

class Test(unittest.TestCase):

    def test_create_text_wrapped_in_rectangle(self):
        textbutton = TextButton(label='This is my text', x=0, y=40)
        drawables = textbutton.createDrawablesUsingFactory(MockDrawableFactory())
        rect = drawables[0]
        txt = drawables[1]
        
        # text has width of 50 and height of 25
        # so rectangle has width of 50 + 2 * padding which is 10
        self.assertEqual(0, rect.x)
        self.assertEqual(40, rect.y)
        self.assertEqual(70, rect.width)
        self.assertEqual(45, rect.height)
        self.assertEqual(70, textbutton.get_width())
        self.assertEqual(45, textbutton.get_height())
        
        # padding is 10
        self.assertEqual(10, txt.x)
        self.assertEqual(50, txt.y)
        self.assertEqual(50, txt.width)
        self.assertEqual(25, txt.height)
        


if __name__ == "__main__":
    unittest.main()