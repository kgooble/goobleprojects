'''
Created on 2012-03-10

@author: Gooble
'''
from main.display.drawing.textbutton import TextButton

class BackButton(TextButton):
    def __init__(self):
        TextButton.__init__(self, "Back")

    def get_action(self):
        return 'Back'
