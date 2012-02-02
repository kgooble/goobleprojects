'''
Created on 2012-01-29

@author: Gooble
'''
import unittest
import main.config
import main.board

from main.point import Point

class Test(unittest.TestCase):

    def setUp(self):
        config = main.config.Configuration().create(["0 0 0 0 0 0",
                                                     "1 1 0 0 0 0",
                                                     "1 0 0 0 0 0"])
#        self.validity = main.validity.Board(config)


if __name__ == "__main__":
    unittest.main()