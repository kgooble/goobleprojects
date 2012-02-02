'''
Created on 2012-02-01

@author: Gooble
'''
import unittest
from main import tile
from main.point import Point
from main.oneshapespawn import OneShapeSpawn

class Test(unittest.TestCase):

    def test_spawn_centered_shape(self):
        spawner = OneShapeSpawn(tile.T_TILE)
        shape = spawner.get_next_centered_shape(10)
        self.assertEqual([Point(0, 5),
                          Point(0, 4),
                          Point(-1, 5),
                          Point(0, 6)], shape.get_points())
        

if __name__ == "__main__":
    unittest.main()