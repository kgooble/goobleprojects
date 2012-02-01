'''
Created on 2012-01-31

@author: Gooble
'''
from main.translation import Translation
from main.rotation import Rotation

class PointTransformer(object):

    def __init__(self, points, translator=Translation(), rotator=Rotation()):
        self.points = points
        self.translator = translator
        self.rotator = rotator
        
    def translate_in_dir(self, direction):
        return self.translator.in_direction(self.points, direction)

        