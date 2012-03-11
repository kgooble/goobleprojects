'''
Created on 2012-03-10

@author: Gooble
'''
import unittest
from test.display.drawing.mock.mockdrawable import MockDrawable
from main.display.buttonhandler import ButtonClickHandler

class Test(unittest.TestCase):

    def yes(self, pos):
        return True
    def no(self, pos):
        return False
    
    def test_get_clicked_button(self):
        button1 = MockDrawable("", 10, 10)
        button1.width = 10
        button1.height = 10
        button1.contains = self.no
        
        button2 = MockDrawable("", 50, 50)
        button2.width = 10
        button2.height = 10
        button2.contains = self.yes
        
        buttons = [button1, button2]
        handler = ButtonClickHandler(None, {})
        handler.buttons = buttons
        self.assertEqual(button2, handler.get_clicked_button((55, 55)))


if __name__ == "__main__":
    unittest.main()