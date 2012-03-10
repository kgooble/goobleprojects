'''
Created on 2012-03-05

@author: Gooble
'''
import unittest
from test.display.drawing.mock.mockdrawablefactory import MockDrawableFactory
from main.display.drawing.textbutton import TextButton

class Test(unittest.TestCase):

    def setUp(self):
        self.textbutton = TextButton(label='This is my text', x=0, y=40)
        self.drawables = self.textbutton.create_drawables_using_factory(MockDrawableFactory())

    def test_create_text_wrapped_in_rectangle(self):
        rect = self.drawables[0]
        txt = self.drawables[1]
        
        # text has width of 50 and height of 25
        # so rectangle has width of 50 + 2 * padding which is 10
        self.assertEqual(0, rect.x)
        self.assertEqual(40, rect.y)
        self.assertEqual(70, rect.width)
        self.assertEqual(45, rect.height)
        self.assertEqual(70, self.textbutton.get_width())
        self.assertEqual(45, self.textbutton.get_height())
        
        # padding is 10
        self.assertEqual(10, txt.x)
        self.assertEqual(50, txt.y)
        self.assertEqual(50, txt.width)
        self.assertEqual(25, txt.height)
        
    def test_set_x_and_y_updates_x_for_both_drawables_maintaining_relative_positions(self):
        rect = self.drawables[0]
        txt = self.drawables[1]
        
        self.textbutton.set_x(50)
        
        self.assertEqual(50, rect.x)
        self.assertEqual(40, rect.y)
        
        self.assertEqual(60, txt.x)
        self.assertEqual(50, txt.y)
        
        self.textbutton.set_y(100)
        
        self.assertEqual(50, rect.x)
        self.assertEqual(100, rect.y)
        
        self.assertEqual(60, txt.x)
        self.assertEqual(110, txt.y)


if __name__ == "__main__":
    unittest.main()