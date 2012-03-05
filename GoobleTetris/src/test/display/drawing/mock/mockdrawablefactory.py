'''
Created on 2012-03-04

@author: Gooble
'''
from test.display.drawing.mock.mocktext import MockText

class MockDrawableFactory(object):
    def createImage(self, imagefile, x, y):
        image = MockText(imagefile, x, y)
        image.width = 50
        image.height = 50
        return image
        
    def createText(self, text, x, y):
        text = MockText(text, x, y)
        text.width = 50
        text.height = 25
        return text